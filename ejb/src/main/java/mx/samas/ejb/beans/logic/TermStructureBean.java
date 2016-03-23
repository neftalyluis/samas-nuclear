/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.TermStructure;

/**
 *
 * @author neftaly
 */
@Stateless
public class TermStructureBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(TermStructureBean.class.getName());

    public void persistTermStructure(TermStructure ts) throws AppException {
        try {
            em.persist(ts);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}