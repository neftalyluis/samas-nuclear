/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Activo;
import mx.samas.ejb.entities.DenominacionMoneda;

/**
 *
 * @author neftaly
 */
@Stateless
public class CurrencyBean {

    private static final Logger LOG = Logger.getLogger(CurrencyBean.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public DenominacionMoneda getMXPCurrency() {
        try {
            return (DenominacionMoneda) em.createNamedQuery("DenominatorCurrency.findByTicker").setParameter("ticker", "*C_MXP_").getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener el Denominator Currency, la excepcion es: {0}", e.getMessage());

            return null;
        }
    }

    public void persistCurrency(Activo c) throws AppException {
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public void persistDenominator(DenominacionMoneda c) throws AppException {
        try {
            em.persist(c);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
