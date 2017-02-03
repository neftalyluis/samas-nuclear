/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.rebalanceo;

/**
 *
 * @author samas
 */
public class TuplePortafolio {

    private Double posicion;
    private Double diana;

    /** TENGO DUDAS AQUI
     * @param posicion Variable de tipo Double
     * @param diana Variable de tipo Double
     */
    public TuplePortafolio(Double posicion, Double diana) {
        this.posicion = posicion;
        this.diana = diana;
    }

    /**
     * @return the posicion
     */
    public Double getPosicion() {
        return posicion;
    }

    /**
     * @param posicion the posicion to set
     */
    public void setPosicion(Double posicion) {
        this.posicion = posicion;
    }

    /**
     * @return the diana
     */
    public Double getDiana() {
        return diana;
    }

    /**
     * @param diana the diana to set
     */
    public void setDiana(Double diana) {
        this.diana = diana;
    }
}
