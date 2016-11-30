/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.List;

/**
 *
 * @author samas
 */
public class PortafolioDTO {

    private Long id;
    private CuentaDTO cuentaEje;
    private Long estrategiaId;
    private Long tipoServicioId;
    private String monedaDenominacion;
    private List<Long> clientes;
    private List<CuentaDTO> cuentas;
    private Double margen;

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
     * @return the estrategiaId
     */
    public Long getEstrategiaId() {
        return estrategiaId;
    }

    /**
     * @param estrategiaId the estrategiaId to set
     */
    public void setEstrategiaId(Long estrategiaId) {
        this.estrategiaId = estrategiaId;
    }

    /**
     * @return the tipoServicioId
     */
    public Long getTipoServicioId() {
        return tipoServicioId;
    }

    /**
     * @param tipoServicioId the tipoServicioId to set
     */
    public void setTipoServicioId(Long tipoServicioId) {
        this.tipoServicioId = tipoServicioId;
    }

    /**
     * @return the monedaDenominacion
     */
    public String getMonedaDenominacion() {
        return monedaDenominacion;
    }

    /**
     * @param monedaDenominacion the monedaDenominacion to set
     */
    public void setMonedaDenominacion(String monedaDenominacion) {
        this.monedaDenominacion = monedaDenominacion;
    }

    /**
     * @return the clientes
     */
    public List<Long> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<Long> clientes) {
        this.clientes = clientes;
    }

    /**
     * @return the cuentas
     */
    public List<CuentaDTO> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(List<CuentaDTO> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * @return the cuentaEje
     */
    public CuentaDTO getCuentaEje() {
        return cuentaEje;
    }

    /**
     * @param cuentaEje the cuentaEje to set
     */
    public void setCuentaEje(CuentaDTO cuentaEje) {
        this.cuentaEje = cuentaEje;
    }

    /**
     * @return the margen
     */
    public Double getMargen() {
        return margen;
    }

    /**
     * @param margen the margen to set
     */
    public void setMargen(Double margen) {
        this.margen = margen;
    }
}
