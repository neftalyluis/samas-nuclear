/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.entities.Bond;
import mx.samas.ejb.entities.Currency;
import mx.samas.ejb.entities.DenominatorCurrency;
import mx.samas.ejb.entities.Issuer;
import mx.samas.ejb.entities.SecurityClass;
import mx.samas.ejb.entities.Ticker;

/**
 *
 * @author neftaly
 */
@Stateless
public class OnDeployBootstraping {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger log = Logger.getLogger(OnDeployBootstraping.class.getName());

    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        log.info("SAMAS Bootstrap");
        doTheBootstrap();
        timer.cancel();
    }

    private void doTheBootstrap() {
        buildAssets();
    }

    private void buildAssets() {
        log.info("Persistiendo Entidades Iniciales");

    }

    private void persistSecurityClasses() {
        SecurityClass s = new SecurityClass();
        s.setAssetType("Bond");
        s.setCode("S");
        s.setDescription("Udibonos");

        SecurityClass m = new SecurityClass();
        m.setAssetType("Bond");
        m.setCode("M");
        m.setDescription("Bonos de Gobierno Federal Tasa Fija");

        SecurityClass unoi = new SecurityClass();
        unoi.setAssetType("Equity");
        unoi.setCode("1I");
        unoi.setDescription("Tracks Extranjeros");

        SecurityClass uno = new SecurityClass();
        uno.setAssetType("Equity");
        uno.setCode("1");
        uno.setDescription("Acciones Industriales, Comerico y de Servicios");

        SecurityClass unoa = new SecurityClass();
        unoa.setAssetType("Equity");
        unoa.setCode("1A");
        unoa.setDescription("Acciones del sistema Internal. de cotizaciones");

        SecurityClass curr = new SecurityClass();
        curr.setAssetType("Currency");
        curr.setCode("*C");
        curr.setDescription("Tipos de Cambio");

        try {
            log.info("Persistimos SecurityClass");
            em.persist(s);
            em.persist(m);
            em.persist(unoi);
            em.persist(uno);
            em.persist(unoa);
            em.persist(curr);

        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());

        }
    }

    private boolean persistAssets() {
        Bond udibono40 = new Bond();
        udibono40.setCashflowDate(null);
        udibono40.setComission(Boolean.FALSE);
        udibono40.setCurrencyDenomination(mxpcd);
        udibono40.setDayCount(null);
        udibono40.setIsin("");
        udibono40.setIssuer(udibono);
        udibono40.setMaturityDate(new Date(2040, 11, 15));
        udibono40.setName("SECRETARIA DE HACIENDA Y CREDITO PUBLICO");
        udibono40.setReferenceRate(null);
        udibono40.setSecurityClass(s);
        udibono40.setSeries("401115");
        udibono40.setSettlementTimes(null);
        udibono40.setSpreadFixed(null);
        udibono40.setTermStructure(null);
        udibono40.setTickSize(null);

        Ticker udibono40t = new Ticker();
        udibono40t.setTickerValue(curr.getCode() + "_" + mxpi.getCode() + "_" + mxpeso.getSeries());
        List<Ticker> u40tl = new LinkedList<>();
        u40tl.add(udibono40t);
        udibono40.setTicker(u40tl);

        Bond udibono22 = new Bond();
        Bond udibono16 = new Bond();
        Bond bonos42 = new Bond();
        Bond bonos24 = new Bond();
        Bond bonos16 = new Bond();

        return true;
    }

    private boolean persistIssuers() {

        Issuer bonos = new Issuer();
        bonos.setCode("BONOS");

        Issuer udibono = new Issuer();
        udibono.setCode("UDIBONO");

        Issuer ivv = new Issuer();
        ivv.setCode("IVV");

        Issuer kimber = new Issuer();
        kimber.setCode("KIMBER");

        Issuer amd = new Issuer();
        amd.setCode("AMD");

        Issuer gfregio = new Issuer();
        gfregio.setCode("GFREGIO");

        Issuer f = new Issuer();
        f.setCode("F");

        Issuer tsla = new Issuer();
        tsla.setCode("TSLA");

        Issuer amzn = new Issuer();
        amzn.setCode("AMZN");

        ///Prueba 
        Issuer mxpi = new Issuer();
        mxpi.setCode("MXP");

        try {
            log.info("Persistiendo Issuers");
            em.persist(bonos);
            em.persist(udibono);
            em.persist(ivv);
            em.persist(kimber);
            em.persist(amd);
            em.persist(gfregio);
            em.persist(f);
            em.persist(tsla);
            em.persist(amzn);
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
        }

        return true;
    }

    private void persistInitialCurrency() {
        Currency mxpeso = new Currency();
        mxpeso.setComission(Boolean.FALSE);
        mxpeso.setIsin(null);
        mxpeso.setCurrencyDenomination(null);
        mxpeso.setIssuer(mxpi);
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setSeries("");
        mxpeso.setSettlementTimes(null);
        mxpeso.setTickSize(null);
        Ticker mxpesot = new Ticker();

        List<Ticker> lt = new LinkedList<>();
        lt.add(mxpesot);

        mxpesot.setTickerValue(curr.getCode() + "_" + mxpi.getCode() + "_" + mxpeso.getSeries());
        mxpeso.setTicker(lt);

        DenominatorCurrency mxpcd = new DenominatorCurrency();
        mxpcd.setCurrency(mxpeso);

        try {
            log.info("Persistimos el Currency inicial");
            em.persist(mxpesot);
            em.persist(mxpeso);
            em.persist(mxpcd);
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
        }
    }

    private void persistVectors() {
    }
    
    private void persist
}
