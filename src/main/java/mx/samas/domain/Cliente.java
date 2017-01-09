/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author samas
 */
@Entity
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombre;

    private Boolean sofisticado;

    private Boolean elegible;

    public Cliente() {
    }

    /**
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     */
    public Cliente(String nombre) {
        this.nombre = nombre;
        this.elegible = false;
        this.sofisticado = false;
    }

    /**
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param elegible Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     * @param sofisticado Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     */
    public Cliente(String nombre, Boolean elegible, Boolean sofisticado) {
        this.nombre = nombre;
        this.elegible = elegible;
        this.sofisticado = sofisticado;
    }

    /**
     * @param nombre Guardara la cadena que se ingrese desde la interfaz.
     * @param elegible Solo acepta como valores verdadero o falso (booleano) el cual guardara. 
     */
    public Cliente(String nombre, Boolean elegible) {
        this.nombre = nombre;
        this.elegible = elegible;
        this.sofisticado = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.domain.Cliente[ id=" + id + " ]";
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
