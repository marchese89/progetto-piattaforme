/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Antonio Giovanni
 */
@Entity
@Table(catalog = "videogiochi_everytime", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ordine.findAll", query = "SELECT o FROM Ordine o"),
    @NamedQuery(name = "Ordine.findByCodice", query = "SELECT o FROM Ordine o WHERE o.codice = :codice"),
    @NamedQuery(name = "Ordine.findByData", query = "SELECT o FROM Ordine o WHERE o.data = :data"),
    @NamedQuery(name = "Ordine.findByPrezzoTotale", query = "SELECT o FROM Ordine o WHERE o.prezzoTotale = :prezzoTotale"),
    @NamedQuery(name = "Ordine.findByModPagamento", query = "SELECT o FROM Ordine o WHERE o.modPagamento = :modPagamento"),
    @NamedQuery(name = "Ordine.findByStato", query = "SELECT o FROM Ordine o WHERE o.stato = :stato")})
public class Ordine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer codice;
    @Basic(optional = false)
    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo_totale", precision = 22)
    private Double prezzoTotale;
    @Column(name = "mod_pagamento")
    private Integer modPagamento;
    @Size(max = 20)
    @Column(length = 20)
    private String stato;
    @JoinColumn(name = "intestatario", referencedColumnName = "CF", nullable = false)
    @ManyToOne(optional = false)
    private Cliente intestatario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ordine")
    private Collection<RigaOrdine> rigaOrdineCollection;

    public Ordine() {
    }

    public Ordine(Integer codice) {
        this.codice = codice;
    }

    public Ordine(Integer codice, Date data) {
        this.codice = codice;
        this.data = data;
    }

    public Integer getCodice() {
        return codice;
    }

    public void setCodice(Integer codice) {
        this.codice = codice;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public Integer getModPagamento() {
        return modPagamento;
    }

    public void setModPagamento(Integer modPagamento) {
        this.modPagamento = modPagamento;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public Cliente getIntestatario() {
        return intestatario;
    }

    public void setIntestatario(Cliente intestatario) {
        this.intestatario = intestatario;
    }

    @XmlTransient
    public Collection<RigaOrdine> getRigaOrdineCollection() {
        return rigaOrdineCollection;
    }

    public void setRigaOrdineCollection(Collection<RigaOrdine> rigaOrdineCollection) {
        this.rigaOrdineCollection = rigaOrdineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codice != null ? codice.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ordine)) {
            return false;
        }
        Ordine other = (Ordine) object;
        if ((this.codice == null && other.codice != null) || (this.codice != null && !this.codice.equals(other.codice))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ordine[ codice=" + codice + " ]";
    }
    
}
