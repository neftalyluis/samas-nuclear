/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 *
 * Esta entidad se encarga de definir las Propiedades distintivas, que definen a
 * cada Asset
 */
@Entity
@XmlRootElement
public class ActivoPropiedadTipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la propiedad
     */
    private String nombre;
    /**
     * Descripcion de la propiedad
     */
    private String descripcion;
    
    /**
     * El tipo de dato que va a manejar esta propiedad: - Boolean -
     * String - Integer - Double - Date
     */
    @Enumerated(EnumType.STRING)
    private TipoDato tipoDato;

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
        if (!(object instanceof ActivoPropiedadTipo)) {
            return false;
        }
        ActivoPropiedadTipo other = (ActivoPropiedadTipo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.NewAssetProperties[ id=" + id + " ]";
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
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the tipoDato
     */
    public TipoDato getTipoDato() {
        return tipoDato;
    }

    /**
     * @param tipoDato the tipoDato to set
     */
    public void setTipoDato(TipoDato tipoDato) {
        this.tipoDato = tipoDato;
    }
}
