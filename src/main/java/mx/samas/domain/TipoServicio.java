/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

/**
 *
 * @author samas
 */
public class TipoServicio {
    
    private String nombre;
    private Boolean discrecional;

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
     * @return the discrecional
     */
    public Boolean getDiscrecional() {
        return discrecional;
    }

    /**
     * @param discrecional the discrecional to set
     */
    public void setDiscrecional(Boolean discrecional) {
        this.discrecional = discrecional;
    }
    
}
