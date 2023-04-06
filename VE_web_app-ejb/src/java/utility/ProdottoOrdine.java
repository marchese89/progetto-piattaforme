/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

/**
 *
 * @author Giovanni
 */
public class ProdottoOrdine {
    
    private final int codice,idProdotto,qta;
    
    public ProdottoOrdine(int codice, int idProdotto,int qta){
        this.codice = codice;
        this.idProdotto = idProdotto;
        this.qta = qta;
    }
    
    public int getCodice(){
        return codice;
    }
    public int getProdotto(){
        return idProdotto;
    }
    public int getQta(){
        return qta;
    }
}
