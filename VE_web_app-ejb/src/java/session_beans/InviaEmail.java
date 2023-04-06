/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Cliente;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utility.MailSender;

/**
 *
 * @author giovanni
 */
@Stateless
public class InviaEmail implements InviaEmailLocal {
    
    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public boolean send(String dest){
        Query q = manager.createNamedQuery("Cliente.findByEmail");
        q.setParameter("email",dest);
        List<Cliente> res = q.getResultList();
        if(res == null || res.isEmpty())
            return false;
        Cliente c = res.get(0);
        try {
            MailSender.send("prolagd1@gmail.com",dest,c.getPassword());
        } catch (Exception ex) {
            Logger.getLogger(InviaEmail.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
