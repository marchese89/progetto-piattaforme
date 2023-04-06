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
import javax.validation.constraints.Size;

/**
 *
 * @author Antonio Giovanni
 */
@Embeddable
public class CarrelloPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_prodotto", nullable = false)
    private int idProdotto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "id_utente", nullable = false, length = 16)
    private String idUtente;

    public CarrelloPK() {
    }

    public CarrelloPK(int idProdotto, String idUtente) {
        this.idProdotto = idProdotto;
        this.idUtente = idUtente;
    }

    public int getIdProdotto() {
        return idProdotto;
    }

    public void setIdProdotto(int idProdotto) {
        this.idProdotto = idProdotto;
    }

    public String getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(String idUtente) {
        this.idUtente = idUtente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idProdotto;
        hash += (idUtente != null ? idUtente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CarrelloPK)) {
            return false;
        }
        CarrelloPK other = (CarrelloPK) object;
        if (this.idProdotto != other.idProdotto) {
            return false;
        }
        if ((this.idUtente == null && other.idUtente != null) || (this.idUtente != null && !this.idUtente.equals(other.idUtente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CarrelloPK[ idProdotto=" + idProdotto + ", idUtente=" + idUtente + " ]";
    }
    
}
