/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.projection;

/**
 *
 * @author samas
 */
public class TransaccionProjection {

    private Long id;
    private String fuenteTransaccion;
    private String nombre;
    private Boolean credito;
    private Long flujoTitulos;
    private Long flujoEfectivo;

    /**
     * @param id Guardara un numero entre -2147483648 a 2147483647.
     * @param fuenteTransaccion Guardara una cadena.
     * @param nombre Guardara una cadena.
     * @param credito Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param flujoTitulos Guardara un numero entre -2147483648 a 2147483647.
     * @param flujoEfectivo Guardara un numero entre -2147483648 a 2147483647.
     */
    public TransaccionProjection(Long id, String fuenteTransaccion, String nombre, Boolean credito, Long flujoTitulos, Long flujoEfectivo) {
        this.id = id;
        this.fuenteTransaccion = fuenteTransaccion;
        this.nombre = nombre;
        this.credito = credito;
        this.flujoTitulos = flujoTitulos;
        this.flujoEfectivo = flujoEfectivo;
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
     * @return the fuenteTransaccion
     */
    public String getFuenteTransaccion() {
        return fuenteTransaccion;
    }

    /**
     * @param fuenteTransaccion the fuenteTransaccion to set
     */
    public void setFuenteTransaccion(String fuenteTransaccion) {
        this.fuenteTransaccion = fuenteTransaccion;
    }

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
     * @return the credito
     */
    public Boolean getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(Boolean credito) {
        this.credito = credito;
    }

    /**
     * @return the flujoTitulos
     */
    public Long getFlujoTitulos() {
        return flujoTitulos;
    }

    /**
     * @param flujoTitulos the flujoTitulos to set
     */
    public void setFlujoTitulos(Long flujoTitulos) {
        this.flujoTitulos = flujoTitulos;
    }

    /**
     * @return the flujoEfectivo
     */
    public Long getFlujoEfectivo() {
        return flujoEfectivo;
    }

    /**
     * @param flujoEfectivo the flujoEfectivo to set
     */
    public void setFlujoEfectivo(Long flujoEfectivo) {
        this.flujoEfectivo = flujoEfectivo;
    }
}
