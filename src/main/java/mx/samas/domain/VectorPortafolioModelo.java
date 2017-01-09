/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class VectorPortafolioModelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate creado;

    @ManyToOne
    private Activo activo;

    @JsonIgnore
    @ManyToOne
    private Estrategia estrategia;

    private Double diana;

    /**Obtiene la fecha actual.
     * No hay parametros.
     */
    public VectorPortafolioModelo() {
        this.creado = LocalDate.now();

    }

    /**
     * @param a Guardara el Activo ingresado desde la interfaz.
     * @param diana Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz.
     */
    public VectorPortafolioModelo(Activo a, Double diana) {
        this.activo = a;
        this.diana = diana;
        this.creado = LocalDate.now();
    }

    /**
     * @param a Guardara el Activo ingresado desde la interfaz.
     * @param diana Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz.
     * @param e Guardara la Estrategia ingresada desde la interfaz.
     */
    public VectorPortafolioModelo(Activo a, Double diana, Estrategia e) {
        this.activo = a;
        this.diana = diana;
        this.estrategia = e;
        this.creado = LocalDate.now();

    }

    /**
     * @param d Guardara la fecha del dia actual.
     * @param a Guardara el Activo ingresado desde la interfaz.
     * @param e Guardara la Estrategia ingresada desde la interfaz.
     * @param diana Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz.
     */
    public VectorPortafolioModelo(LocalDate d, Activo a, Estrategia e, Double diana) {
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
        if (!(object instanceof VectorPortafolioModelo)) {
            return false;
        }
        VectorPortafolioModelo other = (VectorPortafolioModelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.PortafolioModelo[ id=" + id + " ]";
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
    public LocalDate getCreado() {
        return creado;
    }

    /**
     * @param creado the creado to set
     */
    public void setCreado(LocalDate creado) {
        this.creado = creado;
    }

}
