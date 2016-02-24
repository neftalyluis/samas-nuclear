/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Stateless;
import mx.samas.ejb.entities.Transaction;

/**
 *
 * @author neftaly
 */
@Stateless
public class TransactionBean implements TransactionBeanLocal {

    @Override
    public Transaction findBySourceAndName(String sourceOwner, String transactionName) {
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
}
