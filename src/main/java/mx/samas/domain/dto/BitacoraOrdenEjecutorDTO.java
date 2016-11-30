/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * Objeto que alberga la ejecucion de una Orden, con una Lista de Transacciones
 * y sus valores
 *
 * @author samas
 */
public class BitacoraOrdenEjecutorDTO {

    @NotNull
    private Long idOperacion;
    @NotNull
    private String folioOperacion;
    private String clavePizarra;
    @NotNull
    private String numeroContrato;

    @NotNull
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

    /**
     * @return the clavePizarra
     */
    public String getClavePizarra() {
        return clavePizarra;
    }

    /**
     * @param clavePizarra the clavePizarra to set
     */
    public void setClavePizarra(String clavePizarra) {
        this.clavePizarra = clavePizarra;
    }

    /**
     * @return the folioOperacion
     */
    public String getFolioOperacion() {
        return folioOperacion;
    }

    /**
     * @param folioOperacion the folioOperacion to set
     */
    public void setFolioOperacion(String folioOperacion) {
        this.folioOperacion = folioOperacion;
    }

    /**
     * @return the numeroContrato
     */
    public String getNumeroContrato() {
        return numeroContrato;
    }

    /**
     * @param numeroContrato the numeroContrato to set
     */
    public void setNumeroContrato(String numeroContrato) {
        this.numeroContrato = numeroContrato;
    }

}
