/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

/**
 *
 * @author samas
 */
public class CuentaDTO {

    private Long id;
    private String idCuenta;
    private Long bancoId;
    private Boolean tieneCredito;
    private Boolean operaFlujo;
    private Boolean operaIndice;
    private Boolean operaDerivado;

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
     * @return the idCuenta
     */
    public String getIdCuenta() {
        return idCuenta;
    }

    /**
     * @param idCuenta the idCuenta to set
     */
    public void setIdCuenta(String idCuenta) {
        this.idCuenta = idCuenta;
    }

    /**
     * @return the bancoId
     */
    public Long getBancoId() {
        return bancoId;
    }

    /**
     * @param bancoId the bancoId to set
     */
    public void setBancoId(Long bancoId) {
        this.bancoId = bancoId;
    }

    /**
     * @return the tieneCredito
     */
    public Boolean getTieneCredito() {
        return tieneCredito;
    }

    /**
     * @param tieneCredito the tieneCredito to set
     */
    public void setTieneCredito(Boolean tieneCredito) {
        this.tieneCredito = tieneCredito;
    }

    /**
     * @return the operaFlujo
     */
    public Boolean getOperaFlujo() {
        return operaFlujo;
    }

    /**
     * @param operaFlujo the operaFlujo to set
     */
    public void setOperaFlujo(Boolean operaFlujo) {
        this.operaFlujo = operaFlujo;
    }

    /**
     * @return the operaIndice
     */
    public Boolean getOperaIndice() {
        return operaIndice;
    }

    /**
     * @param operaIndice the operaIndice to set
     */
    public void setOperaIndice(Boolean operaIndice) {
        this.operaIndice = operaIndice;
    }

    /**
     * @return the operaDerivado
     */
    public Boolean getOperaDerivado() {
        return operaDerivado;
    }

    /**
     * @param operaDerivado the operaDerivado to set
     */
    public void setOperaDerivado(Boolean operaDerivado) {
        this.operaDerivado = operaDerivado;
    }
}
