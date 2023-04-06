/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.util.Date;
import java.util.StringTokenizer;

/**
 *
 * @author Giovanni
 */
public class OrdinePrint {

    private final int codiceOrdine;
    private final String cliente;
    private final String dataOrdine;
    private final double totale;
    private final int modPagamento;
    private final String stato;

    public OrdinePrint(int codiceOrdine, String cliente, Date data, double totale, int modPagamento, String stato) {
        this.codiceOrdine = codiceOrdine;
        this.cliente = cliente;
        String dattt = data.toString();
        StringTokenizer st = new StringTokenizer(dattt, " ");
        st.nextToken();
        String mese = st.nextToken();
        String giorno = st.nextToken();
        st.nextToken();
        st.nextToken();
        String anno = st.nextToken();
        StringBuilder sb = new StringBuilder();
        sb.append(giorno);
        sb.append("-");
        sb.append(mese);
        sb.append("-");
        sb.append(anno);
        this.dataOrdine = sb.toString();
        this.totale = totale;
        this.modPagamento = modPagamento;
        this.stato = stato;
    }

    /**
     * @return the codiceOrdine
     */
    public int getCodiceOrdine() {
        return codiceOrdine;
    }

    /**
     * @return the cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * @return the dataOrdine
     */
    public String getDataOrdine() {
        return dataOrdine;
    }

    /**
     * @return the totale
     */
    public double getTotale() {
        return totale;
    }

    /**
     * @return the modPagamento
     */
    public int getModPagamento() {
        return modPagamento;
    }

    /**
     * @return the stato
     */
    public String getStato() {
        return stato;
    }

}
