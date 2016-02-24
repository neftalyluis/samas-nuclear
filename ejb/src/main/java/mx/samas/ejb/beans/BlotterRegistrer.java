/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans;

import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.logic.AssetBeanLocal;
import mx.samas.ejb.beans.logic.PortfolioAccountBeanLocal;
import mx.samas.ejb.beans.logic.TransactionBeanLocal;
import mx.samas.ejb.entities.Blotter;
import mx.samas.ejb.entities.BlotterChild;

/**
 *
 * @author neftaly
 */
@Stateless
public class BlotterRegistrer implements BlotterRegistrerLocal {
    
    private static final Logger LOG = Logger.getLogger(BlotterRegistrer.class.getName());
    
    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;
    
    @EJB
    private PortfolioAccountBeanLocal pab;
    
    @EJB
    private TransactionBeanLocal tb;
    
    @EJB
    private AssetBeanLocal ab;

    /**
     *
     * @param amount La cantidad de dinero que se va a depositar
     * @param contract La cuenta a donde se va a depositar
     * @return Si la entrada fue o no persistida en la DB
     */
    @Override
    public boolean depositMoney(Double amount, String contract) {
        try {
            Date sameDate = new Date();
            Blotter b = new Blotter();
            b.setAmount(amount);
            b.setAsset(null);
            b.setContract(pab.findByAccountNumber(contract));
            b.setInputDate(sameDate);
            b.setMarket(null);
            b.setPrice(amount);
            b.setQuantity(new Long(0));
            b.setRate(0.0);
            b.setSettlementDate(sameDate);
            b.setTradeDate(sameDate);
            //Falta denominar con transaction source
            b.setTransaction(tb.findBySourceAndName(contract, contract));
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
    public boolean buyEquity(String ticker, Double price, Long quantity, String contract) {
        try {
            //
            //No discrecional, checar la comisión
//        PortfolioAccount pa = new PortfolioAccount();
            Date hoy = new Date();

            //Tres dias para settlementDate
            Calendar c = Calendar.getInstance();
            c.setTime(hoy);
            c.add(Calendar.DATE, 3);
            
            Blotter compra = new Blotter();
            compra.setAmount(price / quantity);
            compra.setPrice(price);
            compra.setQuantity(quantity);
            //No existe tasa
            compra.setRate(0.0);

            //entrega de titulos, mas 3 dias
            compra.setSettlementDate(c.getTime());
            //No aplicable en sociedades de inversion
            compra.setTradeDate(hoy);
            compra.setInputDate(hoy);
            
            compra.setAsset(ab.findByTicker(ticker));
            compra.setContract(pab.findByAccountNumber(contract));
            compra.setTransaction(tb.findBySourceAndName(ticker, contract));
            
            BlotterChild comision = new BlotterChild();
            comision.setFather(compra);
            
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean reporto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean buyBond() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
