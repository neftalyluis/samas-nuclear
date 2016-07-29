/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

/**
 *
 * @author samas
 */
@Entity
public class Cliente extends ParentModel {
    
    private String nombre;
    
    private Boolean sofisticado;
    private Boolean elegible;

    @ManyToMany
    private List<Cuenta> cuentas;

    /**
     * @return the cuentas
     */
    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    /**
     * @param cuentas the cuentas to set
     */
    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
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
     * @return the sofisticado
     */
    public Boolean getSofisticado() {
        return sofisticado;
    }

    /**
     * @param sofisticado the sofisticado to set
     */
    public void setSofisticado(Boolean sofisticado) {
        this.sofisticado = sofisticado;
    }

    /**
     * @return the elegible
     */
    public Boolean getElegible() {
        return elegible;
    }

    /**
     * @param elegible the elegible to set
     */
    public void setElegible(Boolean elegible) {
        this.elegible = elegible;
    }

}
