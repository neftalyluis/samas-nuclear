/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Modelo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String nombre;
    @OneToMany(mappedBy = "estrategia", cascade = CascadeType.ALL)
    private List<VectorModelo> estrategiaModelo;
    //Eversiones respecto a desbalances
    private Boolean liquidez;
    private Boolean efectivo;
    private Boolean grupos;
    private Boolean margen;
    private Double concentracionPorExposicion;
    @ManyToMany
    private List<Grupo> grupoLista;
    private Double liquidezCantidad;
//    @ManyToOne
//    private PerfilRiesgo perfilRiesgo;
    public Modelo() {

    }

    /**
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param modelo Guardara el Modelo (Lista de VectoresPortafolioModelo) que
     * se ingrese desde la interfaz.
     */
    public Modelo(String nombre, List<VectorModelo> modelo) {
        this.nombre = nombre;
        this.estrategiaModelo = modelo;

    }

    /**
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     */
    public Modelo(String nombre) {
        this.nombre = nombre;

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
        if (!(object instanceof Modelo)) {
            return false;
        }
        Modelo other = (Modelo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Estrategia[ id=" + id + " ]";
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
     * @return the estrategiaModelo
     */
    public List<VectorModelo> getEstrategiaModelo() {
        return estrategiaModelo;
    }

    /**
     * @param estrategiaModelo the estrategiaModelo to set
     */
    public void setEstrategiaModelo(List<VectorModelo> estrategiaModelo) {
        this.estrategiaModelo = estrategiaModelo;
    }

    /**
     * @return the liquidez
     */
    public Boolean getLiquidez() {
        return liquidez;
    }

    /**
     * @param liquidez the liquidez to set
     */
    public void setLiquidez(Boolean liquidez) {
        this.liquidez = liquidez;
    }

    /**
     * @return the efectivo
     */
    public Boolean getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(Boolean efectivo) {
        this.efectivo = efectivo;
    }

    /**
     * @return the grupos
     */
    public Boolean getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(Boolean grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the margen
     */
    public Boolean getMargen() {
        return margen;
    }

    /**
     * @param margen the margen to set
     */
    public void setMargen(Boolean margen) {
        this.margen = margen;
    }

    /**
     * @return the grupoLista
     */
    public List<Grupo> getGrupoLista() {
        return grupoLista;
    }

    /**
     * @param grupoLista the grupoLista to set
     */
    public void setGrupoLista(List<Grupo> grupoLista) {
        this.grupoLista = grupoLista;
    }

    /**
     * @return the concentracionPorExposicion
     */
    public Double getConcentracionPorExposicion() {
        return concentracionPorExposicion;
    }

    /**
     * @param concentracionPorExposicion the concentracionPorExposicion to set
     */
    public void setConcentracionPorExposicion(Double concentracionPorExposicion) {
        this.concentracionPorExposicion = concentracionPorExposicion;
    }

    /**
     * @return the liquidezCantidad
     */
    public Double getLiquidezCantidad() {
        return liquidezCantidad;
    }

    /**
     * @param liquidezCantidad the liquidezCantidad to set
     */
    public void setLiquidezCantidad(Double liquidezCantidad) {
        this.liquidezCantidad = liquidezCantidad;
    }

}