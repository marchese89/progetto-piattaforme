/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Cliente;
import entities.Indirizzo;
import entities.Ordine;
import java.util.Collection;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.IndirizzoPrint;
import utility.OrdinePrint;
import utility.UtentePrint;

/**
 *
 * @author Giovanni
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class CaricaUtenteBean implements CaricaUtenteBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public LinkedList<UtentePrint> getUtenti() {
        Query trovaUtenti = manager.createNamedQuery("Cliente.findAll");
        Collection<Cliente> utenti = trovaUtenti.getResultList();
        LinkedList<UtentePrint> risultato = new LinkedList<>();
        for (Cliente c : utenti) {
            risultato.add(new UtentePrint(c.getCf(), c.getNome(), c.getCognome()));
        }
        return risultato;
    }

    @Override
    public LinkedList<OrdinePrint> getOrdiniUtente(String cfUser) {
        //troviamo prima tutti gli ordini che ha fatto il "nostro" cliente
        Query trovaOrdini = manager.createNamedQuery("Ordine.findAll");
        Collection<Ordine> ordini = trovaOrdini.getResultList();
        LinkedList<OrdinePrint> risultato = new LinkedList<>();
        for (Ordine o : ordini) {
            if (o.getIntestatario().getCf().equals(cfUser)) {
                risultato.add(new OrdinePrint(o.getCodice(), o.getIntestatario().getCf(),
                        o.getData(), o.getPrezzoTotale(), o.getModPagamento(), o.getStato()));
            }
        }
        return risultato;
    }

    @Override
    public IndirizzoPrint getRecapito(String cfUser) {
        Query trovaIndirizzo = manager.createNamedQuery("Indirizzo.findByCodCliente");
        trovaIndirizzo.setParameter("codCliente", cfUser);
        Indirizzo ind = (Indirizzo) trovaIndirizzo.getResultList().get(0);
        return new IndirizzoPrint(ind.getCliente().getCf(), ind.getIndirizzoPK().getVia(),
                ind.getIndirizzoPK().getNumCivico(), ind.getCap(), ind.getIndirizzoPK().getComune(),
                ind.getProvincia());
    }

    @Override
    public LinkedList<OrdinePrint> aggiornaStato(int idOrdine, String stato) {
        Query aggiornaStato = manager.createNativeQuery("UPDATE ordine SET stato=? WHERE codice=?;");
        aggiornaStato.setParameter(1, stato);
        aggiornaStato.setParameter(2, idOrdine);
        aggiornaStato.executeUpdate();
        Query trova = manager.createNamedQuery("Ordine.findByCodice");
        trova.setParameter("codice", idOrdine);
        Ordine ord1 = (Ordine) trova.getSingleResult();
        String cfUser = ord1.getIntestatario().getCf();
        manager.refresh(ord1);
        //troviamo prima tutti gli ordini che ha fatto il "nostro" cliente
        Query trovaOrdini = manager.createNamedQuery("Ordine.findAll");
        Collection<Ordine> ordini = trovaOrdini.getResultList();
        LinkedList<OrdinePrint> risultato = new LinkedList<>();
        for (Ordine o : ordini) {
            if (o.getIntestatario().getCf().equals(cfUser)) {
                risultato.add(new OrdinePrint(o.getCodice(), o.getIntestatario().getCf(),
                        o.getData(), o.getPrezzoTotale(), o.getModPagamento(), o.getStato()));
            }
        }
        return risultato;
    }

    @Override
    public LinkedList<OrdinePrint> getOrdiniNonEvasi(Integer idOrdine) {

        if (idOrdine != -1) {
            Query trovaOrd = manager.createNamedQuery("Ordine.findByCodice");
            trovaOrd.setParameter("codice", idOrdine);
            Ordine o = (Ordine) trovaOrd.getSingleResult();
            manager.refresh(o);
        }
        Query trovaOrdini = manager.createNamedQuery("Ordine.findByStato");
        trovaOrdini.setParameter("stato", "da evadere");
        Collection<Ordine> daEvadere = trovaOrdini.getResultList();
        trovaOrdini.setParameter("stato", "in lavorazione");
        Collection<Ordine> inLavorazione = trovaOrdini.getResultList();
        LinkedList<OrdinePrint> risultato = new LinkedList<>();
        for (Ordine o : daEvadere) {
            risultato.add(new OrdinePrint(o.getCodice(), o.getIntestatario().getCf(),
                    o.getData(), o.getPrezzoTotale(), o.getModPagamento(), o.getStato()));
        }
        for (Ordine o : inLavorazione) {
            risultato.add(new OrdinePrint(o.getCodice(), o.getIntestatario().getCf(),
                    o.getData(), o.getPrezzoTotale(), o.getModPagamento(), o.getStato()));
        }

        return risultato;

    }

    @Override
    public LinkedList<UtentePrint> getUtente(String cf) {
        LinkedList<UtentePrint> risultato = new LinkedList<>();
        Query q = manager.createNamedQuery("Cliente.findByCf");
        q.setParameter("cf", cf);
        Cliente c = (Cliente) q.getSingleResult();
        manager.refresh(c);
        UtentePrint u = new UtentePrint(cf, c.getNome(), c.getCognome());
        Collection<Indirizzo> indirizzi = c.getIndirizzoCollection();
        for (Indirizzo ind : indirizzi) {
            manager.refresh(ind);
            u.setCap(ind.getCap().toString());
            u.setEmail(c.getEmail());
            u.setNumeroC(Integer.toString(ind.getIndirizzoPK().getNumCivico()));
            u.setVia(ind.getIndirizzoPK().getVia());
            u.setPassword(c.getPassword());
            u.setComune(ind.getIndirizzoPK().getComune());
            u.setProv(ind.getProvincia());
            risultato.add(u);
        }
        return risultato;
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void modificaDatiUtente(UtentePrint utente) {
        Query modificaUtente = manager.createNativeQuery("UPDATE Cliente SET email = ?, password = ? WHERE cf = ?");
        modificaUtente.setParameter(1, utente.getEmail());
        modificaUtente.setParameter(2, utente.getPassword());
        modificaUtente.setParameter(3, utente.getCF());
        modificaUtente.executeUpdate();
        //modifichiamo l'indirizzo
        Query modificaIndirizzo
                = manager.createNativeQuery("UPDATE Indirizzo SET via = ?, num_civico = ?, cap = ?, comune = ?, provincia = ? WHERE cod_cliente = ?");
        modificaIndirizzo.setParameter(1, utente.getVia());
        modificaIndirizzo.setParameter(2, utente.getNumeroC());
        modificaIndirizzo.setParameter(3, utente.getCap());
        modificaIndirizzo.setParameter(4, utente.getComune());
        modificaIndirizzo.setParameter(5, utente.getProv());
        modificaIndirizzo.setParameter(6, utente.getCf());
        modificaIndirizzo.executeUpdate();
    }

}
