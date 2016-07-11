/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author samas
 */
@Entity
public class PortafolioModelo extends ParentModel {

    @ManyToOne
    private Activo activo;
    
    @ManyToOne
    private Estrategia estrategia;
    
    private Double diana;

    /**
     * @return the activo
     */
    public Activo getActivo() {
        return activo;
    }

    /**
     * @param activo the activo to set
     */
    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    /**
     * @return the estrategia
     */
    public Estrategia getEstrategia() {
        return estrategia;
    }

    /**
     * @param estrategia the estrategia to set
     */
    public void setEstrategia(Estrategia estrategia) {
        this.estrategia = estrategia;
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
