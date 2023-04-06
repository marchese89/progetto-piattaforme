/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Videogioco;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanni
 */
@Stateless
public class HomeBean implements HomeBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public HashMap<String,String> generateRandomHome() {
        
        Query trovaProdotti = manager.createNamedQuery("Videogioco.findAll");
        Collection<Videogioco> allProducts = trovaProdotti.getResultList();
        int elementi = 9;
        HashMap<String,String> risultato = new HashMap<>();
        for(Videogioco v: allProducts){
            if(elementi >0){
            risultato.put(v.getPercorso(), "index.jsp?pagina=ShowSingleP&idVideogioco="+v.getId());
            elementi--;
            }else{
                break;
            }
        }
        
        return risultato;
    }
}
