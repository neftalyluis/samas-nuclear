/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

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
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.SecurityClass;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.entities.Ticker;

/**
 *
 * @author neftaly
 *
 * En este bean inicializamos los datos que necesita SAMAS para poder operar
 * desde cero, esto implica tambien subir los AssetVector de tiempo atrás y
 * algunos portafolios de prueba.
 */
@Stateless
public class OnDeployBootstraping {

    @PersistenceContext(unitName = "mx_samas_ejb_1.0PU")
    private EntityManager em;

    private static final Logger log = Logger.getLogger(OnDeployBootstraping.class.getName());

    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        log.info("=================SAMAS Bootstrap=================");
        log.info("Persistiendo Entidades Iniciales");
        persistSecurityClasses();
        persistIssuers();
        log.info("=================SAMAS Bootstrap=================");
        timer.cancel();
    }

    private boolean persistSecurityClasses() {

        try {
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

            log.info("--SecurityClasses");

            em.persist(s);
            em.persist(m);
            em.persist(unoi);
            em.persist(uno);
            em.persist(unoa);
            em.persist(curr);

            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistIssuers() {
        try {
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

            log.info("--Issuers");

            em.persist(bonos);
            em.persist(udibono);
            em.persist(ivv);
            em.persist(kimber);
            em.persist(amd);
            em.persist(gfregio);
            em.persist(f);
            em.persist(tsla);
            em.persist(amzn);

            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistAssets() {
        Bond udibono40 = new Bond();
        Bond udibono22 = new Bond();
        Bond udibono16 = new Bond();
        Bond bonos42 = new Bond();
        Bond bonos24 = new Bond();
        Bond bonos16 = new Bond();

        return true;
    }

    private void persistInitialCurrency() {
        Currency mxpeso = new Currency();
        mxpeso.setComission(Boolean.FALSE);
        mxpeso.setIsin(null);
        mxpeso.setCurrencyDenomination(null);
//        mxpeso.setIssuer(mxpi);
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setSeries("");
        mxpeso.setSettlementTimes(null);
        mxpeso.setTickSize(null);
        Ticker mxpesot = new Ticker();

        List<Ticker> lt = new LinkedList<>();
        lt.add(mxpesot);

//        mxpesot.setTickerValue(curr.getCode() + "_" + mxpi.getCode() + "_" + mxpeso.getSeries());
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

    private boolean persistRiskProfile() {
        try {

            RiskProfile conservador = new RiskProfile();
            conservador.setName("Conservador");
            RiskProfile balanceado = new RiskProfile();
            balanceado.setName("Balanceado");
            RiskProfile agresivo = new RiskProfile();
            agresivo.setName("Agresivo");

            em.persist(conservador);
            em.persist(balanceado);
            em.persist(agresivo);

            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistStrategiesAndSlices() {
        try {
            //Checate esta query super chingona, papu
            Strategy divydeu = new Strategy();
            divydeu.setName("Dividendo y Deuda");
            divydeu.setRiskProfile((RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Balanceado").getSingleResult());
            //No olvides dar de alta Aqui los slices
            
            //Checate esta query super chingona, papu
            Strategy lqs = new Strategy();
            lqs.setName("Liquidez");
            lqs.setRiskProfile((RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Agresivo").getSingleResult());
            //No olvides dar de alta Aqui los slices
            
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
}
