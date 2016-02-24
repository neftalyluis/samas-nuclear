/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Stateless;
import mx.samas.ejb.entities.PortfolioAccount;

/**
 *
 * @author neftaly
 */
@Stateless
public class PortfolioAccountBean implements PortfolioAccountBeanLocal {

    @Override
    public PortfolioAccount findByAccountNumber(String accountNumber) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
