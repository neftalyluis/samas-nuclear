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
import mx.samas.ejb.beans.logic.AssetBean;
import mx.samas.ejb.beans.logic.BankBean;
import mx.samas.ejb.beans.logic.BlotterRegistrer;
import mx.samas.ejb.beans.logic.BrokerBean;
import mx.samas.ejb.beans.logic.ClientBean;
import mx.samas.ejb.beans.logic.ClosingProcessBean;
import mx.samas.ejb.beans.logic.CurrencyBean;
import mx.samas.ejb.beans.logic.IssuerBean;
import mx.samas.ejb.beans.logic.PortfolioAccountBean;
import mx.samas.ejb.beans.logic.PortfolioVectorBean;
import mx.samas.ejb.beans.logic.RiskProfileBean;
import mx.samas.ejb.beans.logic.SecurityClassBean;
import mx.samas.ejb.beans.logic.StrategyBean;
import mx.samas.ejb.beans.logic.TermStructureBean;
import mx.samas.ejb.beans.logic.TransactionBean;
import mx.samas.ejb.beans.logic.UserBean;
import mx.samas.ejb.entities.Activo;
import mx.samas.ejb.entities.TipoActivo;
import mx.samas.ejb.entities.Banco;
import mx.samas.ejb.entities.Corredor;
import mx.samas.ejb.entities.Cliente;
import mx.samas.ejb.entities.DenominacionMoneda;
import mx.samas.ejb.entities.Emisor;
import mx.samas.ejb.entities.PortafolioCuenta;
import mx.samas.ejb.entities.PortafolioEstatus;
import mx.samas.ejb.entities.VectorPortafolio;
import mx.samas.ejb.entities.PerfilRiesgo;
import mx.samas.ejb.entities.TipoValor;
import mx.samas.ejb.entities.VectorPortafolioModelo;
import mx.samas.ejb.entities.DuenoFuente;
import mx.samas.ejb.entities.Estrategia;
import mx.samas.ejb.entities.EstructuraTasas;
import mx.samas.ejb.entities.Transaccion;
import mx.samas.ejb.entities.Usuario;

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

    @EJB
    private ClosingProcessBean cpb;

    @EJB
    private UserBean ub;

    private static final Logger LOG = Logger.getLogger(OnDeployBootstraping.class.getName());

    @Schedule(hour = "*", minute = "*", persistent = false)
    protected void init(Timer timer) {
        LOG.info("=================SAMAS Bootstrap=================");
        LOG.info("Persistiendo Entidades Iniciales");
        
//        test();

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
        closingDay();

        users();
        LOG.info("=================SAMAS Bootstrap=================");
        timer.cancel();
    }

    private boolean persistSecurityClasses() {
        try {
            TipoValor s = new TipoValor();
            s.setAssetType("Bond");
            s.setCode("S");
            s.setDescription("Udibonos");

            TipoValor m = new TipoValor();
            m.setAssetType("Bond");
            m.setCode("M");
            m.setDescription("Bonos de Gobierno Federal Tasa Fija");

            TipoValor unoi = new TipoValor();
            unoi.setAssetType("Equity");
            unoi.setCode("1I");
            unoi.setDescription("Tracks Extranjeros");

            TipoValor uno = new TipoValor();
            uno.setAssetType("Equity");
            uno.setCode("1");
            uno.setDescription("Acciones Industriales, Comerico y de Servicios");

            TipoValor unoa = new TipoValor();
            unoa.setAssetType("Equity");
            unoa.setCode("1A");
            unoa.setDescription("Acciones del sistema Internal. de cotizaciones");

            TipoValor curr = new TipoValor();
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
            Emisor bonos = new Emisor();
            bonos.setCode("BONOS");

            Emisor udibono = new Emisor();
            udibono.setCode("UDIBONO");

            Emisor ivv = new Emisor();
            ivv.setCode("IVV");

            Emisor kimber = new Emisor();
            kimber.setCode("KIMBER");

            Emisor amd = new Emisor();
            amd.setCode("AMD");

            Emisor gfregio = new Emisor();
            gfregio.setCode("GFREGIO");

            Emisor f = new Emisor();
            f.setCode("F");

            Emisor tsla = new Emisor();
            tsla.setCode("TSLA");

            Emisor amzn = new Emisor();
            amzn.setCode("AMZN");

            ///Prueba 
            Emisor mxpi = new Emisor();
            mxpi.setCode("MXP");

            Emisor brsf = new Emisor();
            brsf.setCode("BRFS");

            Emisor lqd = new Emisor("LQS");
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
            Cliente j = new Cliente("Juan");
            Cliente a = new Cliente("Eduardo");
            Cliente e = new Cliente("Ricardo");
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

        DenominacionMoneda mxpcd = new DenominacionMoneda();

        Activo mxpeso = new Activo();
        mxpeso.setType(TipoActivo.CURRENCY);
        mxpeso.setCurrencyDenomination(mxpcd);
        mxpeso.setName("Peso Mexicano");
        mxpeso.setSecurityClass("*C");
        mxpeso.setIssuer(ib.getIssuerByCode("MXP"));
        mxpeso.setSeries("");
        mxpeso.setTicker("*C_MXP_");

        mxpcd.setCurrency(mxpeso);

        try {
            LOG.info("--Asset MXP");
            cb.persistCurrency(mxpeso);
            LOG.info("--Currency MXP");
            cb.persistDenominator(mxpcd);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Currency Inicial, la excepcion es: {0}", e.getMessage());
        }
    }

    private boolean persistAssets() {

        Activo amzn = new Activo();
        amzn.setType(TipoActivo.EQUITY);
        amzn.setCurrencyDenomination(cb.getMXPCurrency());
        amzn.setSecurityClass("1A");
        LOG.info("KHE");
        amzn.setIssuer(ib.getIssuerByCode("AMZN"));
        amzn.setSeries("*");
        amzn.setName("AMAZON COM INC");
        amzn.setTicker("1A_AMZN_*");

        Activo tsla = new Activo();
        tsla.setType(TipoActivo.EQUITY);
        tsla.setCurrencyDenomination(cb.getMXPCurrency());
        tsla.setSecurityClass("1A");
        tsla.setIssuer(ib.getIssuerByCode("TSLA"));
        tsla.setSeries("*");
        tsla.setName("TESLA INC");
        tsla.setTicker("1A_TSLA_*");

        Activo fiat = new Activo();
        fiat.setType(TipoActivo.EQUITY);
        fiat.setCurrencyDenomination(cb.getMXPCurrency());
        fiat.setSecurityClass("1A");
        fiat.setIssuer(ib.getIssuerByCode("F"));
        fiat.setSeries("*");
        fiat.setName("FIAT SPA");
        fiat.setTicker("1A_F_*");

        Activo gfr = new Activo();
        gfr.setType(TipoActivo.EQUITY);
        gfr.setCurrencyDenomination(cb.getMXPCurrency());
        gfr.setSecurityClass("1");
        gfr.setIssuer(ib.getIssuerByCode("GFREGIO"));
        gfr.setSeries("O");
        gfr.setName("AF BANREGIO  S.A. DE C.V. SOFOM");
        gfr.setTicker("1_GFREGIO_O");

        Activo amd = new Activo();
        amd.setType(TipoActivo.EQUITY);
        amd.setCurrencyDenomination(cb.getMXPCurrency());
        amd.setSecurityClass("1A");
        amd.setIssuer(ib.getIssuerByCode("AMD"));
        amd.setSeries("*");
        amd.setName("AMD");
        amd.setTicker("1A_AMD_*");

        Activo kimber = new Activo();
        kimber.setType(TipoActivo.EQUITY);
        kimber.setCurrencyDenomination(cb.getMXPCurrency());
        kimber.setSecurityClass("1");
        kimber.setIssuer(ib.getIssuerByCode("KIMBER"));
        kimber.setSeries("A");
        kimber.setName("KIMBERLY-CLARK DE MÉXICO S. A. B. DE C. V.");
        kimber.setTicker("1_KIMBER_A");

        Activo ivv = new Activo();
        ivv.setType(TipoActivo.EQUITY);
        ivv.setCurrencyDenomination(cb.getMXPCurrency());
        ivv.setSecurityClass("1I");
        ivv.setIssuer(ib.getIssuerByCode("IVV"));
        ivv.setSeries("*");
        ivv.setName("ISHARES CORE S&P 500 ETF");
        ivv.setTicker("1I_IVV_*");

        Activo udibono40 = new Activo();
        udibono40.setType(TipoActivo.BOND);
        udibono40.setCurrencyDenomination(cb.getMXPCurrency());
        udibono40.setSecurityClass("S");
        udibono40.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono40.setSeries("401115");
        udibono40.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono40.setTicker("S_UDIBONO_401115");

        Activo udibono22 = new Activo();
        udibono22.setType(TipoActivo.BOND);
        udibono22.setCurrencyDenomination(cb.getMXPCurrency());
        udibono22.setSecurityClass("S");
        udibono22.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono22.setSeries("220609");
        udibono22.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono22.setTicker("S_UDIBONO_220609");

        Activo udibono16 = new Activo();
        udibono16.setType(TipoActivo.BOND);
        udibono16.setCurrencyDenomination(cb.getMXPCurrency());
        udibono16.setSecurityClass("S");
        udibono16.setIssuer(ib.getIssuerByCode("UDIBONO"));
        udibono16.setSeries("161215");
        udibono16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono16.setTicker("S_UDIBONO_160616");

        Activo bonos42 = new Activo();
        bonos42.setType(TipoActivo.BOND);
        bonos42.setCurrencyDenomination(cb.getMXPCurrency());
        bonos42.setSecurityClass("M");
        bonos42.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos42.setSeries("421113");
        bonos42.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos42.setTicker("M_BONOS_421113");

        Activo bonos24 = new Activo();
        bonos24.setType(TipoActivo.BOND);
        bonos24.setCurrencyDenomination(cb.getMXPCurrency());
        bonos24.setSecurityClass("M");
        bonos24.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos24.setSeries("241205");
        bonos24.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos24.setTicker("M_BONOS_241205");

        Activo bonos16 = new Activo();
        bonos16.setType(TipoActivo.BOND);
        bonos16.setCurrencyDenomination(cb.getMXPCurrency());
        bonos16.setSecurityClass("M");
        bonos16.setIssuer(ib.getIssuerByCode("BONOS"));
        bonos16.setSeries("161215");
        bonos16.setName("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos16.setTicker("M_BONOS_161215");

        Activo brfs = new Activo();
        brfs.setType(TipoActivo.EQUITY);
        brfs.setCurrencyDenomination(cb.getMXPCurrency());
        brfs.setSecurityClass("1A");
        brfs.setIssuer(ib.getIssuerByCode("BRFS"));
        brfs.setSeries("N");
        brfs.setName("BRASIL FOODS SA");
        brfs.setTicker("1A_BRFS_N");

        Activo lqs = new Activo("Liquidez", "1", ib.getIssuerByCode("LQS"), "1", false);
        lqs.setType(TipoActivo.EQUITY);

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

            PerfilRiesgo conservador = new PerfilRiesgo();
            conservador.setName("Conservador");
            PerfilRiesgo balanceado = new PerfilRiesgo();
            balanceado.setName("Balanceado");
            PerfilRiesgo agresivo = new PerfilRiesgo();
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
            Estrategia divydeu = new Estrategia();
            divydeu.setName("Dividendo y Deuda");
            divydeu.setRiskProfile(rpb.findByName("Balanceado"));
            List<VectorPortafolioModelo> lsv = new LinkedList<>();
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMZN_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_TSLA_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_F_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1_GFREGIO_O"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMD_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1_KIMBER_A"), divydeu, 7.5));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1I_IVV_*"), divydeu, 7.5));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("M_BONOS_161215"), divydeu, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("M_BONOS_241205"), divydeu, 10.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("M_BONOS_421113"), divydeu, 5.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("S_UDIBONO_160616"), divydeu, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("S_UDIBONO_220609"), divydeu, 10.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("S_UDIBONO_401115"), divydeu, 5.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1_LQS_1"), divydeu, 5.0));

            divydeu.setSlices(lsv);
            if (sgl.persistStrategy(divydeu)) {
                LOG.log(Level.INFO, "Estrategia :{0}", divydeu.getName());
            }

            //Estrategia Liquidez
            Estrategia lqs = new Estrategia();
            lqs.setName("Liquidez");
            lqs.setRiskProfile(rpb.findByName("Agresivo"));

            lsv.clear();
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMZN_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_TSLA_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_F_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1_GFREGIO_O"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMD_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_BRFS_N"), lqs, 20.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("M_BONOS_421113"), lqs, 5.0));
            lqs.setSlices(lsv);
            if (sgl.persistStrategy(lqs)) {
                LOG.log(Level.INFO, "Estrategia: {0}", lqs.getName());
            }

            lsv.clear();

            Calendar c = Calendar.getInstance();
            c.add(Calendar.MONTH, 10);
            lsv.add(new VectorPortafolioModelo(c.getTime(), ab.findByTicker("1A_AMZN_*"), lqs, 100.0));
            sgl.updateStrategy(lqs.getId(), lsv);

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Strategies y Slices, la excepcion es: {0} ", e.getMessage());
            return false;
        }
    }

    private boolean persistSourceOwners() {
        try {
            DuenoFuente bu = new DuenoFuente("Business");
            DuenoFuente cl = new DuenoFuente("Client");
            DuenoFuente po = new DuenoFuente("Portfolio");
            DuenoFuente br = new DuenoFuente("Broker");
            DuenoFuente ha = new DuenoFuente("Treasury");

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
            EstructuraTasas hoy = new EstructuraTasas("HOY");
            EstructuraTasas veinteycuatro = new EstructuraTasas("24 HORAS");
            EstructuraTasas cuarentayocho = new EstructuraTasas("48 HORAS");
            EstructuraTasas setentayocho = new EstructuraTasas("72 HORAS");
            EstructuraTasas noventayseis = new EstructuraTasas("96 HORAS");
            EstructuraTasas cientoveionte = new EstructuraTasas("+120 HORAS");

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
        Banco b = new Banco();
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
        PortafolioEstatus ps = new PortafolioEstatus();
        ps.setName("Active");

        PortafolioEstatus ps1 = new PortafolioEstatus();
        ps1.setName("Suspended");

        PortafolioEstatus ps2 = new PortafolioEstatus();
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
            PortafolioCuenta pa = new PortafolioCuenta();
            pa.setAccountNumber("GYRFEMK_87654");
            pa.setActiveCommission(0.0);
            pa.setBank(bb.getBankByName("HSBC"));
            pa.setDiscretionary(Boolean.FALSE);

            VectorPortafolio pv = new VectorPortafolio();
            pv.setAccount(pa);
            pv.setClients(clb.getAllClients());
            pv.setDateTime(new Date());
            pv.setPortfolioStatus(pab.getActiveStatus());
            pv.setStrategy(sgl.getStrategyByName("Dividendo y Deuda"));

            PortafolioCuenta pal = new PortafolioCuenta();
            pal.setAccountNumber("HGRFD_7654GHJ");
            pal.setActiveCommission(0.0);
            pal.setBank(bb.getBankByName("HSBC"));
            pal.setDiscretionary(Boolean.FALSE);

            VectorPortafolio pvl = new VectorPortafolio();
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
            Corredor HSBC = new Corredor("HSBC");
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

        List<DuenoFuente> lso = new LinkedList<>();

        List<Transaccion> tl = new LinkedList<>();
//////////////////////////////////////////////CLIENT
        Transaccion depositMoneyFromClient = new Transaccion();
        depositMoneyFromClient.setName("Deposito en efectivo del Cliente");
        depositMoneyFromClient.setOpCash(new Long(1));
        depositMoneyFromClient.setOpQuantity(new Long(0));
        depositMoneyFromClient.setCredit(false);
        tl.add(depositMoneyFromClient);

        Transaccion depositTitlesFromClient = new Transaccion();
        depositTitlesFromClient.setName("Deposito en titulos del Cliente");
        depositTitlesFromClient.setOpCash(new Long(0));
        depositTitlesFromClient.setOpQuantity(new Long(1));
        depositTitlesFromClient.setCredit(false);
        tl.add(depositTitlesFromClient);

        Transaccion withdrawalMoneyFromClient = new Transaccion();
        withdrawalMoneyFromClient.setName("Retiro de Efectivo del Cliente");
        withdrawalMoneyFromClient.setOpCash(new Long(-1));
        withdrawalMoneyFromClient.setOpQuantity(new Long(0));
        withdrawalMoneyFromClient.setCredit(false);
        tl.add(withdrawalMoneyFromClient);

        Transaccion withdrawalTitlesFromClient = new Transaccion();
        withdrawalTitlesFromClient.setName("Retiro en titulos del Cliente");
        withdrawalTitlesFromClient.setOpCash(new Long(0));
        withdrawalTitlesFromClient.setOpQuantity(new Long(-1));
        withdrawalTitlesFromClient.setCredit(false);
        tl.add(withdrawalTitlesFromClient);

//////////////////////////////////////////////BUSINESS
        Transaccion refundFromBusiness = new Transaccion();
        refundFromBusiness.setName("Reembolso");
        refundFromBusiness.setOpCash(new Long(1));
        refundFromBusiness.setOpQuantity(new Long(0));
        refundFromBusiness.setCredit(false);
        tl.add(refundFromBusiness);

        Transaccion comissionFromBusiness = new Transaccion();
        comissionFromBusiness.setName("Comisión");
        comissionFromBusiness.setOpCash(new Long(-1));
        comissionFromBusiness.setOpQuantity(new Long(0));
        comissionFromBusiness.setCredit(false);
        tl.add(comissionFromBusiness);

        Transaccion marginCallFromBusiness = new Transaccion();
        marginCallFromBusiness.setName("Llamada de Margen");
        marginCallFromBusiness.setOpCash(new Long(0));
        marginCallFromBusiness.setOpQuantity(new Long(-1));
        marginCallFromBusiness.setCredit(false);
        tl.add(marginCallFromBusiness);

        Transaccion marginCreditFromBusiness = new Transaccion();
        marginCreditFromBusiness.setName("Credito Margen");
        marginCreditFromBusiness.setOpCash(new Long(1));
        marginCreditFromBusiness.setOpQuantity(new Long(0));
        marginCreditFromBusiness.setCredit(true);
        tl.add(marginCreditFromBusiness);

        Transaccion incomingSecurityLendingFromBusiness = new Transaccion();
        incomingSecurityLendingFromBusiness.setName("Prestamo de Valores Entrante");
        incomingSecurityLendingFromBusiness.setOpCash(new Long(0));
        incomingSecurityLendingFromBusiness.setOpQuantity(new Long(1));
        incomingSecurityLendingFromBusiness.setCredit(true);
        tl.add(incomingSecurityLendingFromBusiness);

        Transaccion outgoingSecurityLendingFromBusiness = new Transaccion();
        outgoingSecurityLendingFromBusiness.setName("Pestamo de valores Saliente");
        outgoingSecurityLendingFromBusiness.setOpCash(new Long(0));
        outgoingSecurityLendingFromBusiness.setOpQuantity(new Long(-1));
        outgoingSecurityLendingFromBusiness.setCredit(true);
        tl.add(outgoingSecurityLendingFromBusiness);

        Transaccion amortizationMarginFromBusiness = new Transaccion();
        amortizationMarginFromBusiness.setName("Amortización Margen");
        amortizationMarginFromBusiness.setOpCash(new Long(-1));
        amortizationMarginFromBusiness.setOpQuantity(new Long(0));
        amortizationMarginFromBusiness.setCredit(true);
        tl.add(amortizationMarginFromBusiness);
//////////////////////////////////////////////////////////////// BROKER

        Transaccion refundFromBroker = new Transaccion();
        refundFromBroker.setName("Reembolso de Correduría");
        refundFromBroker.setOpCash(new Long(1));
        refundFromBroker.setOpQuantity(new Long(0));
        refundFromBroker.setCredit(false);
        tl.add(refundFromBroker);

        Transaccion refund2FromBroker = new Transaccion();
        refund2FromBroker.setName("Reembolso");
        refund2FromBroker.setOpCash(new Long(0));
        refund2FromBroker.setOpQuantity(new Long(1));
        refund2FromBroker.setCredit(false);
        tl.add(refund2FromBroker);

        Transaccion buyAsset = new Transaccion();
        buyAsset.setName("Compra");
        buyAsset.setOpCash(new Long(-1));
        buyAsset.setOpQuantity(new Long(1));
        buyAsset.setCredit(false);
        tl.add(buyAsset);

        Transaccion sellAsset = new Transaccion();
        sellAsset.setName("Venta");
        sellAsset.setOpCash(new Long(1));
        sellAsset.setOpQuantity(new Long(-1));
        sellAsset.setCredit(false);
        tl.add(sellAsset);

        Transaccion inicioReporto = new Transaccion();
        inicioReporto.setOpQuantity(new Long(1));
        inicioReporto.setOpCash(new Long(-1));
        inicioReporto.setName("Inicio de Reporto");
        inicioReporto.setCredit(true);
        tl.add(inicioReporto);

        Transaccion finReporto = new Transaccion();
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

    private void closingDay() {
        try {
            cpb.onDirect("GYRFEMK_87654", new Date(), "1A_AMZN_*");
        } catch (AppException ex) {
            Logger.getLogger(OnDeployBootstraping.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void users() {
        Usuario u = new Usuario();
        u.setActive(Boolean.TRUE);
        u.setEmail("tgyhbnj@ftgybn.com");
        u.setName("TEST");
        u.setPassword("drftgyhujn");

        em.persist(u);
    }

    private void test() {
        LOG.info("Prueba de que voy a hjacer el objeto");
        Activo a = new Activo();
        a.setName("PRUBEAAAAA");
        a.setType(TipoActivo.BOND);
        LOG.info("Prueba de que persiste");
        em.persist(a);
    }
}
