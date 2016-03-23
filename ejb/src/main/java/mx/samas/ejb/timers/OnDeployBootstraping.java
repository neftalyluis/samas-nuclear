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
import mx.samas.ejb.beans.exceptions.AppException;
import mx.samas.ejb.beans.logic.AssetBean;
import mx.samas.ejb.beans.logic.BankBean;
import mx.samas.ejb.beans.logic.BlotterRegistrer;
import mx.samas.ejb.beans.logic.BrokerBean;
import mx.samas.ejb.beans.logic.ClientBean;
import mx.samas.ejb.beans.logic.CurrencyBean;
import mx.samas.ejb.beans.logic.IssuerBean;
import mx.samas.ejb.beans.logic.PortfolioAccountBean;
import mx.samas.ejb.beans.logic.PortfolioVectorBean;
import mx.samas.ejb.beans.logic.RiskProfileBean;
import mx.samas.ejb.beans.logic.SecurityClassBean;
import mx.samas.ejb.beans.logic.StrategyBean;
import mx.samas.ejb.beans.logic.TermStructureBean;
import mx.samas.ejb.beans.logic.TransactionBean;
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

    @EJB
    private IssuerBean ib;

    @EJB
    private CurrencyBean cb;

    @EJB
    private BankBean bb;

    @EJB
    private ClientBean clb;

    @EJB
    private PortfolioAccountBean pab;

    @EJB
    private BrokerBean brb;

    @EJB
    private SecurityClassBean scb;

    @EJB
    private AssetBean ab;

    @EJB
    private TransactionBean tb;

    @EJB
    private PortfolioVectorBean pve;

    @EJB
    private RiskProfileBean rpb;

    @EJB
    private TermStructureBean tsb;

    private static final Logger LOG = Logger.getLogger(OnDeployBootstraping.class.getName());

    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        LOG.info("=================SAMAS Bootstrap=================");
        LOG.info("Persistiendo Entidades Iniciales");

        persistIssuers();
        persistClients();
        persistInitialCurrency();
        persistAssets();
        persistRiskProfile();
        persistStrategiesAndSlices();
        persistSourceOwners();
        persistTermStructure();
        persistBank();
        persistPortfolioStatuses();
        persistPortfolioVectors();
        createBrokersAndCommisions();
        persistTransactions();
        blotterEntries();

        LOG.info("=================SAMAS Bootstrap=================");
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

            LOG.info("--SecurityClasses");

            scb.persistSecurityClass(s);
            scb.persistSecurityClass(m);
            scb.persistSecurityClass(unoi);
            scb.persistSecurityClass(uno);
            scb.persistSecurityClass(unoa);
            scb.persistSecurityClass(curr);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los SecurityClass, la excepcion es: {0}", e.getMessage());
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
            LOG.info("--Issuers");

            ib.persistIssuer(bonos);
            ib.persistIssuer(udibono);
            ib.persistIssuer(ivv);
            ib.persistIssuer(kimber);
            ib.persistIssuer(amd);
            ib.persistIssuer(gfregio);
            ib.persistIssuer(f);
            ib.persistIssuer(tsla);
            ib.persistIssuer(amzn);
            ib.persistIssuer(mxpi);
            ib.persistIssuer(brsf);
            ib.persistIssuer(lqd);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Issuers, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistClients() {
        try {
            Client j = new Client("Juan");
            Client a = new Client("Eduardo");
            Client e = new Client("Ricardo");
            clb.persistClient(e);
            clb.persistClient(a);
            clb.persistClient(j);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Clients, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private void persistInitialCurrency() {

        DenominatorCurrency mxpcd = new DenominatorCurrency();

        Currency mxpeso = new Currency();
        mxpeso.setCurrencyDenomination(mxpcd);
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setIssuer(ib.getIssuerByCode("MXP"));
        mxpeso.setSeries("");
        mxpeso.setTicker("*C_MXP_");

        mxpcd.setCurrency(mxpeso);

        try {
            LOG.info("--Currency MXP");
            LOG.info("--Asset MXP");
            cb.persistCurrency(mxpeso);
            cb.persistDenominator(mxpcd);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Currency Inicial, la excepcion es: {0}", e.getMessage());
        }
    }

    private boolean persistAssets() {

        Equity amzn = new Equity();
        amzn.setCurrencyDenomination(cb.getMXPCurrency());
        amzn.setSecurityClass("1A");
        amzn.setIssuer(ib.getIssuerByCode("AMZN"));
        amzn.setSeries("*");
        amzn.setName("AMAZON COM INC");
        amzn.setTicker("1A_AMZN_*");

        Equity tsla = new Equity();
        tsla.setCurrencyDenomination(cb.getMXPCurrency());
        tsla.setSecurityClass("1A");
        tsla.setIssuer(ib.getIssuerByCode("TSLA"));
        tsla.setSeries("*");
        tsla.setName("TESLA INC");
        tsla.setTicker("1A_TSLA_*");

        Equity fiat = new Equity();
        fiat.setCurrencyDenomination(cb.getMXPCurrency());
        fiat.setSecurityClass("1A");
        fiat.setIssuer(ib.getIssuerByCode("F"));
        fiat.setSeries("*");
        fiat.setName("FIAT SPA");
        fiat.setTicker("1A_F_*");

        Equity gfr = new Equity();
        gfr.setCurrencyDenomination(cb.getMXPCurrency());
        gfr.setSecurityClass("1");
        gfr.setIssuer(ib.getIssuerByCode("GFREGIO"));
        gfr.setSeries("O");
        gfr.setName("AF BANREGIO  S.A. DE C.V. SOFOM");
        gfr.setTicker("1_GFREGIO_O");

        Equity amd = new Equity();
        amd.setCurrencyDenomination(cb.getMXPCurrency());
        amd.setSecurityClass("1A");
        amd.setIssuer(ib.getIssuerByCode("AMD"));
        amd.setSeries("*");
        amd.setName("AMD");
        amd.setTicker("1A_AMD_*");

        Equity kimber = new Equity();
        kimber.setCurrencyDenomination(cb.getMXPCurrency());
        kimber.setSecurityClass("1");
        kimber.setIssuer(ib.getIssuerByCode("KIMBER"));
        kimber.setSeries("A");
        kimber.setName("KIMBERLY-CLARK DE MÉXICO S. A. B. DE C. V.");
        kimber.setTicker("1_KIMBER_A");

        Equity ivv = new Equity();
        ivv.setCurrencyDenomination(cb.getMXPCurrency());
        ivv.setSecurityClass("1I");
        ivv.setIssuer(ib.getIssuerByCode("IVV"));
        ivv.setSeries("*");
        ivv.setName("ISHARES CORE S&P 500 ETF");
        ivv.setTicker("1I_IVV_*");

        Bond udibono40 = new Bond();
        udibono40.setCurrencyDenomination(cb.getMXPCurrency());
        udibono40.setSecurityClass("S");
        udibono40.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono40.setSeries("401115");
        udibono40.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono40.setTicker("S_UDIBONO_401115");

        Bond udibono22 = new Bond();
        udibono22.setCurrencyDenomination(cb.getMXPCurrency());
        udibono22.setSecurityClass("S");
        udibono22.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono22.setSeries("220609");
        udibono22.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono22.setTicker("S_UDIBONO_220609");

        Bond udibono16 = new Bond();
        udibono16.setCurrencyDenomination(cb.getMXPCurrency());
        udibono16.setSecurityClass("S");
        udibono16.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono16.setSeries("161215");
        udibono16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono16.setTicker("S_UDIBONO_160616");

        Bond bonos42 = new Bond();
        bonos42.setCurrencyDenomination(cb.getMXPCurrency());
        bonos42.setSecurityClass("M");
        bonos42.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos42.setSeries("421113");
        bonos42.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos42.setTicker("M_BONOS_421113");

        Bond bonos24 = new Bond();
        bonos24.setCurrencyDenomination(cb.getMXPCurrency());
        bonos24.setSecurityClass("M");
        bonos24.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos24.setSeries("241205");
        bonos24.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos24.setTicker("M_BONOS_241205");

        Bond bonos16 = new Bond();
        bonos16.setCurrencyDenomination(cb.getMXPCurrency());
        bonos16.setSecurityClass("M");
        bonos16.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos16.setSeries("161215");
        bonos16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos16.setTicker("M_BONOS_161215");

        Equity brfs = new Equity();
        brfs.setCurrencyDenomination(cb.getMXPCurrency());
        brfs.setSecurityClass("1A");
        brfs.setIssuer(ib.getIssuerByCode("BRFS"));
        brfs.setSeries("N");
        brfs.setName("BRASIL FOODS SA");
        brfs.setTicker("1A_BRFS_N");

        Currency lqs = new Currency("Liquidez", "1", ib.getIssuerByCode("LQS"), "1", false);

        try {

            ab.persistAsset(amzn);
            ab.persistAsset(tsla);
            ab.persistAsset(fiat);
            ab.persistAsset(gfr);
            ab.persistAsset(amd);
            ab.persistAsset(kimber);
            ab.persistAsset(ivv);
            ab.persistAsset(udibono16);
            ab.persistAsset(udibono22);
            ab.persistAsset(udibono40);
            ab.persistAsset(bonos16);
            ab.persistAsset(bonos24);
            ab.persistAsset(bonos42);
            ab.persistAsset(brfs);
            ab.persistAsset(lqs);

            LOG.info("--Equities y Bonds");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Assets iniciales, la excepcion es: {0}", e.getMessage());
            return false;
        }

    }

    private boolean persistRiskProfile() {
        try {
            LOG.info("--RiskProfiles");

            RiskProfile conservador = new RiskProfile();
            conservador.setName("Conservador");
            RiskProfile balanceado = new RiskProfile();
            balanceado.setName("Balanceado");
            RiskProfile agresivo = new RiskProfile();
            agresivo.setName("Agresivo");

            rpb.persistRiskProfile(conservador);
            rpb.persistRiskProfile(balanceado);
            rpb.persistRiskProfile(agresivo);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los RiskProfile, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistStrategiesAndSlices() {
        try {
            //Estrategia Dividendo y Deuda
            Strategy divydeu = new Strategy();
            divydeu.setName("Dividendo y Deuda");
            divydeu.setRiskProfile(rpb.findByName("Balanceado"));
            List<SliceVector> lsv = new LinkedList<>();
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_AMZN_*"), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_TSLA_*"), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_F_*"), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1_GFREGIO_O"), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_AMD_*"), divydeu, 4.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1_KIMBER_A"), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1I_IVV_*"), divydeu, 7.5));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("M_BONOS_161215"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("M_BONOS_241205"), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("M_BONOS_421113"), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("S_UDIBONO_160616"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("S_UDIBONO_220609"), divydeu, 10.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("S_UDIBONO_401115"), divydeu, 5.0));
            lsv.add(new SliceVector(new Date(), (Currency) ab.findByTicker("1_LQS_1"), divydeu, 5.0));

            divydeu.setSlices(lsv);
            if (sgl.persistStrategy(divydeu)) {
                LOG.log(Level.INFO, "Estrategia :{0}", divydeu.getName());
            }

            //Estrategia Liquidez
            Strategy lqs = new Strategy();
            lqs.setName("Liquidez");
            lqs.setRiskProfile(rpb.findByName("Agresivo"));

            lsv.clear();
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_AMZN_*"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_TSLA_*"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_F_*"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1_GFREGIO_O"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_AMD_*"), divydeu, 15.0));
            lsv.add(new SliceVector(new Date(), (Equity) ab.findByTicker("1A_BRFS_N"), divydeu, 20.0));
            lsv.add(new SliceVector(new Date(), (Bond) ab.findByTicker("M_BONOS_421113"), divydeu, 5.0));
            lqs.setSlices(lsv);
            if (sgl.persistStrategy(lqs)) {
                LOG.log(Level.INFO, "Estrategia: {0}", lqs.getName());
            }
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Strategies y Slices, la excepcion es: {0} ", e.getMessage());
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

            tb.persistOwner(bu);
            tb.persistOwner(cl);
            tb.persistOwner(po);
            tb.persistOwner(br);
            tb.persistOwner(ha);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los SourceOwners, la excepcion es: {0}", e.getMessage());
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

            tsb.persistTermStructure(hoy);
            tsb.persistTermStructure(veinteycuatro);
            tsb.persistTermStructure(cuarentayocho);
            tsb.persistTermStructure(setentayocho);
            tsb.persistTermStructure(noventayseis);
            tsb.persistTermStructure(cientoveionte);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los TermStructure, la excepcion es: {0}", e.getMessage());

            return false;
        }
    }

    private boolean persistBank() {
        Bank b = new Bank();
        b.setName("HSBC");
        try {
            bb.persistBank(b);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Bank, la excepcion es: {0}", e.getMessage());
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
            pab.persistPortfolioStatus(ps);
            pab.persistPortfolioStatus(ps1);
            pab.persistPortfolioStatus(ps2);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los PortfolioStatus, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistPortfolioVectors() {

        try {
            PortfolioAccount pa = new PortfolioAccount();
            pa.setAccountNumber("GYRFEMK_87654");
            pa.setActiveCommission(0.0);
            pa.setBank(bb.getBankByName("HSBC"));
            pa.setDiscretionary(Boolean.FALSE);

            PortfolioVector pv = new PortfolioVector();
            pv.setAccount(pa);
            pv.setClients(clb.getAllClients());
            pv.setDateTime(new Date());
            pv.setPortfolioStatus(pab.getActiveStatus());
            pv.setStrategy(sgl.getStrategyByName("Dividendo y Deuda"));

            PortfolioAccount pal = new PortfolioAccount();
            pal.setAccountNumber("HGRFD_7654GHJ");
            pal.setActiveCommission(0.0);
            pal.setBank(bb.getBankByName("HSBC"));
            pal.setDiscretionary(Boolean.FALSE);

            PortfolioVector pvl = new PortfolioVector();
            pvl.setAccount(pal);
            pvl.setClients(clb.getAllClients());
            pvl.setDateTime(new Date());
            pvl.setPortfolioStatus(pab.getActiveStatus());
            pvl.setStrategy(sgl.getStrategyByName("Liquidez"));

            pab.persistPortfolioAccount(pa);
            pve.persistPortfolioVector(pv);
            pab.persistPortfolioAccount(pal);
            pve.persistPortfolioVector(pvl);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los PortfolioVector, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean createBrokersAndCommisions() {
        try {
            //Checar el ambito de las comisiones, hay muchos campos y falta doncumentacion
            Broker HSBC = new Broker("HSBC");
//            BrokerCommission hsbcc = new BrokerCommission();
//            hsbcc.setBroker(hsbcc.getBroker().push(HSBC));
            brb.persistBroker(HSBC);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Broker, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistTransactions() {

        List<SourceOwner> lso = new LinkedList<>();

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
        sellAsset.setName("Venta");
        sellAsset.setOpCash(new Long(1));
        sellAsset.setOpQuantity(new Long(-1));
        sellAsset.setCredit(false);
        tl.add(sellAsset);

        Transaction inicioReporto = new Transaction();
        inicioReporto.setOpQuantity(new Long(1));
        inicioReporto.setOpCash(new Long(-1));
        inicioReporto.setName("Inicio de Reporto");
        inicioReporto.setCredit(true);
        tl.add(inicioReporto);

        Transaction finReporto = new Transaction();
        finReporto.setCredit(true);
        finReporto.setName("Fin de Reporto");
        finReporto.setOpCash(new Long(1));
        finReporto.setOpQuantity(new Long(-1));
        tl.add(finReporto);

        try {
            tb.persistTransactionList(tl);
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir nuestra transaccion, la excepcion es: {0}", e.getMessage());
            return false;
        }

    }

    private void blotterEntries() {
        try {
            brl.depositMoney(10000000.0, "GYRFEMK_87654");
            brl.buyEquity("1A_AMZN_*", 399839.05, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1A_TSLA_*", 399572.65, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1A_F_*", 399951.19, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1_GFREGIO_O", 399961.00, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1A_AMD_*", 399981.67, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1_KIMBER_A", 749978.82, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("1I_IVV_*", 748447.03, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("M_BONOS_161215", 1499963.19, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("M_BONOS_241205", 999878.70, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("M_BONOS_421113", 499887.72, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("S_UDIBONO_160616", 1499792.23, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("S_UDIBONO_220609", 999555.37, new Long(121), "GYRFEMK_87654");
            brl.buyEquity("S_UDIBONO_401115", 499519.90, new Long(121), "GYRFEMK_87654");

        } catch (AppException ex) {
            LOG.log(Level.WARNING, "No pudimos persistir los registros de la Bitacora, la excepcion es: {0}", ex.getMessage());
        }
    }
    
    private void closingDay(){
        
    }
}
