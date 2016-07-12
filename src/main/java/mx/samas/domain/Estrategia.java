/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author samas
 */
@Entity
public class Estrategia extends ParentModel {

    private String nombre;

    @OneToMany(mappedBy = "estrategia", cascade = CascadeType.ALL)
    private List<PortafolioModelo> estrategiaModelo;

    public Estrategia() {

    }

    public Estrategia(String nombre, List<PortafolioModelo> modelo) {
        this.nombre = nombre;
        this.estrategiaModelo = modelo;

    }

    public Estrategia(String nombre) {
        this.nombre = nombre;

    }

//    @ManyToOne
//    private PerfilRiesgo perfilRiesgo;
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
     * @return the estrategiaModelo
     */
    public List<PortafolioModelo> getEstrategiaModelo() {
        return estrategiaModelo;
    }

    /**
     * @param estrategiaModelo the estrategiaModelo to set
     */
    public void setEstrategiaModelo(List<PortafolioModelo> estrategiaModelo) {
        this.estrategiaModelo = estrategiaModelo;
    }
}
