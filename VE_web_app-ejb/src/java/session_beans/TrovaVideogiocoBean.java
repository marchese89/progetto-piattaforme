/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Videogioco;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.VideogiocoPrint;

/**
 *
 * @author Antonio Giovanni
 */
@Stateless
public class TrovaVideogiocoBean implements TrovaVideogiocoBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public VideogiocoPrint findById(String id) {
        Query q = manager.createNamedQuery("Videogioco.findById");
        q.setParameter("id", Integer.parseInt(id));
        try {
            Videogioco v = (Videogioco) q.getSingleResult();
            return new VideogiocoPrint(v.getNome(), id, v.getDescrizione(), v.getPercorso(),
                    v.getQtaDisp().toString(),v.getPrezzoVendita().toString(),v.getCategoria().getNome());
        } catch (NoResultException e) {
            return null;
        }
    }

}
