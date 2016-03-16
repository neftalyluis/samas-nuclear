/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.timers;

import java.util.Calendar;
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
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.beans.logic.BlotterRegistrer;
import mx.samas.ejb.beans.logic.StrategyBean;
import mx.samas.ejb.entities.Bank;
import mx.samas.ejb.entities.Bond;
import mx.samas.ejb.entities.Broker;
import mx.samas.ejb.entities.Client;
import mx.samas.ejb.entities.Currency;
import mx.samas.ejb.entities.DenominatorCurrency;
import mx.samas.ejb.entities.Equity;
import mx.samas.ejb.entities.Issuer;
import mx.samas.ejb.entities.PortfolioAccount;
import mx.samas.ejb.entities.PortfolioStatus;
import mx.samas.ejb.entities.PortfolioVector;
import mx.samas.ejb.entities.RiskProfile;
import mx.samas.ejb.entities.SecurityClass;
import mx.samas.ejb.entities.SliceVector;
import mx.samas.ejb.entities.SourceOwner;
import mx.samas.ejb.entities.Strategy;
import mx.samas.ejb.entities.TermStructure;
import mx.samas.ejb.entities.Transaction;

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
    private StrategyBean sgl;
    
    @EJB
    private BlotterRegistrer brl;
    
    private static final Logger log = Logger.getLogger(OnDeployBootstraping.class.getName());
    
    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        log.info("=================SAMAS Bootstrap=================");
        log.info("Persistiendo Entidades Iniciales");

        //Por ahora no se usaran estas entidades.
//        persistSecurityClasses();
        persistIssuers();
        persistClients();
        persistInitialCurrency();
        persistAssets();
        persistRiskProfile();
        persistStrategiesAndSlices();
        persistSourceOwners();
        persistTermStructure();
//        persistVectors();
        persistBank();
        persistPortfolioStatuses();
        persistPortfolioVectors();
        createBrokersAndCommisions();
        persistTransactions();
        depositMoneyToPortfolio();
        blotterEntries();
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
            log.log(Level.WARNING, "No pudimos persistir los SecurityClass, la excepcion es: {0}", e.getMessage());
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
            
            Issuer brsf = new Issuer();
            brsf.setCode("BRFS");
            
            Issuer lqd = new Issuer("LQS");
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
            em.persist(mxpi);
            em.persist(brsf);
            em.persist(lqd);
            
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir los Issuers, la excepcion es: {0}", e.getMessage());
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
            log.log(Level.WARNING, "No pudimos persistir los Clients, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
    
    private void persistInitialCurrency() {
        
        DenominatorCurrency mxpcd = new DenominatorCurrency();
        
        Currency mxpeso = new Currency();
        mxpeso.setCurrencyDenomination(mxpcd);
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setIssuer((Issuer) em.createNamedQuery("Issuer.findByCode").setParameter("code", "MXP").getSingleResult());
        mxpeso.setSeries("");
        mxpeso.setTicker("*C_MXP_");
        
        mxpcd.setCurrency(mxpeso);
        
        try {
            log.info("--Currency MXP");
            log.info("--Asset MXP");
            em.persist(mxpeso);
            em.persist(mxpcd);
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir el Currency Inicial, la excepcion es: {0}", e.getMessage());
        }
    }
    
    private boolean persistAssets() {
        
        Equity amzn = new Equity();
        amzn.setCurrencyDenomination(getMXPCurrency());
        amzn.setSecurityClass("1A");
        amzn.setIssuer(getIssuerByCode("AMZN"));
        amzn.setSeries("*");
        amzn.setName("AMAZON COM INC");
        amzn.setTicker("1A_AMZN_*");
        
        Equity tsla = new Equity();
        tsla.setCurrencyDenomination(getMXPCurrency());
        tsla.setSecurityClass("1A");
        tsla.setIssuer(getIssuerByCode("TSLA"));
        tsla.setSeries("*");
        tsla.setName("TESLA INC");
        tsla.setTicker("1A_TSLA_*");
        
        Equity fiat = new Equity();
        fiat.setCurrencyDenomination(getMXPCurrency());
        fiat.setSecurityClass("1A");
        fiat.setIssuer(getIssuerByCode("F"));
        fiat.setSeries("*");
        fiat.setName("FIAT SPA");
        fiat.setTicker("1A_F_*");
        
        Equity gfr = new Equity();
        gfr.setCurrencyDenomination(getMXPCurrency());
        gfr.setSecurityClass("1");
        gfr.setIssuer(getIssuerByCode("GFREGIO"));
        gfr.setSeries("O");
        gfr.setName("AF BANREGIO  S.A. DE C.V. SOFOM");
        gfr.setTicker("1_GFREGIO_O");
        
        Equity amd = new Equity();
        amd.setCurrencyDenomination(getMXPCurrency());
        amd.setSecurityClass("1A");
        amd.setIssuer(getIssuerByCode("AMD"));
        amd.setSeries("*");
        amd.setName("AMD");
        amd.setTicker("1A_AMD_*");
        
        Equity kimber = new Equity();
        kimber.setCurrencyDenomination(getMXPCurrency());
        kimber.setSecurityClass("1");
        kimber.setIssuer(getIssuerByCode("KIMBER"));
        kimber.setSeries("A");
        kimber.setName("KIMBERLY-CLARK DE MÉXICO S. A. B. DE C. V.");
        kimber.setTicker("1_KIMBER_A");
        
        Equity ivv = new Equity();
        ivv.setCurrencyDenomination(getMXPCurrency());
        ivv.setSecurityClass("1I");
        ivv.setIssuer(getIssuerByCode("IVV"));
        ivv.setSeries("*");
        ivv.setName("ISHARES CORE S&P 500 ETF");
        ivv.setTicker("1I_IVV_*");
        
        Bond udibono40 = new Bond();
        udibono40.setCurrencyDenomination(getMXPCurrency());
        udibono40.setSecurityClass("S");
        udibono40.setIssuer(getIssuerByCode("UDIBONO"));
        udibono40.setSeries("401115");
        udibono40.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono40.setTicker("S_UDIBONO_401115");
        
        Bond udibono22 = new Bond();
        udibono22.setCurrencyDenomination(getMXPCurrency());
        udibono22.setSecurityClass("S");
        udibono22.setIssuer(getIssuerByCode("UDIBONO"));
        udibono22.setSeries("220609");
        udibono22.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono22.setTicker("S_UDIBONO_220609");
        
        Bond udibono16 = new Bond();
        udibono16.setCurrencyDenomination(getMXPCurrency());
        udibono16.setSecurityClass("S");
        udibono16.setIssuer(getIssuerByCode("UDIBONO"));
        udibono16.setSeries("161215");
        udibono16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono16.setTicker("S_UDIBONO_160616");
        
        Bond bonos42 = new Bond();
        bonos42.setCurrencyDenomination(getMXPCurrency());
        bonos42.setSecurityClass("M");
        bonos42.setIssuer(getIssuerByCode("BONOS"));
        bonos42.setSeries("421113");
        bonos42.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos42.setTicker("M_BONOS_421113");
        
        Bond bonos24 = new Bond();
        bonos24.setCurrencyDenomination(getMXPCurrency());
        bonos24.setSecurityClass("M");
        bonos24.setIssuer(getIssuerByCode("BONOS"));
        bonos24.setSeries("241205");
        bonos24.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos24.setTicker("M_BONOS_241205");
        
        Bond bonos16 = new Bond();
        bonos16.setCurrencyDenomination(getMXPCurrency());
        bonos16.setSecurityClass("M");
        bonos16.setIssuer(getIssuerByCode("BONOS"));
        bonos16.setSeries("161215");
        bonos16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos16.setTicker("M_BONOS_161215");
        
        Equity brfs = new Equity();
        brfs.setCurrencyDenomination(getMXPCurrency());
        brfs.setSecurityClass("1A");
        brfs.setIssuer(getIssuerByCode("BRFS"));
        brfs.setSeries("N");
        brfs.setName("BRASIL FOODS SA");
        brfs.setTicker("1A_BRFS_N");
        
        Currency lqs = new Currency("Liquidez", "1", getIssuerByCode("LQS"), "1", false);
        
        try {
            
            em.persist(amzn);
            em.persist(tsla);
            em.persist(fiat);
            em.persist(gfr);
            em.persist(amd);
            em.persist(kimber);
            em.persist(ivv);
            em.persist(udibono16);
            em.persist(udibono22);
            em.persist(udibono40);
            em.persist(bonos16);
            em.persist(bonos24);
            em.persist(bonos42);
            em.persist(brfs);
            em.persist(lqs);
            
            log.info("--Equities y Bonds");
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir los Assets iniciales, la excepcion es: {0}", e.getMessage());
            return false;
        }
        
    }
    
    private boolean persistRiskProfile() {
        try {
            log.info("--RiskProfiles");
            
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
            log.log(Level.WARNING, "No pudimos persistir los RiskProfile, la excepcion es: {0}", e.getMessage());
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
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_161215").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_241205").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_421113").getSingleResult(), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_160616").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_220609").getSingleResult(), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "S_UDIBONO_401115").getSingleResult(), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_LQS.1").getSingleResult(), divydeu, 5.0));
            
            divydeu.setSlices(lsv);
            if (sgl.persistStrategy(divydeu)) {
                log.log(Level.INFO, "Estrategia :{0}", divydeu.getName());
            }

            //Estrategia Liquidez
            Strategy lqs = new Strategy();
            lqs.setName("Liquidez");
            lqs.setRiskProfile((RiskProfile) em.createNamedQuery("RiskProfile.findByName").setParameter("name", "Agresivo").getSingleResult());
            lsv.clear();
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMZN_*").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_TSLA_*").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_F_*").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1_GFREGIO_O").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMD_*").getSingleResult(), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_BRFS_N").getSingleResult(), divydeu, 20.0));
            lsv.add(new SliceVector(new Date(), (Bond) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "M_BONOS_421113").getSingleResult(), divydeu, 5.0));
            lqs.setSlices(lsv);
            if (sgl.persistStrategy(lqs)) {
                log.log(Level.INFO, "Estrategia: {0}", lqs.getName());
            }
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir los Strategies y Slices, la excepcion es: {0} ", e.getMessage());
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
            log.log(Level.WARNING, "No pudimos persistir los SourceOwners, la excepcion es: {0}", e.getMessage());
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
            log.log(Level.WARNING, "No pudimos persistir los TermStructure, la excepcion es: {0}", e.getMessage());
            
            return false;
        }
    }
    
    private boolean persistBank() {
        Bank b = new Bank();
        b.setName("HSBC");
        try {
            em.persist(b);
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir el Bank, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
    
    private boolean persistPortfolioStatuses() {
        PortfolioStatus ps = new PortfolioStatus();
        ps.setName("Active");
        
        PortfolioStatus ps1 = new PortfolioStatus();
        ps1.setName("Suspended");
        
        PortfolioStatus ps2 = new PortfolioStatus();
        ps2.setName("Liquidation");
        
        try {
            em.persist(ps);
            em.persist(ps1);
            em.persist(ps2);
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir los PortfolioStatus, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
    
    private boolean persistPortfolioVectors() {
        
        try {
            PortfolioAccount pa = new PortfolioAccount();
            pa.setAccountNumber("GYRFEMK_87654");
            pa.setActiveCommission(0.0);
            pa.setBank(getBank());
            pa.setDiscretionary(Boolean.FALSE);
            
            PortfolioVector pv = new PortfolioVector();
            pv.setAccount(pa);
            pv.setClients(getClients());
            pv.setDateTime(new Date());
            pv.setPortfolioStatus(getActiveStatus());
            pv.setStrategy(sgl.getStrategyByName("Dividendo y Deuda"));
            
            PortfolioAccount pal = new PortfolioAccount();
            pal.setAccountNumber("HGRFD_7654GHJ");
            pal.setActiveCommission(0.0);
            pal.setBank(getBank());
            pal.setDiscretionary(Boolean.FALSE);
            
            PortfolioVector pvl = new PortfolioVector();
            pvl.setAccount(pal);
            pvl.setClients(getClients());
            pvl.setDateTime(new Date());
            pvl.setPortfolioStatus(getActiveStatus());
            pvl.setStrategy(sgl.getStrategyByName("Liquidez"));
            
            em.persist(pa);
            em.persist(pv);
            em.persist(pal);
            em.persist(pvl);
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir los PortfolioVector, la excepcion es: {0}", e.getMessage());
            return false;
        }
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
            log.log(Level.WARNING, "No pudimos persistir el Broker, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
    
    private boolean persistTransactions() {
        
        List<Transaction> tl = new LinkedList<>();
//////////////////////////////////////////////CLIENT
        Transaction depositMoneyFromClient = new Transaction();
        depositMoneyFromClient.setName("Deposito en efectivo del Cliente");
        depositMoneyFromClient.setOpCash(new Long(1));
        depositMoneyFromClient.setOpQuantity(new Long(0));
        depositMoneyFromClient.setCredit(false);
        tl.add(depositMoneyFromClient);
        
        Transaction depositTitlesFromClient = new Transaction();
        depositTitlesFromClient.setName("Deposito en titulos del Cliente");
        depositTitlesFromClient.setOpCash(new Long(0));
        depositTitlesFromClient.setOpQuantity(new Long(1));
        depositTitlesFromClient.setCredit(false);
        tl.add(depositTitlesFromClient);
        
        Transaction withdrawalMoneyFromClient = new Transaction();
        withdrawalMoneyFromClient.setName("Retiro de Efectivo del Cliente");
        withdrawalMoneyFromClient.setOpCash(new Long(-1));
        withdrawalMoneyFromClient.setOpQuantity(new Long(0));
        withdrawalMoneyFromClient.setCredit(false);
        tl.add(withdrawalMoneyFromClient);
        
        Transaction withdrawalTitlesFromClient = new Transaction();
        withdrawalTitlesFromClient.setName("Retiro en titulos del Cliente");
        withdrawalTitlesFromClient.setOpCash(new Long(0));
        withdrawalTitlesFromClient.setOpQuantity(new Long(-1));
        withdrawalTitlesFromClient.setCredit(false);
        tl.add(withdrawalTitlesFromClient);

//////////////////////////////////////////////BUSINESS
        Transaction refundFromBusiness = new Transaction();
        refundFromBusiness.setName("Reembolso");
        refundFromBusiness.setOpCash(new Long(1));
        refundFromBusiness.setOpQuantity(new Long(0));
        refundFromBusiness.setCredit(false);
        tl.add(refundFromBusiness);
        
        Transaction comissionFromBusiness = new Transaction();
        comissionFromBusiness.setName("Comisión");
        comissionFromBusiness.setOpCash(new Long(-1));
        comissionFromBusiness.setOpQuantity(new Long(0));
        comissionFromBusiness.setCredit(false);
        tl.add(comissionFromBusiness);
        
        Transaction marginCallFromBusiness = new Transaction();
        marginCallFromBusiness.setName("Llamada de Margen");
        marginCallFromBusiness.setOpCash(new Long(0));
        marginCallFromBusiness.setOpQuantity(new Long(-1));
        marginCallFromBusiness.setCredit(false);
        tl.add(marginCallFromBusiness);
        
        Transaction marginCreditFromBusiness = new Transaction();
        marginCreditFromBusiness.setName("Credito Margen");
        marginCreditFromBusiness.setOpCash(new Long(1));
        marginCreditFromBusiness.setOpQuantity(new Long(0));
        marginCreditFromBusiness.setCredit(true);
        tl.add(marginCreditFromBusiness);
        
        Transaction incomingSecurityLendingFromBusiness = new Transaction();
        incomingSecurityLendingFromBusiness.setName("Prestamo de Valores Entrante");
        incomingSecurityLendingFromBusiness.setOpCash(new Long(0));
        incomingSecurityLendingFromBusiness.setOpQuantity(new Long(1));
        incomingSecurityLendingFromBusiness.setCredit(true);
        tl.add(incomingSecurityLendingFromBusiness);
        
        Transaction outgoingSecurityLendingFromBusiness = new Transaction();
        outgoingSecurityLendingFromBusiness.setName("Pestamo de valores Saliente");
        outgoingSecurityLendingFromBusiness.setOpCash(new Long(0));
        outgoingSecurityLendingFromBusiness.setOpQuantity(new Long(-1));
        outgoingSecurityLendingFromBusiness.setCredit(true);
        tl.add(outgoingSecurityLendingFromBusiness);
        
        Transaction amortizationMarginFromBusiness = new Transaction();
        amortizationMarginFromBusiness.setName("Amortización Margen");
        amortizationMarginFromBusiness.setOpCash(new Long(-1));
        amortizationMarginFromBusiness.setOpQuantity(new Long(0));
        amortizationMarginFromBusiness.setCredit(true);
        tl.add(amortizationMarginFromBusiness);
//////////////////////////////////////////////////////////////// BROKER

        Transaction refundFromBroker = new Transaction();
        refundFromBroker.setName("Reembolso de Correduría");
        refundFromBroker.setOpCash(new Long(1));
        refundFromBroker.setOpQuantity(new Long(0));
        refundFromBroker.setCredit(false);
        tl.add(refundFromBroker);
        
        Transaction refund2FromBroker = new Transaction();
        refund2FromBroker.setName("Reembolso");
        refund2FromBroker.setOpCash(new Long(0));
        refund2FromBroker.setOpQuantity(new Long(1));
        refund2FromBroker.setCredit(false);
        tl.add(refund2FromBroker);
        
        Transaction buyAsset = new Transaction();
        buyAsset.setName("Compra");
        buyAsset.setOpCash(new Long(-1));
        buyAsset.setOpQuantity(new Long(1));
        buyAsset.setCredit(false);
        tl.add(buyAsset);
        
        Transaction sellAsset = new Transaction();
        buyAsset.setName("Venta");
        buyAsset.setOpCash(new Long(1));
        buyAsset.setOpQuantity(new Long(-1));
        buyAsset.setCredit(false);
        tl.add(sellAsset);
        
        try {
            for (Transaction t : tl) {
                em.persist(t);
            }
            return true;
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir nuestra transaccion, la excepcion es: {0}", e.getMessage());
            return false;
        }
        
    }
    
    private void depositMoneyToPortfolio() {
        try {
            brl.depositMoney(10000000.0, "GYRFEMK_87654");
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos persistir nuestra operacion en el blotter, la excepcion es: {0}", e.getMessage());
            
        }
    }
    
    private Bank getBank() {
        try {
            return (Bank) em.createNamedQuery("Bank.findByName").setParameter("name", "HSBC").getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener el bank, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    private List<Client> getClients() {
        try {
            return em.createQuery("SELECT c FROM Client c").getResultList();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener la lista de clientes, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    private PortfolioStatus getActiveStatus() {
        try {
            return (PortfolioStatus) em.createNamedQuery("PortfolioStatus.active").setMaxResults(1).getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obetener el estatus Activo, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    private Broker getUniqueBroker() {
        try {
            return (Broker) em.createNamedQuery("Broker.getByName").setParameter("name", "HSBC").getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener nuestro broker, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    private PortfolioAccount getContract(String name) {
        try {
            return (PortfolioAccount) em.createQuery("SELECT pa FROM PortfolioAccount pa WHERE pa.accountNumber= :number").setParameter("number", name).getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener nuestro contrato, la excepcion es: {0}", e.getMessage());
            return null;
        }
        
    }

//    private void buyEquityAMZN() {
//        try {
//            Blotter b = new Blotter();
//            b.setAsset((Equity) em.createNamedQuery("Asset.findByTicker").setParameter("ticker", "1A_AMZN_*").getSingleResult());
//            b.setContract(getContract("GYRFEMK_87654"));
//            b.setBroker(getUniqueBroker());
//
//            b.setClientComission(0.0);
//            b.setBusinessComission(0.0);
//
//            b.setCashFlow(-400158.92);
//            b.setPrice(3304.45);
//
//            b.setQuantity(new Long(121));
//            b.setQuantityFlow(121.0);
//
//            b.setInputDate(saveDate(2013, 2, 25));
//            b.setSettlementDate(saveDate(2013, 2, 28));
//            b.setTradeDate(saveDate(2013, 2, 25));
//
//            b.setTransaction((Transaction) em.createQuery("SELECT t FROM Transaction t WHERE t.name= :name").setParameter("name", "Compra").setMaxResults(1).getSingleResult());
//            b.setTransactionSource((SourceOwner) em.createQuery("SELECT so FROM SourceOwner so WHERE so.name= :name").setParameter("name", "Portfolio").setMaxResults(1).getSingleResult());
//
//            em.persist(b);
//        } catch (Exception e) {
//            log.log(Level.WARNING, "No pudimos persistir nuestra operacion en el blotter, la excepcion es: {0}", e.getMessage());
//
//        }
//    }
    private Date saveDate(int y, int m, int d) {
        Calendar cal = Calendar.getInstance();
        // set the year,month and day to something else
        cal.set(y, m, d);
        return cal.getTime();
    }
    
    private Issuer getIssuerByCode(String code) {
        try {
            return (Issuer) em.createNamedQuery("Issuer.findByCode").setParameter("code", code).getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener el Issuer, la excepcion es: {0}", e.getMessage());
            return null;
        }
    }
    
    private DenominatorCurrency getMXPCurrency() {
        try {
            return (DenominatorCurrency) em.createNamedQuery("DenominatorCurrency.findByTicker").setParameter("ticker", "*C_MXP_").getSingleResult();
        } catch (Exception e) {
            log.log(Level.WARNING, "No pudimos obtener el Denominator Currency, la excepcion es: {0}", e.getMessage());
            
            return null;
        }
    }
    
    private void blotterEntries() {
        try {
            brl.buyEquity("1A_AMZN_*", 399839.05, new Long(121), "GYRFEMK_87654");
        } catch (AppException ex) {
            log.log(Level.WARNING, "No pudimos persistir los registros de la Bitacora, la excepcion es: {0}", ex.getMessage());
        }
    }
}
