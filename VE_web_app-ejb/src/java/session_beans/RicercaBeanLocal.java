/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.HashSet;
import javax.ejb.Local;
import utility.VideogiocoPrint;

/**
 *
 * @author Antonio Giovanni
 */
@Local
public interface RicercaBeanLocal {
    HashSet<VideogiocoPrint> getResultList(String cerca);
}
