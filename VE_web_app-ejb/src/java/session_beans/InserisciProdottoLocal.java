/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package session_beans;

import javax.ejb.Local;

/**
 *
 * @author Valentino
 */
@Local
public interface InserisciProdottoLocal {
     public boolean inserisciProdotto(String nome,String descrizione,double prezzo,int disponibile,String categoria,String percorso);
}
