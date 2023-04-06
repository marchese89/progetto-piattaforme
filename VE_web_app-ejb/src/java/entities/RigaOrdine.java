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
@Table(name = "riga_ordine", catalog = "videogiochi_everytime", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RigaOrdine.findAll", query = "SELECT r FROM RigaOrdine r"),
    @NamedQuery(name = "RigaOrdine.findByCodice", query = "SELECT r FROM RigaOrdine r WHERE r.rigaOrdinePK.codice = :codice"),
    @NamedQuery(name = "RigaOrdine.findByProdotto", query = "SELECT r FROM RigaOrdine r WHERE r.rigaOrdinePK.prodotto = :prodotto"),
    @NamedQuery(name = "RigaOrdine.findByQta", query = "SELECT r FROM RigaOrdine r WHERE r.qta = :qta")})
public class RigaOrdine implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RigaOrdinePK rigaOrdinePK;
    private Integer qta;
    @JoinColumn(name = "codice", referencedColumnName = "codice", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Ordine ordine;
    @JoinColumn(name = "prodotto", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Videogioco videogioco;

    public RigaOrdine() {
    }

    public RigaOrdine(RigaOrdinePK rigaOrdinePK) {
        this.rigaOrdinePK = rigaOrdinePK;
    }

    public RigaOrdine(int codice, int prodotto) {
        this.rigaOrdinePK = new RigaOrdinePK(codice, prodotto);
    }

    public RigaOrdinePK getRigaOrdinePK() {
        return rigaOrdinePK;
    }

    public void setRigaOrdinePK(RigaOrdinePK rigaOrdinePK) {
        this.rigaOrdinePK = rigaOrdinePK;
    }

    public Integer getQta() {
        return qta;
    }

    public void setQta(Integer qta) {
        this.qta = qta;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    public Videogioco getVideogioco() {
        return videogioco;
    }

    public void setVideogioco(Videogioco videogioco) {
        this.videogioco = videogioco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rigaOrdinePK != null ? rigaOrdinePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RigaOrdine)) {
            return false;
        }
        RigaOrdine other = (RigaOrdine) object;
        if ((this.rigaOrdinePK == null && other.rigaOrdinePK != null) || (this.rigaOrdinePK != null && !this.rigaOrdinePK.equals(other.rigaOrdinePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RigaOrdine[ rigaOrdinePK=" + rigaOrdinePK + " ]";
    }
    
}
