/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.List;

/**
 * Objeto que alberga la ejecucion de una Orden, con una Lista de Transacciones
 * y sus valores
 *
 * @author samas
 */
public class BitacoraOrdenEjecutorDTO {

    private Long idOperacion;
    private List<BitacoraOrdenValorDTO> valorTransacciones;

    /**
     * @return the idOperacion
     */
    public Long getIdOperacion() {
        return idOperacion;
    }

    /**
     * @param idOperacion the idOperacion to set
     */
    public void setIdOperacion(Long idOperacion) {
        this.idOperacion = idOperacion;
    }

    /**
     * @return the valorTransacciones
     */
    public List<BitacoraOrdenValorDTO> getValorTransacciones() {
        return valorTransacciones;
    }

    /**
     * @param valorTransacciones the valorTransacciones to set
     */
    public void setValorTransacciones(List<BitacoraOrdenValorDTO> valorTransacciones) {
        this.valorTransacciones = valorTransacciones;
    }

}
