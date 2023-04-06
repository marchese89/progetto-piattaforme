/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Videogioco;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.VideogiocoPrint;

/**
 *
 * @author Antonio Giovanni
 */
@Stateless
public class RicercaBean implements RicercaBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public HashSet<VideogiocoPrint> getResultList(String cerca) {
        StringTokenizer st = new StringTokenizer(cerca, " ");
        HashSet<VideogiocoPrint> risultato = new HashSet<>();
        Query q = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> tutti = q.getResultList();
        
        while (st.hasMoreTokens()) {
            ricerca(st.nextToken(), tutti, risultato);
        }
        return risultato;
    }

    private void ricerca(String chiave, Collection<Videogioco> tutti,
            HashSet<VideogiocoPrint> parziale) {
        Iterator<Videogioco> it = tutti.iterator();
        while (it.hasNext()) {
            Videogioco v = it.next();
            VideogiocoPrint vp = new VideogiocoPrint(v.getNome(),
                    v.getId().toString(), v.getDescrizione(), v.getPercorso(),
                    v.getQtaDisp().toString(),v.getPrezzoVendita().toString(),v.getCategoria().getNome());
            if (chiave.equalsIgnoreCase(v.getNome())) {
                if (!parziale.contains(vp)) {
                    parziale.add(vp);
                    break;
                }
            } else {
                StringTokenizer st = new StringTokenizer(v.getDescrizione(), " ");
                while (st.hasMoreTokens()) {
                    if (chiave.equalsIgnoreCase(st.nextToken())) {
                        parziale.add(vp);
                        break;
                    }
                }
            }

        }

    }

}
