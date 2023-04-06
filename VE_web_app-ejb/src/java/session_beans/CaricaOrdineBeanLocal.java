/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.HashMap;
import javax.ejb.Local;

/**
 *
 * @author Giovanni
 */
@Local
public interface CaricaOrdineBeanLocal {
    HashMap<Integer,Integer> getCarrello(int codice);
    int getModPagamento(int codice);
    String getNomeVideogioco(int codice);
    double getPrezzoVideogioco(int codice);
}
