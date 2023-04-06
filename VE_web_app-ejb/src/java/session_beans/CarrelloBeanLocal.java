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
 * @author giovanni
 */
@Local
public interface CarrelloBeanLocal {
    void addVideogioco(int idV);
    void removeVideogioco(int idV);
    void modifyQta(int idV, int newQta);
    HashMap<Integer,Integer> getContenuto();
    void setModPagamento(int mod);
    int getModPagamento();
    void clear();
}
