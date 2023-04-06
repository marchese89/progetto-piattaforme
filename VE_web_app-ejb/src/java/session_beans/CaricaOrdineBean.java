/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Ordine;
import entities.RigaOrdine;
import entities.Videogioco;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Giovanni
 */
@Stateless
public class CaricaOrdineBean implements CaricaOrdineBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public HashMap<Integer, Integer> getCarrello(int codice) {
        HashMap<Integer, Integer> risultato = new HashMap<>();
        Query trovaRigheOrdine = manager.createNamedQuery("RigaOrdine.findByCodice");
        trovaRigheOrdine.setParameter("codice", codice);
        List<RigaOrdine> righe = trovaRigheOrdine.getResultList();
        for(RigaOrdine r : righe) {
                risultato.put(r.getVideogioco().getId(), r.getQta());
        }
        return risultato;
    }

    @Override
    public int getModPagamento(int codice) {
        Query trovaOrdine = manager.createNamedQuery("Ordine.findByCodice");
        trovaOrdine.setParameter("codice", codice);
        Ordine o = (Ordine) trovaOrdine.getSingleResult();
        return o.getModPagamento();
    }

    @Override
    public String getNomeVideogioco(int codice) {
        Query trovaVideogioco = manager.createNamedQuery("Videogioco.findById");
        trovaVideogioco.setParameter("id", codice);
        Videogioco v = (Videogioco) trovaVideogioco.getSingleResult();
        return v.getNome();
    }

    @Override
    public double getPrezzoVideogioco(int codice) {
        Query trovaVideogioco = manager.createNamedQuery("Videogioco.findById");
        trovaVideogioco.setParameter("id", codice);
        Videogioco v = (Videogioco) trovaVideogioco.getSingleResult();
        return v.getPrezzoVendita();
    }
}
