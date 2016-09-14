/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author samas
 */
@Entity
public class VectorActivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @ManyToOne
    private Activo activo;

    private Double precioLimpio;

    @OneToMany
    private List<ActivoPropiedadValor> propiedadesValor;
    

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
        if (!(object instanceof VectorActivo)) {
            return false;
        }
        VectorActivo other = (VectorActivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.newdomain.VectorActivo[ id=" + id + " ]";
    }

    /**
     * @return the activo
     */
    public Activo getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    /**
     * @return the precioLimpio
     */
    public Double getPrecioLimpio() {
        return precioLimpio;
    }

    /**
     * @param precioLimpio the precioLimpio to set
     */
    public void setPrecioLimpio(Double precioLimpio) {
        this.precioLimpio = precioLimpio;
    }

    /**
     * @return the propiedadesValor
     */
    public List<ActivoPropiedadValor> getPropiedadesValor() {
        return propiedadesValor;
    }

    /**
     * @param propiedadesValor the propiedadesValor to set
     */
    public void setPropiedadesValor(List<ActivoPropiedadValor> propiedadesValor) {
        this.propiedadesValor = propiedadesValor;
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
