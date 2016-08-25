/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 *
 * Objeto para crear una nueva Orden de Transacciones
 *
 * @author samas
 */
public class BitacoraOrdenDTO {

    @NotNull
    private String nombre;
    @NotNull
    private List<Long> transacciones;

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
     * @return the transacciones
     */
    public List<Long> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transacciones the transacciones to set
     */
    public void setTransacciones(List<Long> transacciones) {
        this.transacciones = transacciones;
    }
}
