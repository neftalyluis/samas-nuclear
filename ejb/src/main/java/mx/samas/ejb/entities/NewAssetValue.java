/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

/**
 *
 * @author neftaly
 * @param <T>
 */
@Entity
public class NewAssetValue<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private NewAssetProperty property;
    
    @ManyToOne
    private NewAsset asset;
    
    @Lob
    private Class<T> objectValue;
    
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
        if (!(object instanceof NewAssetValue)) {
            return false;
        }
        NewAssetValue other = (NewAssetValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.NewAssetValues[ id=" + id + " ]";
    }

    /**
     * @return the property
     */
    public NewAssetProperty getProperty() {
        return property;
    }

    /**
     * @param property the property to set
     */
    public void setProperty(NewAssetProperty property) {
        this.property = property;
    }

    /**
     * @return the asset
     */
    public NewAsset getAsset() {
        return asset;
    }

    /**
     * @param asset the asset to set
     */
    public void setAsset(NewAsset asset) {
        this.asset = asset;
    }

    /**
     * @return the objectValue
     */
    public Class<T> getObjectValue() {
        return objectValue;
    }

    /**
     * @param objectValue the objectValue to set
     */
    public void setObjectValue(Class<T> objectValue) {
        this.objectValue = objectValue;
    }




    
}
