/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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
    @NamedQuery(name = "Amministratore.findAll", query = "SELECT a FROM Amministratore a"),
    @NamedQuery(name = "Amministratore.findByCf", query = "SELECT a FROM Amministratore a WHERE a.cf = :cf"),
    @NamedQuery(name = "Amministratore.findByEmail", query = "SELECT a FROM Amministratore a WHERE a.email = :email"),
    @NamedQuery(name = "Amministratore.findByPassword", query = "SELECT a FROM Amministratore a WHERE a.password = :password")})
public class Amministratore implements Serializable {
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

    public Amministratore() {
    }

    public Amministratore(String cf) {
        this.cf = cf;
    }

    public Amministratore(String cf, String email, String password) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cf != null ? cf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Amministratore)) {
            return false;
        }
        Amministratore other = (Amministratore) object;
        if ((this.cf == null && other.cf != null) || (this.cf != null && !this.cf.equals(other.cf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Amministratore[ cf=" + cf + " ]";
    }
    
}
