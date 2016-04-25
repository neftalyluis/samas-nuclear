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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neftaly
 * 
 * Esta entidad describe todos los agentes que operan en los portafolios 
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Broker.getByName", query = "SELECT b FROM Broker b WHERE b.name = :name")

})
public class Broker implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    
    public Broker(){
        
    }

    public Broker(String name) {
        this.name = name;
    }

    @ManyToMany
    private List<BrokerCommission> commisions;

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
        if (!(object instanceof Broker)) {
            return false;
        }
        Broker other = (Broker) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Broker[ id=" + id + " ]";
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
     * @return the commisions
     */
    @XmlTransient
    public List<BrokerCommission> getCommisions() {
        return commisions;
    }

    /**
     * @param commisions the commisions to set
     */
    public void setCommisions(List<BrokerCommission> commisions) {
        this.commisions = commisions;
    }

}
