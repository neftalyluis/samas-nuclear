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
import mx.samas.ejb.entities.DenominatorCurrency;

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
    public DenominatorCurrency getMXPCurrency() {
        try {
            return (DenominatorCurrency) em.createNamedQuery("DenominatorCurrency.findByTicker").setParameter("ticker", "*C_MXP_").getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener el Denominator Currency, la excepcion es: {0}", e.getMessage());

            return null;
        }
    }

}
