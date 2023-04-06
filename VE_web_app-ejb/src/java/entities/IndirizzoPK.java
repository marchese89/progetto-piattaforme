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
public class IndirizzoPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(name = "cod_cliente", nullable = false, length = 16)
    private String codCliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String via;
    @Basic(optional = false)
    @NotNull
    @Column(name = "num_civico", nullable = false)
    private int numCivico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String comune;

    public IndirizzoPK() {
    }

    public IndirizzoPK(String codCliente, String via, int numCivico, String comune) {
        this.codCliente = codCliente;
        this.via = via;
        this.numCivico = numCivico;
        this.comune = comune;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getNumCivico() {
        return numCivico;
    }

    public void setNumCivico(int numCivico) {
        this.numCivico = numCivico;
    }

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codCliente != null ? codCliente.hashCode() : 0);
        hash += (via != null ? via.hashCode() : 0);
        hash += (int) numCivico;
        hash += (comune != null ? comune.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IndirizzoPK)) {
            return false;
        }
        IndirizzoPK other = (IndirizzoPK) object;
        if ((this.codCliente == null && other.codCliente != null) || (this.codCliente != null && !this.codCliente.equals(other.codCliente))) {
            return false;
        }
        if ((this.via == null && other.via != null) || (this.via != null && !this.via.equals(other.via))) {
            return false;
        }
        if (this.numCivico != other.numCivico) {
            return false;
        }
        if ((this.comune == null && other.comune != null) || (this.comune != null && !this.comune.equals(other.comune))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.IndirizzoPK[ codCliente=" + codCliente + ", via=" + via + ", numCivico=" + numCivico + ", comune=" + comune + " ]";
    }
    
}
