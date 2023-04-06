/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Videogioco;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Antonio Giovanni
 */
@Stateless
public class ModificaProdottoBean implements ModificaProdottoBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public boolean modifica(String id, String nome, String descrizione,
            double prezzoVendita, int disponibile, String categoria, String percorsoFoto) {

        Query modifica = manager.createNativeQuery
        ("UPDATE Videogioco SET nome = ?,descrizione = ?, prezzo_vendita = ?, qta_disp = ?, percorso = ? WHERE id = ?");
        modifica.setParameter(1, nome);
        modifica.setParameter(2, descrizione);
        modifica.setParameter(3, prezzoVendita);
        modifica.setParameter(4, disponibile);
        modifica.setParameter(5, percorsoFoto);
        modifica.setParameter(6, id);
        int risultato = modifica.executeUpdate();
        Query trova = manager.createNamedQuery("Videogioco.findById");
        trova.setParameter("id", Integer.parseInt(id));
        Videogioco v = (Videogioco)trova.getSingleResult();
        manager.merge(v);
        return  risultato == 1;
    }

    @Override
    public boolean elimina(String id) {
        Query trova = manager.createNamedQuery("Videogioco.findById");
        trova.setParameter("id", Integer.parseInt(id));
        Videogioco v = (Videogioco)trova.getSingleResult();
        Query elimina = manager.createNativeQuery("DELETE FROM Videogioco WHERE id = ?;");
        elimina.setParameter(1, v.getId());
        int risultato = elimina.executeUpdate();
        return risultato == 1;
    }
}
