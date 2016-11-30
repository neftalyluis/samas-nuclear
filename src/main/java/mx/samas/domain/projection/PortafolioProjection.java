/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.projection;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 *
 * @author samas
 */
public class PortafolioProjection {

    private String cuentaEje;
    private Map<String, String> cuentas;
    private String nombreEstrategia;
    private String nombreTipoServicio;
    private LocalDate fecha;
    private String monedaDenominacionTicker;
    private List<String> clientes;
    private Double margen;

    public PortafolioProjection(String cuentaEje, Map<String, String> cuentas, 
            String nombreEstrategia, String nombreTipoServicio, LocalDate fecha, 
            String monedaDenominacionTicker, List<String> clientes, Double margen) {
        this.cuentaEje = cuentaEje;
        this.cuentas = cuentas;
        this.nombreEstrategia = nombreEstrategia;
        this.nombreTipoServicio = nombreTipoServicio;
        this.fecha = fecha;
        this.monedaDenominacionTicker = monedaDenominacionTicker;
        this.clientes = clientes;
        this.margen = margen;
    }
    
    

    /**
     * @return the cuentaEje
     */
    public String getCuentaEje() {
        return cuentaEje;
    }

    /**
     * @param cuentaEje the cuentaEje to set
     */
    public void setCuentaEje(String cuentaEje) {
        this.cuentaEje = cuentaEje;
    }

    /**
     * @return the cuentas
     */
    public Map<String, String> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(Map<String, String> cuentas) {
        this.cuentas = cuentas;
    }

    /**
     * @return the nombreEstrategia
     */
    public String getNombreEstrategia() {
        return nombreEstrategia;
    }

    /**
     * @param nombreEstrategia the nombreEstrategia to set
     */
    public void setNombreEstrategia(String nombreEstrategia) {
        this.nombreEstrategia = nombreEstrategia;
    }

    /**
     * @return the nombreTipoServicio
     */
    public String getNombreTipoServicio() {
        return nombreTipoServicio;
    }

    /**
     * @param nombreTipoServicio the nombreTipoServicio to set
     */
    public void setNombreTipoServicio(String nombreTipoServicio) {
        this.nombreTipoServicio = nombreTipoServicio;
    }

    /**
     * @return the fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * @return the monedaDenominacionTicker
     */
    public String getMonedaDenominacionTicker() {
        return monedaDenominacionTicker;
    }

    /**
     * @param monedaDenominacionTicker the monedaDenominacionTicker to set
     */
    public void setMonedaDenominacionTicker(String monedaDenominacionTicker) {
        this.monedaDenominacionTicker = monedaDenominacionTicker;
    }

    /**
     * @return the clientes
     */
    public List<String> getClientes() {
        return clientes;
    }

    /**
     * @param clientes the clientes to set
     */
    public void setClientes(List<String> clientes) {
        this.clientes = clientes;
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
