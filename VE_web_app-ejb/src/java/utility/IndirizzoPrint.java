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
public class IndirizzoPrint {

    private final String cf;
    private final String via;
    private final int numCivico;
    private final Integer cap;
    private final String comune;
    private final String provincia;
    public IndirizzoPrint(String cf, String via, int numCivico, Integer cap, String comune, String provincia) {
        this.cf = cf;
        this.via = via;
        this.numCivico = numCivico;
        this.cap = cap;
        this.comune = comune;
        this.provincia = provincia;
    }

    /**
     * @return the cf
     */
    public String getCf() {
        return cf;
    }

    /**
     * @return the via
     */
    public String getVia() {
        return via;
    }

    /**
     * @return the numCivico
     */
    public int getNumCivico() {
        return numCivico;
    }

    /**
     * @return the cap
     */
    public Integer getCap() {
        return cap;
    }

    /**
     * @return the comune
     */
    public String getComune() {
        return comune;
    }

    /**
     * @return the provincia
     */
    public String getProvincia() {
        return provincia;
    }
    
}
