/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Ordine;
import entities.Videogioco;
import java.sql.Date;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Giovanni
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class InviaOrdineBean implements InviaOrdineBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    private int modPagamento = 0;

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean invia(String cfUser, HashMap<Integer, Integer> prodotti) {

        GregorianCalendar gc = new GregorianCalendar();
        Date data = new Date(gc.getTimeInMillis());
        double prezzoTotale = 0;
        Query trovaVideogioco = manager.createNamedQuery("Videogioco.findById");

        Set<Integer> codici = prodotti.keySet();
        for (Integer i : codici) {
            trovaVideogioco.setParameter("id", i);
            Videogioco v = (Videogioco) trovaVideogioco.getSingleResult();
            v.setQtaDisp(v.getQtaDisp()-prodotti.get(i));
            prezzoTotale += v.getPrezzoVendita()*prodotti.get(i);
        }
        prezzoTotale += 5.9;//spese di spedizione
        if (modPagamento == 1) {
            prezzoTotale += 9.9;
        }
        //inseriamo l'ordine
        Query inserisciOrdine = manager.createNativeQuery("INSERT INTO Ordine(intestatario,data,prezzo_totale,mod_pagamento,stato) VALUES (?,?,?,?,?)");
        inserisciOrdine.setParameter(1, cfUser);
        inserisciOrdine.setParameter(2, data);
        inserisciOrdine.setParameter(3, prezzoTotale);
        inserisciOrdine.setParameter(4, modPagamento);
        inserisciOrdine.setParameter(5, "da evadere");
        int res = inserisciOrdine.executeUpdate();
        if (res == 0) {
            return false;
        }
        //troviamo il codice dell'ordine appena inserito
       
        Query trovaOrdine = manager.createNamedQuery("Ordine.findByData");
        trovaOrdine.setParameter("data", data);

        Collection<Ordine> ordini = (Collection<Ordine>)trovaOrdine.getResultList();
        Ordine appenaFatto = null;

        if (ordini.size() >= 1) {
            for (Ordine o : ordini) {
                if (appenaFatto != null && appenaFatto.getCodice() < o.getCodice()) {
                    appenaFatto = o;
                }
                if (appenaFatto == null) {
                    appenaFatto = o;
                }
            }
        }
        int codice = appenaFatto.getCodice();
        //inseriamo tutti i prodotti nella tabella riga_ordine
        Query inserisciRiga = manager.createNativeQuery("INSERT INTO riga_ordine VALUES (?,?,?)");
        int risultato_parz;
        for (Integer i : codici) {
            inserisciRiga.setParameter(1, codice);
            inserisciRiga.setParameter(2, i);
            inserisciRiga.setParameter(3, prodotti.get(i));
            risultato_parz = inserisciRiga.executeUpdate();
            if (risultato_parz == 0) {
                return false;
            }
        }

        return res == 1;
    }

    void setModPagamento(int mod) {
        modPagamento = mod;
    }
}
