/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import entities.Amministratore;
import entities.Cliente;
import java.util.List;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author giovanni
 */
@Stateful
public class LoginBean implements LoginBeanLocal {

    @PersistenceContext(unitName = "VE_web_app-ejbPU")
    private EntityManager manager;

    //dati interni al bean
    private String nomeUtente;
    private String password;
    private String cf;
    private String pageParameters = "";
    private boolean loginOk;
    //la pagina jsp di login che usa questo bean prende la stringa 
    //di risultato del metodo principale e la usa per indirizzare
    //l'utente che effettua il login nella sua area personale
    private final String pathUtente = "index.jsp?user=";
    private final String pathAdmin = "index.jsp?pagina=admin";
    private final String pathErrore = "index.jsp?pagina=errorecause=nouser";

    @Override
    public String effettuaLogin(String email, String password) {
        if (cercaUtente(email, password)) {
            loginOk = true;
            return pathUtente + nomeUtente + "&login=ok" + pageParameters;
        }
        if (cercaAmministratore(email, password)) {
            loginOk = true;
            return pathAdmin;
        }
        loginOk = false;
        return pathErrore;
    }

    @Override
    public String getNomeUtente() {
        return nomeUtente;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getCf() {
        return cf;
    }

    private boolean cercaUtente(String email, String password) {
        Query q = manager.createNamedQuery("Cliente.findByEmail");
        q.setParameter("email", email);
        List<Cliente> res = q.getResultList();
        if (res == null || res.isEmpty()) {
            return false;
        }
        Cliente c = res.get(0);
        nomeUtente = c.getNome();
        cf = c.getCf();
        return c.getPassword().equals(password);
    }

    private boolean cercaAmministratore(String email, String password) {
        Query q = manager.createNamedQuery("Amministratore.findByEmail");
        q.setParameter("email", email);
        //q.setParameter("password", password);
        List<Amministratore> res = q.getResultList();
        if (res == null || res.isEmpty()) {
            return false;
        }
        Amministratore a = res.get(0);
        nomeUtente = a.getEmail();
        return a.getPassword().equals(password);
    }
    
    public boolean getLoginOk(){
        return loginOk;
    }
    @Override
    public void setPageParam(String nome, String valore) {
        pageParameters = pageParameters + "&" + nome + "=" + valore;
    }
}
