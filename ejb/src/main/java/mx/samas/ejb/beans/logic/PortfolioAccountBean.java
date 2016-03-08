/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.PortfolioAccount;

/**
 *
 * @author neftaly
 */
@Stateless
public class PortfolioAccountBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public PortfolioAccount findByAccountNumber(String accountNumber) throws AppException {
        try {
            return (PortfolioAccount) em.createNamedQuery("PortfolioAccount.findByAccountNumber").setParameter("account", accountNumber).getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }
    
    

}
