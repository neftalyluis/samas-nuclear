/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.SourceOwner;
import mx.samas.ejb.entities.Transaction;

/**
 *
 * @author neftaly
 */
@Stateless
public class TransactionBean {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    public Transaction findByNameAndOwner(String sourceOwner, String transactionName) throws AppException {
        try {
            return (Transaction) em.createNamedQuery("Transaction.findByNameAndOwner").setParameter("nameOwner", sourceOwner).setParameter("nameTransaction", transactionName).getSingleResult();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public Transaction findByName(String transactionName) throws AppException {
        try {
            return (Transaction) em.createNamedQuery("Transaction.findByName").setParameter("nameTransaction", transactionName).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException();
        }
    }

    public void persistTransaction(Transaction t) throws AppException {
        try {
            em.persist(t);
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public void persistTransactionList(List<Transaction> lt) throws AppException {
        try {
            for (Transaction t : lt) {
                em.persist(t);
            }
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public void persistOwner(SourceOwner so) throws AppException {
        try {
            em.persist(so);
        } catch (Exception e) {
            throw new AppException();
        }
    }

}
