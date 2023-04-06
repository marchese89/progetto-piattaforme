/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Antonio Giovanni
 */
@Entity
@Table(catalog = "videogiochi_everytime", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Indirizzo.findAll", query = "SELECT i FROM Indirizzo i"),
    @NamedQuery(name = "Indirizzo.findByCodCliente", query = "SELECT i FROM Indirizzo i WHERE i.indirizzoPK.codCliente = :codCliente"),
    @NamedQuery(name = "Indirizzo.findByVia", query = "SELECT i FROM Indirizzo i WHERE i.indirizzoPK.via = :via"),
    @NamedQuery(name = "Indirizzo.findByNumCivico", query = "SELECT i FROM Indirizzo i WHERE i.indirizzoPK.numCivico = :numCivico"),
    @NamedQuery(name = "Indirizzo.findByCap", query = "SELECT i FROM Indirizzo i WHERE i.cap = :cap"),
    @NamedQuery(name = "Indirizzo.findByComune", query = "SELECT i FROM Indirizzo i WHERE i.indirizzoPK.comune = :comune"),
    @NamedQuery(name = "Indirizzo.findByProvincia", query = "SELECT i FROM Indirizzo i WHERE i.provincia = :provincia")})
public class Indirizzo implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected IndirizzoPK indirizzoPK;
    private Integer cap;
    @Size(max = 2)
    @Column(length = 2)
    private String provincia;
    @JoinColumn(name = "cod_cliente", referencedColumnName = "CF", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Indirizzo() {
    }

    public Indirizzo(IndirizzoPK indirizzoPK) {
        this.indirizzoPK = indirizzoPK;
    }

    public Indirizzo(String codCliente, String via, int numCivico, String comune) {
        this.indirizzoPK = new IndirizzoPK(codCliente, via, numCivico, comune);
    }

    public IndirizzoPK getIndirizzoPK() {
        return indirizzoPK;
    }

    public void setIndirizzoPK(IndirizzoPK indirizzoPK) {
        this.indirizzoPK = indirizzoPK;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (indirizzoPK != null ? indirizzoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Indirizzo)) {
            return false;
        }
        Indirizzo other = (Indirizzo) object;
        if ((this.indirizzoPK == null && other.indirizzoPK != null) || (this.indirizzoPK != null && !this.indirizzoPK.equals(other.indirizzoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Indirizzo[ indirizzoPK=" + indirizzoPK + " ]";
    }
    
}
