/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanni
 */
@Stateless
public class SignInBean implements SignInBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    @Override
    public boolean[] iscriviti(String nome, String cognome, String cf, String email1, String email2, String pass1, String pass2, String dataN,
            String via, String nCivico, String cap, String comune, String provincia) {
        //tutti gli errori che si possono riscontrare durante la registrazione
        //codice fiscale con numero di cifre != da 16 (0)
        //email e email di conferma diverse (1)
        //password e password di conferma diverse (2)
        //tupla già presente (3)
        boolean someProblems = false;
        boolean[] errori = new boolean[6];
        for (int i = 0; i < errori.length; i++) {
            errori[i] = false;
        }
        if (cf.length() != 16) {
            errori[0] = true;
            someProblems = true;
        }
        if (!email1.equals(email2)) {
            errori[1] = true;
            someProblems = true;
        }
        if (!pass1.equals(pass2)) {
            errori[2] = true;
            someProblems = true;
        }

        if (someProblems) {
            return errori;
        }
        Query insCliente = manager.createNativeQuery("INSERT INTO Cliente VALUES(?,?,?,?,?,?)");
        insCliente.setParameter(1, cf);
        insCliente.setParameter(2, email1);
        insCliente.setParameter(3, pass1);
        insCliente.setParameter(4, nome);
        insCliente.setParameter(5, cognome);
        insCliente.setParameter(6, 0.0);
        int res1 = 0;

        try {
            res1 = insCliente.executeUpdate();
        } catch (Exception e) {
            errori[4] = true;
        }
        if (res1 == 0) {
            errori[3] = true;
        }

        Query insIndirizzo = manager.createNativeQuery("INSERT INTO indirizzo VALUES (?,?,?,?,?,?)");
        insIndirizzo.setParameter(1, cf);
        insIndirizzo.setParameter(2, via);
        insIndirizzo.setParameter(3, nCivico);
        insIndirizzo.setParameter(4, cap);
        insIndirizzo.setParameter(5, comune);
        insIndirizzo.setParameter(6, provincia);
        
        int res2 = 0;
        try {
            res2 = insIndirizzo.executeUpdate();
        } catch (Exception e) {
            someProblems = true;
        }
        
        if (res1 == 0) {
            errori[3] = true;
        }
        
        if(res2 == 0 || someProblems){
            errori[5] = true;
        }

        return errori;
    }

}
