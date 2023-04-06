/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
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
    @NamedQuery(name = "Videogioco.findAll", query = "SELECT v FROM Videogioco v"),
    @NamedQuery(name = "Videogioco.findById", query = "SELECT v FROM Videogioco v WHERE v.id = :id"),
    @NamedQuery(name = "Videogioco.findByNome", query = "SELECT v FROM Videogioco v WHERE v.nome = :nome"),
    @NamedQuery(name = "Videogioco.findByDescrizione", query = "SELECT v FROM Videogioco v WHERE v.descrizione = :descrizione"),
    @NamedQuery(name = "Videogioco.findByPrezzoVendita", query = "SELECT v FROM Videogioco v WHERE v.prezzoVendita = :prezzoVendita"),
    @NamedQuery(name = "Videogioco.findByQtaDisp", query = "SELECT v FROM Videogioco v WHERE v.qtaDisp = :qtaDisp"),
    @NamedQuery(name = "Videogioco.findByPercorso", query = "SELECT v FROM Videogioco v WHERE v.percorso = :percorso")})
public class Videogioco implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String nome;
    @Size(max = 150)
    @Column(length = 150)
    private String descrizione;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prezzo_vendita", precision = 22)
    private Double prezzoVendita;
    @Column(name = "qta_disp")
    private Integer qtaDisp;
    @Size(max = 120)
    @Column(length = 120)
    private String percorso;
    @JoinColumn(name = "categoria", referencedColumnName = "nome")
    @ManyToOne
    private Categoria categoria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videogioco")
    private Collection<Carrello> carrelloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "videogioco")
    private Collection<RigaOrdine> rigaOrdineCollection;

    public Videogioco() {
    }

    public Videogioco(Integer id) {
        this.id = id;
    }

    public Videogioco(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzoVendita() {
        return prezzoVendita;
    }

    public void setPrezzoVendita(Double prezzoVendita) {
        this.prezzoVendita = prezzoVendita;
    }

    public Integer getQtaDisp() {
        return qtaDisp;
    }

    public void setQtaDisp(Integer qtaDisp) {
        this.qtaDisp = qtaDisp;
    }

    public String getPercorso() {
        return percorso;
    }

    public void setPercorso(String percorso) {
        this.percorso = percorso;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @XmlTransient
    public Collection<Carrello> getCarrelloCollection() {
        return carrelloCollection;
    }

    public void setCarrelloCollection(Collection<Carrello> carrelloCollection) {
        this.carrelloCollection = carrelloCollection;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Videogioco)) {
            return false;
        }
        Videogioco other = (Videogioco) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Videogioco[ id=" + id + " ]";
    }
    
}
