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
import javax.persistence.Id;
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
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByCf", query = "SELECT c FROM Cliente c WHERE c.cf = :cf"),
    @NamedQuery(name = "Cliente.findByEmail", query = "SELECT c FROM Cliente c WHERE c.email = :email"),
    @NamedQuery(name = "Cliente.findByPassword", query = "SELECT c FROM Cliente c WHERE c.password = :password"),
    @NamedQuery(name = "Cliente.findByNome", query = "SELECT c FROM Cliente c WHERE c.nome = :nome"),
    @NamedQuery(name = "Cliente.findByCognome", query = "SELECT c FROM Cliente c WHERE c.cognome = :cognome")})
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 16)
    @Column(nullable = false, length = 16)
    private String cf;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(nullable = false, length = 45)
    private String password;
    @Size(max = 45)
    @Column(length = 45)
    private String nome;
    @Size(max = 45)
    @Column(length = 45)
    private String cognome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "intestatario")
    private Collection<Ordine> ordineCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Carrello> carrelloCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Indirizzo> indirizzoCollection;

    public Cliente() {
    }

    public Cliente(String cf) {
        this.cf = cf;
    }

    public Cliente(String cf, String email, String password) {
        this.cf = cf;
        this.email = email;
        this.password = password;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    @XmlTransient
    public Collection<Ordine> getOrdineCollection() {
        return ordineCollection;
    }

    public void setOrdineCollection(Collection<Ordine> ordineCollection) {
        this.ordineCollection = ordineCollection;
    }

    @XmlTransient
    public Collection<Carrello> getCarrelloCollection() {
        return carrelloCollection;
    }

    public void setCarrelloCollection(Collection<Carrello> carrelloCollection) {
        this.carrelloCollection = carrelloCollection;
    }

    @XmlTransient
    public Collection<Indirizzo> getIndirizzoCollection() {
        return indirizzoCollection;
    }

    public void setIndirizzoCollection(Collection<Indirizzo> indirizzoCollection) {
        this.indirizzoCollection = indirizzoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cf != null ? cf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.cf == null && other.cf != null) || (this.cf != null && !this.cf.equals(other.cf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Cliente[ cf=" + cf + " ]";
    }
    
}
