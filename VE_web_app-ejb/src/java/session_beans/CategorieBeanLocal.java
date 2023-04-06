/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.LinkedList;
import javax.ejb.Local;

/**
 *
 * @author giovanni
 */
@Local
public interface CategorieBeanLocal {
    LinkedList<String> getCategorie();
    void rimuovi(String nomeCat);
}
