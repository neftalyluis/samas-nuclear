/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.projection;

import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.NotNull;

/**
 *
 * @author samas
 */
public class PortafolioProjection implements Serializable {

    @NotNull
    private String cuentaEje;
    @NotNull
    private String nombreEstrategia;
    @NotNull
    private String nombreTipoServicio;
    @NotNull
    private String monedaDenominacionTicker;
    @NotNull
    private Double margen;
    @NotNull
    private String estatus;
    @NotNull
    private LocalDate fecha;

    public PortafolioProjection(String cuentaEje,
            String estrategia, String tipoServicio, LocalDate fecha,
            String monedaTicker, Double margenActual, String estatus) {

        this.cuentaEje = cuentaEje;
        this.nombreEstrategia = estrategia;
        this.nombreTipoServicio = tipoServicio;
        this.monedaDenominacionTicker = monedaTicker;
        this.margen = margenActual;
        this.estatus = estatus;
        this.fecha = fecha;

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

    /**
     * @return the estatus
     */
    public String getEstatus() {
        return estatus;
    }

    /**
     * @param estatus the estatus to set
     */
    public void setEstatus(String estatus) {
        this.estatus = estatus;
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

}
