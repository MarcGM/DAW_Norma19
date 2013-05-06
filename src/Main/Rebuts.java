/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Marc
 */
@Entity
@Table(name = "rebuts", catalog = "daw_m4_uf6_pt1", schema = "")
@NamedQueries({
    @NamedQuery(name = "Rebuts.findAll", query = "SELECT r FROM Rebuts r"),
    @NamedQuery(name = "Rebuts.findByIdRebut", query = "SELECT r FROM Rebuts r WHERE r.idRebut = :idRebut"),
    @NamedQuery(name = "Rebuts.findByIdClienteOrdenante", query = "SELECT r FROM Rebuts r WHERE r.idClienteOrdenante = :idClienteOrdenante"),
    @NamedQuery(name = "Rebuts.findByFechaRecibo", query = "SELECT r FROM Rebuts r WHERE r.fechaRecibo = :fechaRecibo"),
    @NamedQuery(name = "Rebuts.findByCodigoDeReferencia", query = "SELECT r FROM Rebuts r WHERE r.codigoDeReferencia = :codigoDeReferencia"),
    @NamedQuery(name = "Rebuts.findByNombreTitularRecibo", query = "SELECT r FROM Rebuts r WHERE r.nombreTitularRecibo = :nombreTitularRecibo"),
    @NamedQuery(name = "Rebuts.findByCodigoBancoLibrado", query = "SELECT r FROM Rebuts r WHERE r.codigoBancoLibrado = :codigoBancoLibrado"),
    @NamedQuery(name = "Rebuts.findByCodigoSucursalLibrado", query = "SELECT r FROM Rebuts r WHERE r.codigoSucursalLibrado = :codigoSucursalLibrado"),
    @NamedQuery(name = "Rebuts.findByCodigoDCLibrado", query = "SELECT r FROM Rebuts r WHERE r.codigoDCLibrado = :codigoDCLibrado"),
    @NamedQuery(name = "Rebuts.findByNumeroCuentaLibrado", query = "SELECT r FROM Rebuts r WHERE r.numeroCuentaLibrado = :numeroCuentaLibrado"),
    @NamedQuery(name = "Rebuts.findByImporteRecibo", query = "SELECT r FROM Rebuts r WHERE r.importeRecibo = :importeRecibo"),
    @NamedQuery(name = "Rebuts.findByPrimerConcepto", query = "SELECT r FROM Rebuts r WHERE r.primerConcepto = :primerConcepto"),
    @NamedQuery(name = "Rebuts.findByRebutGenerat", query = "SELECT r FROM Rebuts r WHERE r.rebutGenerat = :rebutGenerat")})
public class Rebuts implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idRebut")
    private Integer idRebut;
    @Basic(optional = false)
    @Column(name = "idClienteOrdenante")
    private int idClienteOrdenante;
    @Basic(optional = false)
    @Column(name = "fechaRecibo")
    @Temporal(TemporalType.DATE)
    private Date fechaRecibo;
    @Basic(optional = false)
    @Column(name = "codigoDeReferencia")
    private String codigoDeReferencia;
    @Basic(optional = false)
    @Column(name = "nombreTitularRecibo")
    private String nombreTitularRecibo;
    @Basic(optional = false)
    @Column(name = "codigoBancoLibrado")
    private String codigoBancoLibrado;
    @Basic(optional = false)
    @Column(name = "codigoSucursalLibrado")
    private String codigoSucursalLibrado;
    @Basic(optional = false)
    @Column(name = "codigoDCLibrado")
    private String codigoDCLibrado;
    @Basic(optional = false)
    @Column(name = "numeroCuentaLibrado")
    private String numeroCuentaLibrado;
    @Basic(optional = false)
    @Column(name = "importeRecibo")
    private String importeRecibo;
    @Basic(optional = false)
    @Column(name = "primerConcepto")
    private String primerConcepto;
    @Basic(optional = false)
    @Column(name = "rebutGenerat")
    private boolean rebutGenerat;
    @JoinColumn(name = "idClienteOrdenante", referencedColumnName = "idClient", updatable = false, insertable = false)
    @ManyToOne
    private Clients idclienteordenante;

    public Rebuts() {
    }

    public Rebuts(Integer idRebut) {
        this.idRebut = idRebut;
    }

    public Rebuts(Integer idRebut, int idClienteOrdenante, Date fechaRecibo, String codigoDeReferencia, String nombreTitularRecibo, String codigoBancoLibrado, String codigoSucursalLibrado, String codigoDCLibrado, String numeroCuentaLibrado, String importeRecibo, String primerConcepto, boolean rebutGenerat) {
        this.idRebut = idRebut;
        this.idClienteOrdenante = idClienteOrdenante;
        this.fechaRecibo = fechaRecibo;
        this.codigoDeReferencia = codigoDeReferencia;
        this.nombreTitularRecibo = nombreTitularRecibo;
        this.codigoBancoLibrado = codigoBancoLibrado;
        this.codigoSucursalLibrado = codigoSucursalLibrado;
        this.codigoDCLibrado = codigoDCLibrado;
        this.numeroCuentaLibrado = numeroCuentaLibrado;
        this.importeRecibo = importeRecibo;
        this.primerConcepto = primerConcepto;
        this.rebutGenerat = rebutGenerat;
    }

    public Integer getIdRebut() {
        return idRebut;
    }

    public void setIdRebut(Integer idRebut) {
        Integer oldIdRebut = this.idRebut;
        this.idRebut = idRebut;
        changeSupport.firePropertyChange("idRebut", oldIdRebut, idRebut);
    }

    public int getIdClienteOrdenante() {
        return idClienteOrdenante;
    }

    public void setIdClienteOrdenante(int idClienteOrdenante) {
        int oldIdClienteOrdenante = this.idClienteOrdenante;
        this.idClienteOrdenante = idClienteOrdenante;
        changeSupport.firePropertyChange("idClienteOrdenante", oldIdClienteOrdenante, idClienteOrdenante);
    }

    public Date getFechaRecibo() {
        return fechaRecibo;
    }

    public void setFechaRecibo(Date fechaRecibo) {
        Date oldFechaRecibo = this.fechaRecibo;
        this.fechaRecibo = fechaRecibo;
        changeSupport.firePropertyChange("fechaRecibo", oldFechaRecibo, fechaRecibo);
    }

    public String getCodigoDeReferencia() {
        return codigoDeReferencia;
    }

    public void setCodigoDeReferencia(String codigoDeReferencia) {
        String oldCodigoDeReferencia = this.codigoDeReferencia;
        this.codigoDeReferencia = codigoDeReferencia;
        changeSupport.firePropertyChange("codigoDeReferencia", oldCodigoDeReferencia, codigoDeReferencia);
    }

    public String getNombreTitularRecibo() {
        return nombreTitularRecibo;
    }

    public void setNombreTitularRecibo(String nombreTitularRecibo) {
        String oldNombreTitularRecibo = this.nombreTitularRecibo;
        this.nombreTitularRecibo = nombreTitularRecibo;
        changeSupport.firePropertyChange("nombreTitularRecibo", oldNombreTitularRecibo, nombreTitularRecibo);
    }

    public String getCodigoBancoLibrado() {
        return codigoBancoLibrado;
    }

    public void setCodigoBancoLibrado(String codigoBancoLibrado) {
        String oldCodigoBancoLibrado = this.codigoBancoLibrado;
        this.codigoBancoLibrado = codigoBancoLibrado;
        changeSupport.firePropertyChange("codigoBancoLibrado", oldCodigoBancoLibrado, codigoBancoLibrado);
    }

    public String getCodigoSucursalLibrado() {
        return codigoSucursalLibrado;
    }

    public void setCodigoSucursalLibrado(String codigoSucursalLibrado) {
        String oldCodigoSucursalLibrado = this.codigoSucursalLibrado;
        this.codigoSucursalLibrado = codigoSucursalLibrado;
        changeSupport.firePropertyChange("codigoSucursalLibrado", oldCodigoSucursalLibrado, codigoSucursalLibrado);
    }

    public String getCodigoDCLibrado() {
        return codigoDCLibrado;
    }

    public void setCodigoDCLibrado(String codigoDCLibrado) {
        String oldCodigoDCLibrado = this.codigoDCLibrado;
        this.codigoDCLibrado = codigoDCLibrado;
        changeSupport.firePropertyChange("codigoDCLibrado", oldCodigoDCLibrado, codigoDCLibrado);
    }

    public String getNumeroCuentaLibrado() {
        return numeroCuentaLibrado;
    }

    public void setNumeroCuentaLibrado(String numeroCuentaLibrado) {
        String oldNumeroCuentaLibrado = this.numeroCuentaLibrado;
        this.numeroCuentaLibrado = numeroCuentaLibrado;
        changeSupport.firePropertyChange("numeroCuentaLibrado", oldNumeroCuentaLibrado, numeroCuentaLibrado);
    }

    public String getImporteRecibo() {
        return importeRecibo;
    }

    public void setImporteRecibo(String importeRecibo) {
        String oldImporteRecibo = this.importeRecibo;
        this.importeRecibo = importeRecibo;
        changeSupport.firePropertyChange("importeRecibo", oldImporteRecibo, importeRecibo);
    }

    public String getPrimerConcepto() {
        return primerConcepto;
    }

    public void setPrimerConcepto(String primerConcepto) {
        String oldPrimerConcepto = this.primerConcepto;
        this.primerConcepto = primerConcepto;
        changeSupport.firePropertyChange("primerConcepto", oldPrimerConcepto, primerConcepto);
    }

    public boolean getRebutGenerat() {
        return rebutGenerat;
    }

    public void setRebutGenerat(boolean rebutGenerat) {
        boolean oldRebutGenerat = this.rebutGenerat;
        this.rebutGenerat = rebutGenerat;
        changeSupport.firePropertyChange("rebutGenerat", oldRebutGenerat, rebutGenerat);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRebut != null ? idRebut.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rebuts)) {
            return false;
        }
        Rebuts other = (Rebuts) object;
        if ((this.idRebut == null && other.idRebut != null) || (this.idRebut != null && !this.idRebut.equals(other.idRebut))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Main.Rebuts[ idRebut=" + idRebut + " ]";
    }

    public Clients getIdclienteordenante() {
        return idclienteordenante;
    }

    public void setIdclienteordenante(Clients idclienteordenante) {
        Clients oldIdclienteordenante = this.idclienteordenante;
        this.idclienteordenante = idclienteordenante;
        changeSupport.firePropertyChange("idclienteordenante", oldIdclienteordenante, idclienteordenante);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
