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

/**
 *
 * @author neftaly
 */
@Entity
public class Profile implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String profilename;
    private String profileid;

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
        if (!(object instanceof Profile)) {
            return false;
        }
        Profile other = (Profile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "mx.samas.entities.Profile[ id=" + id + " ]";
    }

    /**
     * @return the profilename
     */
    public String getProfilename() {
        return profilename;
    }

    /**
     * @param profilename the profilename to set
     */
    public void setProfilename(String profilename) {
        this.profilename = profilename;
    }

    /**
     * @return the profileid
     */
    public String getProfileid() {
        return profileid;
    }

    /**
     * @param profileid the profileid to set
     */
    public void setProfileid(String profileid) {
        this.profileid = profileid;
    }

}
