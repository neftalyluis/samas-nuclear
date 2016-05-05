/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Clase raíz donde se van a guardar para cada activo las propiedades que
 * cambian con el tiempo
 */
@Entity
@XmlRootElement
public class VectorActivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;

    @ManyToOne
    private Activo activo;
    
    private Double precioLimpio;

// BONDVECTOR    
//    
//    private Double dirtyPrice;
//    private Double yield;
//    private Double couponRate;
//    /**
//     * "Spread" significa sobretasa versus tasa de referencia en bonos
//     * revisables; para bonos tasa fija es = 0.0 .
//     */
//    private Double spread;
//    private Double amountOutstanding;
//
//    /**
//     * Checar la migracion de calificaciones a Entidades, tucán
//     */
//    private String gradeMoodys;
//    private String gradeSP;
//    private String gradeHR;
//    private String gradeFitch;
//
//    @ManyToMany
//    private List<RatingGrade> ratings;
//
//    private Double faceValue;
    
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
        return "mx.samas.entities.PriceVector[ id=" + id + " ]";
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

}
