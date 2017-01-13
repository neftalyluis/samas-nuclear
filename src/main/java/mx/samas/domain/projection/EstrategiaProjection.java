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
public class EstrategiaProjection {

    private Long id;
    private String nombre;
    private Boolean liquidez;
    private Boolean efectivo;
    private Boolean grupos;
    private Boolean margen;

    /**
     * @param id Guardara un numero entre -2147483648 a 2147483647.
     * @param nombre Guardara una cadena.
     * @param liquidez Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param efectivo Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param grupos Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param margen Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     */
    public EstrategiaProjection(Long id, String nombre, Boolean liquidez, Boolean efectivo, Boolean grupos, Boolean margen) {
        this.id = id;
        this.nombre = nombre;
        this.liquidez = liquidez;
        this.efectivo = efectivo;
        this.grupos = grupos;
        this.margen = margen;
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
     * @return the liquidez
     */
    public Boolean getLiquidez() {
        return liquidez;
    }

    /**
     * @param liquidez the liquidez to set
     */
    public void setLiquidez(Boolean liquidez) {
        this.liquidez = liquidez;
    }

    /**
     * @return the efectivo
     */
    public Boolean getEfectivo() {
        return efectivo;
    }

    /**
     * @param efectivo the efectivo to set
     */
    public void setEfectivo(Boolean efectivo) {
        this.efectivo = efectivo;
    }

    /**
     * @return the grupos
     */
    public Boolean getGrupos() {
        return grupos;
    }

    /**
     * @param grupos the grupos to set
     */
    public void setGrupos(Boolean grupos) {
        this.grupos = grupos;
    }

    /**
     * @return the margen
     */
    public Boolean getMargen() {
        return margen;
    }

    /**
     * @param margen the margen to set
     */
    public void setMargen(Boolean margen) {
        this.margen = margen;
    }
}
