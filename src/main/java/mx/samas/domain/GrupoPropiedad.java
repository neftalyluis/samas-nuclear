/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Embeddable
public class GrupoPropiedad implements Serializable {

    @ManyToOne
    private ActivoPropiedad propiedad;
    private String valor;

    @Enumerated
    private Operador operador;

    /**
     * @return the propiedad
     */
    public ActivoPropiedad getPropiedad() {
        return propiedad;
    }

    /**
     * @param propiedad the propiedad to set
     */
    public void setPropiedad(ActivoPropiedad propiedad) {
        this.propiedad = propiedad;
    }

    /**
     * @return the valor
     */
    public String getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     * @return the operador
     */
    public Operador getOperador() {
        return operador;
    }

    /**
     * @param operador the operador to set
     */
    public void setOperador(Operador operador) {
        this.operador = operador;
    }
}
