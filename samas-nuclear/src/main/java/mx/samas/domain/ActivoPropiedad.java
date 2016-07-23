/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author samas
 */
@Entity
public class ActivoPropiedad extends ParentModel {

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
}
