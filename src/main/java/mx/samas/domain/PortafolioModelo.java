/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author samas
 */
@Entity
public class PortafolioModelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date creado;

    @ManyToOne
    private Activo activo;

    @ManyToOne
    private Estrategia estrategia;

    private Double diana;

    public PortafolioModelo() {
        this.creado = new Date();

    }

    public PortafolioModelo(Activo a, Double diana) {
        this.activo = a;
        this.diana = diana;
        this.creado = new Date();
    }

    public PortafolioModelo(Activo a, Double diana, Estrategia e) {
        this.activo = a;
        this.diana = diana;
        this.estrategia = e;
        this.creado = new Date();

    }

    public PortafolioModelo(Date d, Activo a, Estrategia e, Double diana) {
        this.activo = a;
        this.diana = diana;
        this.estrategia = e;
        this.creado = d;

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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PortafolioModelo)) {
            return false;
        }
        PortafolioModelo other = (PortafolioModelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.newdomain.PortafolioModelo[ id=" + id + " ]";
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

    /**
     * @return the creado
     */
    public Date getCreado() {
        return creado;
    }

    /**
     * @param creado the creado to set
     */
    public void setCreado(Date creado) {
        this.creado = creado;
    }

}
