/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.RiskProfile;

/**
 *
 * @author neftaly
 */
@Stateless
public class RiskProfileBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(RiskProfileBean.class.getName());

    public void persistRiskProfile(RiskProfile rk) throws AppException {
        try {
            em.persist(rk);
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public RiskProfile findByName(String name) throws AppException {
        try {
            return (RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Balanceado").getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
