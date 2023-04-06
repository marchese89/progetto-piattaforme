/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import javax.ejb.Local;
import utility.VideogiocoPrint;

/**
 *
 * @author Antonio Giovanni
 */
@Local
public interface TrovaVideogiocoBeanLocal {
    VideogiocoPrint findById(String id);
}
