/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Categoria;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanni
 */
@Stateless
public class CategorieBean implements CategorieBeanLocal {
    
    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;
    
    
    @Override
    public LinkedList<String> getCategorie(){
        
        LinkedList<String> risultato = new LinkedList<>();
        
        try{
            
        Query q = manager.createNamedQuery("Categoria.findAll");
        Collection<Categoria> categorie = q.getResultList();
        Iterator<Categoria> it = categorie.iterator();
        while(it.hasNext()){
            risultato.add(it.next().getNome());
        }       
        }catch(Exception e){           
        }
        return risultato;
    }

    @Override
    public void rimuovi(String nomeCat) {
        Query q = manager.createNamedQuery("Categoria.findByNome");
        q.setParameter("nome", nomeCat);
        Categoria cat = (Categoria)q.getSingleResult();
        manager.remove(cat);
        manager.flush();
    }
}
