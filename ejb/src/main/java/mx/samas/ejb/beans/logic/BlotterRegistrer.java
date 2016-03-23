/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.beans.logic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TemporalType;
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.entities.Blotter;

/**
 *
 * @author neftaly
 */
@Stateless
public class BlotterRegistrer {

    private static final Logger LOG = Logger.getLogger(BlotterRegistrer.class.getName());

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    @EJB
    private PortfolioAccountBean pab;

    @EJB
    private TransactionBean tb;

    @EJB
    private AssetBean ab;

    /**
     *
     * @param amount La cantidad de dinero que se va a depositar
     * @param contract La cuenta a donde se va a depositar
     * @return Si la entrada fue o no persistida en la DB
     */
    public boolean depositMoney(Double amount, String contract) throws AppException {
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
            b.setTransaction(tb.findByName("Deposito en efectivo del Cliente"));
            em.persist(b);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException();
        }
    }

    public boolean sellEquity(String ticker, Double price, Long quantity, String contract) throws AppException {
        try {
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

            compra.setAsset(ab.findAssetByTicker(ticker));
            compra.setContract(pab.findByAccountNumber(contract));
            compra.setTransaction(tb.findByName("Venta"));

            em.persist(compra);
            return false;
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public boolean buyEquity(String ticker, Double price, Long quantity, String contract) throws AppException {
        try {
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

            compra.setAsset(ab.findAssetByTicker(ticker));
            compra.setContract(pab.findByAccountNumber(contract));
            compra.setTransaction(tb.findByName("Compra"));

            em.persist(compra);

            return true;
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public boolean inicioReporto(String ticker, Double price, Long quantity, String contract, Double rate) throws AppException {
        try {

            Blotter b = new Blotter();

            Date d = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);

            b.setAmount(price);
            b.setAsset(ab.findAssetByTicker(ticker));
            b.setContract(pab.findByAccountNumber(contract));

            b.setPrice(price);
            b.setQuantity(quantity);
            b.setRate(rate);

            b.setSettlementDate(c.getTime());
            b.setTradeDate(d);
            b.setInputDate(d);

            b.setTransaction(tb.findByName("Inicio de Reporto"));

            em.persist(b);
            return true;
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public boolean finReporto(String ticker, Double price, Long quantity, String contract, Double rate) throws AppException {
        try {
            Blotter b = new Blotter();

            Date d = new Date();

            b.setAmount(price);
            b.setAsset(ab.findAssetByTicker(ticker));
            b.setContract(pab.findByAccountNumber(contract));

            b.setPrice(price);
            b.setQuantity(quantity);
            b.setRate(rate);

            b.setSettlementDate(d);
            b.setTradeDate(d);
            b.setInputDate(d);

            b.setTransaction(tb.findByName("Fin de Reporto"));

            em.persist(b);

            return true;
        } catch (Exception e) {
            throw new AppException();
        }

    }

    public boolean buyBond() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Blotter> getBuyAndSellTransactions(String account, Date inputDate, String ticker) throws AppException {
        try {
            return (List<Blotter>) em.createNamedQuery("Blotter.BuyAndSellFromDateAndAccountWithAsset")
                    .setParameter("account", account)
                    .setParameter("input", inputDate, TemporalType.DATE)
                    .setParameter("asset", ab.findByTicker(ticker))
                    .getResultList();
        } catch (Exception e) {
            throw new AppException();
        }
    }

    //VectorPasivo
    /**
     * 
     */
    public void conciliacion (){
        
    }
}
