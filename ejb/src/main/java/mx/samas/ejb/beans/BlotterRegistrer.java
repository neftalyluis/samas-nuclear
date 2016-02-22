/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.entities.Blotter;
import mx.samas.ejb.entities.PortfolioAccount;

/**
 *
 * @author neftaly
 */
@Stateless
public class BlotterRegistrer implements BlotterRegistrerLocal {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    @Override
    public boolean depositMoney(Double amount, PortfolioAccount contract) {
        try {
            Date sameDate = new Date();
            Blotter b = new Blotter();
            b.setAmount(amount);
            b.setAsset(null);
            b.setContract(contract);
            b.setInputDate(sameDate);
            b.setMarket(null);
            b.setPrice(0.0);
            b.setQuantity(0.0);
            b.setRate(0.0);
            b.setSettlementDate(sameDate);
            b.setTradeDate(sameDate);
            //Falta denominar
//            b.setTransaction(transaction);
            em.persist(b);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean sellAsset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buyAsset() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reporto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
