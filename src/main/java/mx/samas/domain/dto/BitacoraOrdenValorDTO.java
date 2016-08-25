/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

/**
 *
 * Objeto para describir elvalor de una Transaccion perteneciente a una Orden
 *
 * @author samas
 */
public class BitacoraOrdenValorDTO {

    private Long transaccionId;
    private Long titulos;
    private Long efectivo;

    /**
     * @return the transaccionId
     */
    public Long getTransaccionId() {
        return transaccionId;
    }

    /**
     * @param transaccionId the transaccionId to set
     */
    public void setTransaccionId(Long transaccionId) {
        this.transaccionId = transaccionId;
    }

    /**
     * @return the titulos
     */
    public Long getTitulos() {
        return titulos;
    }

    /**
     * @param titulos the titulos to set
     */
    public void setTitulos(Long titulos) {
        this.titulos = titulos;
    }

    /**
     * @return the efectivo
     */
    public Long getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(Long efectivo) {
        this.efectivo = efectivo;
    }

}
