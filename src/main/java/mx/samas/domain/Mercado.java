/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author samas
 */
@Entity
public class Mercado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Activo monedaDenominacion;

    private Long fechaValorAccion;

    private Long fechaValorBono;

    private Long fechaValorDerivado;

    private Long fechaValorMoneda;

    @OneToOne
    private CalendarioComercial calendario;

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
        if (!(object instanceof Mercado)) {
            return false;
        }
        Mercado other = (Mercado) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Mercado[ id=" + id + " ]";
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the monedaDenominacion
     */
    public Activo getMonedaDenominacion() {
        return monedaDenominacion;
    }

    /**
     * @param monedaDenominacion the monedaDenominacion to set
     */
    public void setMonedaDenominacion(Activo monedaDenominacion) {
        this.monedaDenominacion = monedaDenominacion;
    }

    /**
     * @return the fechaValorAccion
     */
    public Long getFechaValorAccion() {
        return fechaValorAccion;
    }

    /**
     * @param fechaValorAccion the fechaValorAccion to set
     */
    public void setFechaValorAccion(Long fechaValorAccion) {
        this.fechaValorAccion = fechaValorAccion;
    }

    /**
     * @return the fechaValorBono
     */
    public Long getFechaValorBono() {
        return fechaValorBono;
    }

    /**
     * @param fechaValorBono the fechaValorBono to set
     */
    public void setFechaValorBono(Long fechaValorBono) {
        this.fechaValorBono = fechaValorBono;
    }

    /**
     * @return the fechaValorDerivado
     */
    public Long getFechaValorDerivado() {
        return fechaValorDerivado;
    }

    /**
     * @param fechaValorDerivado the fechaValorDerivado to set
     */
    public void setFechaValorDerivado(Long fechaValorDerivado) {
        this.fechaValorDerivado = fechaValorDerivado;
    }

    /**
     * @return the fechaValorMoneda
     */
    public Long getFechaValorMoneda() {
        return fechaValorMoneda;
    }

    /**
     * @param fechaValorMoneda the fechaValorMoneda to set
     */
    public void setFechaValorMoneda(Long fechaValorMoneda) {
        this.fechaValorMoneda = fechaValorMoneda;
    }

    /**
     * @return the calendario
     */
    public CalendarioComercial getCalendario() {
        return calendario;
    }

    /**
     * @param calendario the calendario to set
     */
    public void setCalendario(CalendarioComercial calendario) {
        this.calendario = calendario;
    }

}
