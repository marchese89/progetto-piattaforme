/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import javax.ejb.Local;

/**
 *
 * @author Antonio Giovanni
 */
@Local
public interface ModificaProdottoBeanLocal {
    boolean modifica(String id,String nome,String descrizione,
            double prezzoVendita,int disponibile,String categoria,String percorsoFoto);
    boolean elimina(String id);
}
