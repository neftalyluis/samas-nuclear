/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Prueba.buscarTodos", query="SELECT t FROM Prueba t")
})
@XmlRootElement
public class Prueba implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    public Prueba(){
        
    }
    
    public Prueba(String nombrePrueba, Date fechaInicial, Date fechaFinal) {
        this.nombrePrueba = nombrePrueba;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
    }
    
    private String nombrePrueba;
    
    @OneToMany
    private List<PortafolioCuenta> cuentas;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaInicial;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaFinal;
    
    @OneToMany
    private List<VectorPrueba> resultados;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Prueba)) {
            return false;
        }
        Prueba other = (Prueba) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.Test[ id=" + id + " ]";
    }

    /**
     * @return the nombrePrueba
     */
    public String getNombrePrueba() {
        return nombrePrueba;
    }

    /**
     * @param nombrePrueba the nombrePrueba to set
     */
    public void setNombrePrueba(String nombrePrueba) {
        this.nombrePrueba = nombrePrueba;
    }

    /**
     * @return the cuentas
     */
    public List<PortafolioCuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(List<PortafolioCuenta> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * @return the fechaInicial
     */
    public Date getFechaInicial() {
        return fechaInicial;
    }

    /**
     * @param fechaInicial the fechaInicial to set
     */
    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    /**
     * @return the fechaFinal
     */
    public Date getFechaFinal() {
        return fechaFinal;
    }

    /**
     * @param fechaFinal the fechaFinal to set
     */
    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    /**
     * @return the resultados
     */
    public List<VectorPrueba> getResultados() {
        return resultados;
    }

    /**
     * @param resultados the resultados to set
     */
    public void setResultados(List<VectorPrueba> resultados) {
        this.resultados = resultados;
    }

}
