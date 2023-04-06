/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session_beans;

import java.util.LinkedList;
import javax.ejb.Local;
import utility.VideogiocoPrint;


/**
 *
 * @author Giovanni
 */
@Local
public interface ProdottiCatBeanLocal {
    LinkedList<VideogiocoPrint> elencoProdottiCat(String categoria,int dimPagina,int pagina);
    LinkedList<VideogiocoPrint> elencoProdottiAlpha(String alpha,int dimPagina, int pagina);
    LinkedList<VideogiocoPrint> elencoCompleto(int dimPagina, int pagina);
    int prodottiCatDim(String categoria);
    int prodottiAlphaDim(String alpha);
    int elencoCompletoDim();
}
