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
public class ActivoPropiedadValor {
    
    private Long propiedadId;
    private String valor;

    /**
     * @return the propiedadId
     */
    public Long getPropiedadId() {
        return propiedadId;
    }

    /**
     * @param propiedadId the propiedadId to set
     */
    public void setPropiedadId(Long propiedadId) {
        this.propiedadId = propiedadId;
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
    
}
