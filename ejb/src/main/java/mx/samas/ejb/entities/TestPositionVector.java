/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author neftaly
 */
@Entity
public class TestPositionVector extends PositionVector implements Serializable {

    @Override
    public String toString() {
        return "mx.samas.ejb.entities.TestPositionVector[ id=" + getId() + " ]";
    }

}
