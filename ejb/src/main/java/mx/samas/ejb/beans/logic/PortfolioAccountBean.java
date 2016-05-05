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
import mx.samas.ejb.entities.PortafolioCuenta;
import mx.samas.ejb.entities.PortafolioEstatus;

/**
 *
 * @author neftaly
 */
@Stateless
public class PortfolioAccountBean {

    private static final Logger LOG = Logger.getLogger(PortfolioAccountBean.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public PortafolioCuenta findByAccountNumber(String accountNumber) throws AppException {
        try {
            return (PortafolioCuenta) em.createNamedQuery("PortfolioAccount.findByAccountNumber").setParameter("account", accountNumber).getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public PortafolioEstatus getActiveStatus() throws AppException {
        try {
            return (PortafolioEstatus) em.createNamedQuery("PortfolioStatus.active").setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obetener el estatus Activo, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }
    
    public void persistPortfolioStatus(PortafolioEstatus ps) throws AppException{
        try {
            em.persist(ps);
        } catch (Exception e) {
            throw new AppException();
        }
        
    }
    
    public void persistPortfolioAccount(PortafolioCuenta pa) throws AppException{
        try {
            em.persist(pa);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
