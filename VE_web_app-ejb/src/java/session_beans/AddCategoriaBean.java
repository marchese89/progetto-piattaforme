/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Categoria;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanni
 */
@Stateless
public class AddCategoriaBean implements AddCategoriaBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public boolean addCategoria(String nome) {
        if(nome.equals(""))
            return false;
        Query q = manager.createNamedQuery("Categoria.findByNome");
        q.setParameter("nome", nome.toLowerCase());
        Categoria c;
        try {
            c = (Categoria) q.getSingleResult();
        } catch (NoResultException e) {
            q = manager.createNativeQuery("INSERT INTO Categoria VALUES(?)");
            q.setParameter(1, nome.toLowerCase());
            int res = q.executeUpdate();
            return res != 0;

        }
        return false;
    }

}
