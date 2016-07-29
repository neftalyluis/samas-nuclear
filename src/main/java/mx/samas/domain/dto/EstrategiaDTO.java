/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author samas
 */
public class EstrategiaDTO {

    @NotNull
    @NotEmpty
    private String nombre;
    private PortafolioModeloDTO modelos;

    public boolean validate() {
        return modelos.validate();
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
     * @return the modelos
     */
    public PortafolioModeloDTO getModelos() {
        return modelos;
    }

    /**
     * @param modelos the modelos to set
     */
    public void setModelos(PortafolioModeloDTO modelos) {
        this.modelos = modelos;
    }

}
