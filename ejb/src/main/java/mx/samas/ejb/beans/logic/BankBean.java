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
import mx.samas.ejb.entities.Bank;

/**
 *
 * @author neftaly
 */
@Stateless
public class BankBean {

    private static final Logger LOG = Logger.getLogger(BankBean.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public Bank getBankByName(String name) throws AppException {
        try {
            return (Bank) em.createNamedQuery("Bank.findByName").setParameter("name", name).getSingleResult();
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos obtener el bank, la excepcion es: {0}", e.getMessage());
            throw new AppException();
        }
    }

    public void persistBank(Bank b) throws AppException {
        try {
            em.persist(b);
        } catch (Exception e) {
            throw new AppException();
        }
    }
}
