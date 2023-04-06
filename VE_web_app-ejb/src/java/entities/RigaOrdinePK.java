/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Antonio Giovanni
 */
@Embeddable
public class RigaOrdinePK implements Serializable {
    @Basic(optional = false)
    @Column(nullable = false)
    private int codice;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    private int prodotto;

    public RigaOrdinePK() {
    }

    public RigaOrdinePK(int codice, int prodotto) {
        this.codice = codice;
        this.prodotto = prodotto;
    }

    public int getCodice() {
        return codice;
    }

    public void setCodice(int codice) {
        this.codice = codice;
    }

    public int getProdotto() {
        return prodotto;
    }

    public void setProdotto(int prodotto) {
        this.prodotto = prodotto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codice;
        hash += (int) prodotto;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RigaOrdinePK)) {
            return false;
        }
        RigaOrdinePK other = (RigaOrdinePK) object;
        if (this.codice != other.codice) {
            return false;
        }
        if (this.prodotto != other.prodotto) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RigaOrdinePK[ codice=" + codice + ", prodotto=" + prodotto + " ]";
    }
    
}
