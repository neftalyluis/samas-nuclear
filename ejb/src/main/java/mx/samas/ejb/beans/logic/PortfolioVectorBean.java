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
import mx.samas.ejb.entities.PortfolioVector;

/**
 *
 * @author neftaly
 */
@Stateless
@LocalBean
public class PortfolioVectorBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(PortfolioVectorBean.class.getName());

    public void persistPortfolioVector(PortfolioVector pv) throws AppException {
        try {
            em.persist(pv);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
