/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Antonio Giovanni
 */
@Entity
@Table(catalog = "videogiochi_everytime", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Carrello.findAll", query = "SELECT c FROM Carrello c"),
    @NamedQuery(name = "Carrello.findByIdProdotto", query = "SELECT c FROM Carrello c WHERE c.carrelloPK.idProdotto = :idProdotto"),
    @NamedQuery(name = "Carrello.findByIdUtente", query = "SELECT c FROM Carrello c WHERE c.carrelloPK.idUtente = :idUtente"),
    @NamedQuery(name = "Carrello.findByQta", query = "SELECT c FROM Carrello c WHERE c.qta = :qta")})
public class Carrello implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected CarrelloPK carrelloPK;
    private Integer qta;
    @JoinColumn(name = "id_prodotto", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Videogioco videogioco;
    @JoinColumn(name = "id_utente", referencedColumnName = "CF", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Cliente cliente;

    public Carrello() {
    }

    public Carrello(CarrelloPK carrelloPK) {
        this.carrelloPK = carrelloPK;
    }

    public Carrello(int idProdotto, String idUtente) {
        this.carrelloPK = new CarrelloPK(idProdotto, idUtente);
    }

    public CarrelloPK getCarrelloPK() {
        return carrelloPK;
    }

    public void setCarrelloPK(CarrelloPK carrelloPK) {
        this.carrelloPK = carrelloPK;
    }

    public Integer getQta() {
        return qta;
    }

    public void setQta(Integer qta) {
        this.qta = qta;
    }

    public Videogioco getVideogioco() {
        return videogioco;
    }

    public void setVideogioco(Videogioco videogioco) {
        this.videogioco = videogioco;
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
        hash += (carrelloPK != null ? carrelloPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Carrello)) {
            return false;
        }
        Carrello other = (Carrello) object;
        if ((this.carrelloPK == null && other.carrelloPK != null) || (this.carrelloPK != null && !this.carrelloPK.equals(other.carrelloPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Carrello[ carrelloPK=" + carrelloPK + " ]";
    }
    
}
