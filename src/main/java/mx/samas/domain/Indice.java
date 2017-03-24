/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author samas
 */
@Entity
public class Indice extends Activo implements Serializable {

    private Boolean privada;
    private Boolean fondo;
    private Double comisionAnual;

    public Indice() {

    }

    /**
     * @param tipoValor Guardara la cadena que se ingrese desde la interfaz.
     * @param emisora Guardara la cadena que se ingrese desde la interfaz.
     * @param serie Guardara la cadena que se ingrese desde la interfaz.
     * @param isin Guardara la cadena que se ingrese desde la interfaz.
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param tipo Guardara el TipoActivo que se seleccione desde la interfaz.
     * @param ventaEnCorto Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param pujaMinima Guardara un numero fraccionario (de hasta 15 digitos) que se ingrese desde la interfaz.
     */
    public Indice(String tipoValor, String emisora, String serie, String isin,
            String nombre, TipoActivo tipo, Boolean ventaEnCorto,
            Double pujaMinima) {
        super(tipoValor, emisora, serie, isin, nombre, tipo, ventaEnCorto, pujaMinima);
        
    }

    /**
     * @return the privada
     */
    public Boolean getPrivada() {
        return privada;
    }

    /**
     * @param privada the privada to set
     */
    public void setPrivada(Boolean privada) {
        this.privada = privada;
    }

    /**
     * @return the fondo
     */
    public Boolean getFondo() {
        return fondo;
    }

    /**
     * @param fondo the fondo to set
     */
    public void setFondo(Boolean fondo) {
        this.fondo = fondo;
    }

    /**
     * @return the comisionAnual
     */
    public Double getComisionAnual() {
        return comisionAnual;
    }

    /**
     * @param comisionAnual the comisionAnual to set
     */
    public void setComisionAnual(Double comisionAnual) {
        this.comisionAnual = comisionAnual;
    }

}