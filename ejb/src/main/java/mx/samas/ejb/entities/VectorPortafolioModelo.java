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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author alfonso
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SliceVector.getSlicesFromStrategy", query = "SELECT s FROM SliceVector s WHERE s.strategy.id = :id"),
    @NamedQuery(name = "SliceVector.getSliceWithIdAndStrategyId", query = "SELECT s FROM SliceVector s WHERE s.id= :sliceId AND s.strategy.id= :strategyId"),
    @NamedQuery(name = "SliceVector.getLastSlicesFromStrategy", query = "SELECT s FROM SliceVector s WHERE s.dateTime="
            + "(SELECT MAX(md.dateTime) FROM SliceVector md WHERE md.strategy.id= :strategyId) "
            + "AND s.strategy.id= :strategyId")
})
public class VectorPortafolioModelo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date fecha;

    @NotNull
    @ManyToOne
    private Activo activo;

    @ManyToOne
    private Fungibilidad fungibilidad;

    @NotNull
    @ManyToOne
    private Estrategia estrategia;

    @NotNull
    private Double diana;

    public VectorPortafolioModelo() {
        this.fecha = new Date();
    }

    public VectorPortafolioModelo(Activo a, Double target) {
        this.fecha = new Date();
        this.activo = a;
        this.diana = target;

    }

    public VectorPortafolioModelo(Date d, Activo a, Estrategia s, Double target) {
        this.activo = a;
        this.fecha = new Date();
        this.estrategia = s;
        this.diana = target;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VectorPortafolioModelo)) {
            return false;
        }
        VectorPortafolioModelo other = (VectorPortafolioModelo) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.SliceVector[ id=" + getId() + " ]";
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
     * @return the fungibilidad
     */
    public Fungibilidad getFungibilidad() {
        return fungibilidad;
    }

    /**
     * @param fungibilidad the fungibilidad to set
     */
    public void setFungibilidad(Fungibilidad fungibilidad) {
        this.fungibilidad = fungibilidad;
    }

    /**
     * @return the estrategia
     */
    public Estrategia getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * @return the diana
     */
    public Double getDiana() {
        return diana;
    }

    /**
     * @param diana the diana to set
     */
    public void setDiana(Double diana) {
        this.diana = diana;
    }


}
