/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 */
@Entity
@XmlRootElement
public class EntidadLegal implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
//    private String fiscalDomicile; // Por ahora no lo usemos

    @OneToMany
    private List<GradoCalidad> grados;

    @OneToMany
    private List<Emisor> emisores;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EntidadLegal)) {
            return false;
        }
        EntidadLegal other = (EntidadLegal) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.LegalClass[ id=" + getId() + " ]";
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
     * @return the grados
     */
    public List<GradoCalidad> getGrados() {
        return grados;
    }

    /**
     * @param grados the grados to set
     */
    public void setGrados(List<GradoCalidad> grados) {
        this.grados = grados;
    }

    /**
     * @return the emisores
     */
    public List<Emisor> getEmisores() {
        return emisores;
    }

    /**
     * @param emisores the emisores to set
     */
    public void setEmisores(List<Emisor> emisores) {
        this.emisores = emisores;
    }

}
