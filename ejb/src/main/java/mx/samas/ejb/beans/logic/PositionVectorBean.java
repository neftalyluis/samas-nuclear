/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.List;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.entities.PositionVector;

/**
 *
 * @author neftaly
 */
@Stateless
public class PositionVectorBean {

    private static final Logger LOG = Logger.getLogger(PositionVectorBean.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public List<PositionVector> getNotInCredit() {
        try {
            return null;
        } catch (Exception e) {
            return null;
        }

    }

}
