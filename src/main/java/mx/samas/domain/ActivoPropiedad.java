/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author samas
 */
@Entity
public class ActivoPropiedad implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * Nombre de la propiedad
     */
    private String nombre;
    /**
     * Descripcion de la propiedad
     */
    private String descripcion;

    /**
     * El tipo de dato que va a manejar esta propiedad: - Boolean - String -
     * Integer - Double - Date
     */
    @Enumerated(EnumType.ORDINAL)
    private TipoDato tipoDato;

    /**
     * En que indice del CSV se va a leer esta propiedad
     */
    private Integer indice;

    /**
     * En que archivo se van a leer estas propiedades
     */
    @Enumerated(EnumType.ORDINAL)
    private FuenteDatos origenDatos;

    /**
     * A que tipo de Activo 
     */
    @Enumerated(EnumType.ORDINAL)
    private TipoActivo tipoActivo;
    
    private Boolean vectorial;

    public ActivoPropiedad() {

    }

    public ActivoPropiedad(String nombre, String descripcion, TipoDato tipoDato, Integer indice, FuenteDatos origenDatos, TipoActivo tipoActivo, Boolean vectorial) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipoDato = tipoDato;
        this.indice = indice;
        this.origenDatos = origenDatos;
        this.tipoActivo = tipoActivo;
        this.vectorial = vectorial;
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
        if (!(object instanceof ActivoPropiedad)) {
            return false;
        }
        ActivoPropiedad other = (ActivoPropiedad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.ActivoPropiedad[ id=" + id + " ]";
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipoDato
     */
    public TipoDato getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }

    /**
     * @return the origenDatos
     */
    public FuenteDatos getOrigenDatos() {
        return origenDatos;
    }

    /**
     * @param origenDatos the origenDatos to set
     */
    public void setOrigenDatos(FuenteDatos origenDatos) {
        this.origenDatos = origenDatos;
    }

    /**
     * @return the indice
     */
    public Integer getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(Integer indice) {
        this.indice = indice;
    }

    /**
     * @return the tipoActivo
     */
    public TipoActivo getTipoActivo() {
        return tipoActivo;
    }

    /**
     * @param tipoActivo the tipoActivo to set
     */
    public void setTipoActivo(TipoActivo tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

    /**
     * @return the vectorial
     */
    public Boolean getVectorial() {
        return vectorial;
    }

    /**
     * @param vectorial the vectorial to set
     */
    public void setVectorial(Boolean vectorial) {
        this.vectorial = vectorial;
    }
}
