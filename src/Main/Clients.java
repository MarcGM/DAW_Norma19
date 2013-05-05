/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Marc
 */
@Entity
@Table(name = "clients", catalog = "daw_m4_uf6_pt1", schema = "")
@NamedQueries({
    @NamedQuery(name = "Clients.findAll", query = "SELECT c FROM Clients c"),
    @NamedQuery(name = "Clients.findByIdClient", query = "SELECT c FROM Clients c WHERE c.idClient = :idClient"),
    @NamedQuery(name = "Clients.findByNom", query = "SELECT c FROM Clients c WHERE c.nom = :nom"),
    @NamedQuery(name = "Clients.findByCognoms", query = "SELECT c FROM Clients c WHERE c.cognoms = :cognoms"),
    @NamedQuery(name = "Clients.findByNif", query = "SELECT c FROM Clients c WHERE c.nif = :nif"),
    //@NamedQuery(name = "Clients.findByCcc", query = "SELECT c FROM Clients c WHERE c.ccc = :ccc"),
    @NamedQuery(name = "Clients.findByTelefon", query = "SELECT c FROM Clients c WHERE c.telefon = :telefon"),
    @NamedQuery(name = "Clients.findByEmail", query = "SELECT c FROM Clients c WHERE c.email = :email"),
    @NamedQuery(name = "Clients.findByDonatAlta", query = "SELECT c FROM Clients c WHERE c.donatAlta = :donatAlta")})
public class Clients implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idClient")
    private Integer idClient;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "cognoms")
    private String cognoms;
    @Basic(optional = false)
    @Column(name = "NIF")
    private String nif;
    @Basic(optional = false)
    //@Column(name = "CCC")
    //private String ccc;
    //@Basic(optional = false)
    @Column(name = "telefon")
    private String telefon;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "donatAlta")
    private boolean donatAlta;

    public Clients() {
    }

    public Clients(Integer idClient) {
        this.idClient = idClient;
    }

    public Clients(Integer idClient, String nom, String cognoms, String nif,/* String ccc,*/ String telefon, String email, boolean donatAlta) {
        this.idClient = idClient;
        this.nom = nom;
        this.cognoms = cognoms;
        this.nif = nif;
        //this.ccc = ccc;
        this.telefon = telefon;
        this.email = email;
        this.donatAlta = donatAlta;
    }

    public Integer getIdClient() {
        return idClient;
    }

    public void setIdClient(Integer idClient) {
        Integer oldIdClient = this.idClient;
        this.idClient = idClient;
        changeSupport.firePropertyChange("idClient", oldIdClient, idClient);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String oldNom = this.nom;
        this.nom = nom;
        changeSupport.firePropertyChange("nom", oldNom, nom);
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        String oldCognoms = this.cognoms;
        this.cognoms = cognoms;
        changeSupport.firePropertyChange("cognoms", oldCognoms, cognoms);
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        String oldNif = this.nif;
        this.nif = nif;
        changeSupport.firePropertyChange("nif", oldNif, nif);
    }

    /*public String getCcc() {
        return ccc;
    }*/

    /*public void setCcc(String ccc) {
        String oldCcc = this.ccc;
        this.ccc = ccc;
        changeSupport.firePropertyChange("ccc", oldCcc, ccc);
    }*/

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        String oldTelefon = this.telefon;
        this.telefon = telefon;
        changeSupport.firePropertyChange("telefon", oldTelefon, telefon);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        String oldEmail = this.email;
        this.email = email;
        changeSupport.firePropertyChange("email", oldEmail, email);
    }

    public boolean getDonatAlta() {
        return donatAlta;
    }

    public void setDonatAlta(boolean donatAlta) {
        boolean oldDonatAlta = this.donatAlta;
        this.donatAlta = donatAlta;
        changeSupport.firePropertyChange("donatAlta", oldDonatAlta, donatAlta);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idClient != null ? idClient.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Clients)) {
            return false;
        }
        Clients other = (Clients) object;
        if ((this.idClient == null && other.idClient != null) || (this.idClient != null && !this.idClient.equals(other.idClient))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Clients[ idClient=" + idClient + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
