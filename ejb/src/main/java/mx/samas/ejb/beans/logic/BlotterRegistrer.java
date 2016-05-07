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
import mx.samas.ejb.entities.Bitacora;

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
            Bitacora b = new Bitacora();
            b.setFlujoEfectivo(amount);
            b.setActivo(null);
            b.setContrato(pab.findByAccountNumber(contract));
            b.setFechaIngreso(sameDate);
            b.setMercado(null);
            b.setPrecio(amount);
            b.setFlujoTitulos(new Long(0));
            b.setTasa(0.0);
            b.setFechaLiquidacion(sameDate);
            b.setFechaEjecucion(sameDate);
            //Falta denominar con transaction source
            b.setTransaccion(tb.findByName("Deposito en efectivo del Cliente"));
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

            Bitacora compra = new Bitacora();
            compra.setFlujoEfectivo(price / quantity);
            compra.setPrecio(price);
            compra.setFlujoTitulos(quantity);
            //No existe tasa
            compra.setTasa(0.0);

            //entrega de titulos, mas 3 dias
            compra.setFechaLiquidacion(c.getTime());
            //No aplicable en sociedades de inversion
            compra.setFechaEjecucion(hoy);
            compra.setFechaIngreso(hoy);

            compra.setActivo(ab.findAssetByTicker(ticker));
            compra.setContrato(pab.findByAccountNumber(contract));
            compra.setTransaccion(tb.findByName("Venta"));

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

            Bitacora compra = new Bitacora();
            compra.setFlujoEfectivo(price / quantity);
            compra.setPrecio(price);
            compra.setFlujoTitulos(quantity);
            //No existe tasa
            compra.setTasa(0.0);

            //entrega de titulos, mas 3 dias
            compra.setFechaLiquidacion(c.getTime());
            //No aplicable en sociedades de inversion
            compra.setFechaEjecucion(hoy);
            compra.setFechaIngreso(hoy);

            compra.setActivo(ab.findAssetByTicker(ticker));
            compra.setContrato(pab.findByAccountNumber(contract));
            compra.setTransaccion(tb.findByName("Compra"));

            em.persist(compra);

            return true;
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public boolean inicioReporto(String ticker, Double price, Long quantity, String contract, Double rate) throws AppException {
        try {

            Bitacora b = new Bitacora();

            Date d = new Date();

            Calendar c = Calendar.getInstance();
            c.setTime(d);
            c.add(Calendar.DATE, 1);

            b.setFlujoEfectivo(price);
            b.setActivo(ab.findAssetByTicker(ticker));
            b.setContrato(pab.findByAccountNumber(contract));

            b.setPrecio(price);
            b.setFlujoTitulos(quantity);
            b.setTasa(rate);

            b.setFechaLiquidacion(c.getTime());
            b.setFechaEjecucion(d);
            b.setFechaIngreso(d);

            b.setTransaccion(tb.findByName("Inicio de Reporto"));

            em.persist(b);
            return true;
        } catch (Exception e) {
            throw new AppException();
        }
    }

    public boolean finReporto(String ticker, Double price, Long quantity, String contract, Double rate) throws AppException {
        try {
            Bitacora b = new Bitacora();

            Date d = new Date();

            b.setFlujoEfectivo(price);
            b.setActivo(ab.findAssetByTicker(ticker));
            b.setContrato(pab.findByAccountNumber(contract));

            b.setPrecio(price);
            b.setFlujoTitulos(quantity);
            b.setTasa(rate);

            b.setFechaLiquidacion(d);
            b.setFechaEjecucion(d);
            b.setFechaIngreso(d);

            b.setTransaccion(tb.findByName("Fin de Reporto"));

            em.persist(b);

            return true;
        } catch (Exception e) {
            throw new AppException();
        }

    }

    public boolean buyBond() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Bitacora> getBuyAndSellTransactions(String account, Date inputDate, String ticker) throws AppException {
        try {
            return (List<Bitacora>) em.createNamedQuery("Bitacora.CompraYVentaConFechayCuenta")
                    .setParameter("cuenta", account)
                    .setParameter("ingreso", inputDate, TemporalType.DATE)
                    .setParameter("activo", ab.findByTicker(ticker))
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
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
