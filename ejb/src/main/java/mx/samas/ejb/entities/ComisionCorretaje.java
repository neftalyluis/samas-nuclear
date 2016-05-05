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
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 *
 * Esta entidad describe los tipos de comision para todos los agentes que operan
 * en los portafolios
 */
@Entity
@XmlRootElement
public class ComisionCorretaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Corredor> corredores;

    private Double comision;

    private String tipoActivo;

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
        if (!(object instanceof ComisionCorretaje)) {
            return false;
        }
        ComisionCorretaje other = (ComisionCorretaje) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.BrokerCommisions[ id=" + id + " ]";
    }

    /**
     * @return the corredores
     */
    public List<Corredor> getCorredores() {
        return corredores;
    }

    /**
     * @param corredores the corredores to set
     */
    public void setCorredores(List<Corredor> corredores) {
        this.corredores = corredores;
    }

    /**
     * @return the comision
     */
    public Double getComision() {
        return comision;
    }

    /**
     * @param comision the comision to set
     */
    public void setComision(Double comision) {
        this.comision = comision;
    }

    /**
     * @return the tipoActivo
     */
    public String getTipoActivo() {
        return tipoActivo;
    }

    /**
     * @param tipoActivo the tipoActivo to set
     */
    public void setTipoActivo(String tipoActivo) {
        this.tipoActivo = tipoActivo;
    }

}
