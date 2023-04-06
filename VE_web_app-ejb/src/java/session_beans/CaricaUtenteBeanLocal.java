/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.LinkedList;
import javax.ejb.Local;
import utility.IndirizzoPrint;
import utility.OrdinePrint;
import utility.UtentePrint;

/**
 *
 * @author Giovanni
 */
@Local
public interface CaricaUtenteBeanLocal {
    
    LinkedList<UtentePrint> getUtenti();
    LinkedList<OrdinePrint> getOrdiniUtente(String cfUser);
    IndirizzoPrint getRecapito(String cfUser);
    LinkedList<OrdinePrint> aggiornaStato(int idOrdine,String stato);
    LinkedList<OrdinePrint> getOrdiniNonEvasi(Integer codice);
    LinkedList<UtentePrint> getUtente(String cf);
    void modificaDatiUtente(UtentePrint utente);

    
}
