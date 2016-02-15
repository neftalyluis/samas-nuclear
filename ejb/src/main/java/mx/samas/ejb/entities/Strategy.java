/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * Esta entidad se encarga de agrupar los objetivos de uno o mas portafolios: 
 * - Lista de Activos
 * - Perfil de Riesgo (TBD)
 * 
 * 
 *  
 * Boolean ShortSale 
 * @author alfonso
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Strategy.findByName", query = "SELECT s FROM Strategy s WHERE s.name = :name")

})
public class Strategy implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "strategy", cascade = CascadeType.ALL)
    private List<SliceVector> slices;

    @ManyToOne
    private RiskProfile riskProfile;

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
        if (!(object instanceof Strategy)) {
            return false;
        }
        Strategy other = (Strategy) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.Strategy[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the slices
     */
    @XmlTransient
    public List<SliceVector> getSlices() {
        return slices;
    }

    /**
     * @param slices the slices to set
     */
    public void setSlices(List<SliceVector> slices) {
        this.slices = slices;
    }

    /**
     * @return the riskProfile
     */
    public RiskProfile getRiskProfile() {
        return riskProfile;
    }

    /**
     * @param riskProfile the riskProfile to set
     */
    public void setRiskProfile(RiskProfile riskProfile) {
        this.riskProfile = riskProfile;
    }

}
