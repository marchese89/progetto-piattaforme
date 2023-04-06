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


@Stateless
public class InserisciProdotto implements InserisciProdottoLocal {

     @PersistenceContext(unitName = "VE_web_app-ejbPU")
     private EntityManager manager;
    
     @Override
     public boolean inserisciProdotto(String nome,String descrizione,double prezzo,int disponibile,String categoria,String percorso){
          
        boolean risultato = true;
        
        Query query = manager.createNativeQuery
        ("INSERT INTO videogioco(nome,descrizione,prezzo_vendita,qta_disp,categoria,percorso) VALUES(?,?,?,?,?,?)");
        
        Query q2 = manager.createNamedQuery("Videogioco.findByNome");
        q2.setParameter("nome", nome);
        
        boolean daInserire = false;
        try{
        Videogioco v = (Videogioco)q2.getSingleResult();
        }catch(Exception e){
            daInserire = true;
        }
        if(!daInserire)
            return false;
        query.setParameter(1, nome);
        query.setParameter(2, descrizione);
        query.setParameter(3, prezzo);
        query.setParameter(4, disponibile);
        query.setParameter(5, categoria);
        query.setParameter(6,percorso);
        int res = 0;
        try{
        res = query.executeUpdate();
        
        }catch(Exception e){
            risultato = false;
        }
        if(res != 0)
           risultato=true;
      
      return risultato;
     }
}
