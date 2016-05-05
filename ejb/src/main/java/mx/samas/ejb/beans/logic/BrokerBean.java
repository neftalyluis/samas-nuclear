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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Corredor;

/**
 *
 * @author neftaly
 */
@Stateless
public class BrokerBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger LOG = Logger.getLogger(BrokerBean.class.getName());

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    public Corredor getUniqueBroker() {
        try {
            return (Corredor) em.createNamedQuery("Broker.getByName").setParameter("name", "HSBC").getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener nuestro broker, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    public void persistBroker(Corredor b) throws AppException{
        try {
            em.persist(b);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
