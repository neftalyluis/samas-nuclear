/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.projection;

import java.util.List;

/**
 *
 * @author samas
 */
public class BitacoraOrdenProjection {

    private Long id;
    private String usaActivo;
    private List<TransaccionProjection> transacciones;

    /**
     * @param id Variable de tipo long que almacenara el id de la Orden
     * @param usaActivo Variable de tipo cadena
     * @param transacciones Variable que almacenara una lista de transacciones.
     */
    public BitacoraOrdenProjection(Long id, String usaActivo, List<TransaccionProjection> transacciones) {
        this.id = id;
        this.usaActivo = usaActivo;
        this.transacciones = transacciones;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the usaActivo
     */
    public String getUsaActivo() {
        return usaActivo;
    }

    /**
     * @param usaActivo the usaActivo to set
     */
    public void setUsaActivo(String usaActivo) {
        this.usaActivo = usaActivo;
    }

    /**
     * @return the transacciones
     */
    public List<TransaccionProjection> getTransacciones() {
        return transacciones;
    }

    /**
     * @param transacciones the transacciones to set
     */
    public void setTransacciones(List<TransaccionProjection> transacciones) {
        this.transacciones = transacciones;
    }

}
