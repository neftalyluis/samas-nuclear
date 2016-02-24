/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import javax.ejb.Local;
import mx.samas.ejb.entities.Transaction;

/**
 *
 * @author neftaly
 */
@Local
public interface TransactionBeanLocal {

    public Transaction findBySourceAndName(String sourceOwner, String transactionName);

}
