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
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.Timer;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.samas.ejb.beans.StrategyGeneratorLocal;
import mx.samas.ejb.entities.Bond;
import mx.samas.ejb.entities.Broker;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.Currency;
import mx.samas.ejb.entities.DenominatorCurrency;
import mx.samas.ejb.entities.Equity;
import mx.samas.ejb.entities.Issuer;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.SecurityClass;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.SourceOwner;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.entities.TermStructure;
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

    @EJB
    private StrategyGeneratorLocal sgl;

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
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setSeries("");
        mxpeso.setSettlementTimes(null);
        mxpeso.setTickSize(null);
        Ticker mxpesot = new Ticker();
        List<Ticker> lt = new LinkedList<>();
        lt.add(mxpesot);
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

    private Currency getMXPCurrency() {
        try {
            //FALTA CREAR TICKER
            return (Currency) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "*C_").getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    private void persistVectors() {
    }

    private boolean createBrokersAndCommisions() {
        try {
            //Checar el ambito de las comisiones, hay muchos campos y falta doncumentacion
            Broker HSBC = new Broker("HSBC");
//            BrokerCommission hsbcc = new BrokerCommission();
//            hsbcc.setBroker(hsbcc.getBroker().push(HSBC));
            em.persist(HSBC);
            return true;
        } catch (Exception e) {
            return false;
        }
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
            //Estrategia Dividendo y Deuda
            Strategy divydeu = new Strategy();
            divydeu.setName("Dividendo y Deuda");
            divydeu.setRiskProfile((RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Balanceado").getSingleResult());
            List<SliceVector> lsv = new LinkedList<>();
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMZN_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_TSLA_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_F_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_GFREGIO_O").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMD_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_KIMBER_A").getSingleResult(), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1I_IVV_*").getSingleResult(), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_16215").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_241205").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_421113").getSingleResult(), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_160616").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_220609").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_401115").getSingleResult(), divydeu, 10.0));
            divydeu.setSlices(lsv);
            if (sgl.persistStrategy(divydeu)) {
                log.log(Level.INFO, "Estrategia :{0}", divydeu.getName());
            }

            //Estrategia Liquidez
            Strategy lqs = new Strategy();
            lqs.setName("Liquidez");
            lqs.setRiskProfile((RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Agresivo").getSingleResult());
            lsv.clear();
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMZN_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_TSLA_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_F_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_GFREGIO_O").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMD_*").getSingleResult(), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_KIMBER_A").getSingleResult(), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1I_IVV_*").getSingleResult(), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_16215").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_241205").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_421113").getSingleResult(), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_160616").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_220609").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_401115").getSingleResult(), divydeu, 10.0));
            lqs.setSlices(lsv);
            if (sgl.persistStrategy(lqs)) {
                log.log(Level.INFO, "Estrategia: {0}", lqs.getName());
            }
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistClients() {
        try {
            Client j = new Client("Juan");
            Client a = new Client("Eduardo");
            Client e = new Client("Ricardo");
            em.persist(e);
            em.persist(a);
            em.persist(j);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean persistTermStructure() {
        try {
            TermStructure hoy = new TermStructure("HOY");
            TermStructure veinteycuatro = new TermStructure("24 HORAS");
            TermStructure cuarentayocho = new TermStructure("48 HORAS");
            TermStructure setentayocho = new TermStructure("72 HORAS");
            TermStructure noventayseis = new TermStructure("96 HORAS");
            TermStructure cientoveionte = new TermStructure("+120 HORAS");

            em.persist(hoy);
            em.persist(veinteycuatro);
            em.persist(cuarentayocho);
            em.persist(setentayocho);
            em.persist(noventayseis);
            em.persist(cientoveionte);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean persistSourceOwners() {
        try {
            SourceOwner bu = new SourceOwner("Business");
            SourceOwner cl = new SourceOwner("Client");
            SourceOwner po = new SourceOwner("Portfolio");
            SourceOwner br = new SourceOwner("Broker");
            SourceOwner ha = new SourceOwner("Treasury");

            em.persist(bu);
            em.persist(cl);
            em.persist(po);
            em.persist(br);
            em.persist(ha);

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
