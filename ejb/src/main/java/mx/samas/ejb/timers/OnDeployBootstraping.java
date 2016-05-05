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
            s.setTipoActivo("Bond");
            s.setCodigo("S");
            s.setDescripcion("Udibonos");

            TipoValor m = new TipoValor();
            m.setTipoActivo("Bond");
            m.setCodigo("M");
            m.setDescripcion("Bonos de Gobierno Federal Tasa Fija");

            TipoValor unoi = new TipoValor();
            unoi.setTipoActivo("Equity");
            unoi.setCodigo("1I");
            unoi.setDescripcion("Tracks Extranjeros");

            TipoValor uno = new TipoValor();
            uno.setTipoActivo("Equity");
            uno.setCodigo("1");
            uno.setDescripcion("Acciones Industriales, Comerico y de Servicios");

            TipoValor unoa = new TipoValor();
            unoa.setTipoActivo("Equity");
            unoa.setCodigo("1A");
            unoa.setDescripcion("Acciones del sistema Internal. de cotizaciones");

            TipoValor curr = new TipoValor();
            curr.setTipoActivo("Currency");
            curr.setCodigo("*C");
            curr.setDescripcion("Tipos de Cambio");

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
            bonos.setCodigo("BONOS");

            Emisor udibono = new Emisor();
            udibono.setCodigo("UDIBONO");

            Emisor ivv = new Emisor();
            ivv.setCodigo("IVV");

            Emisor kimber = new Emisor();
            kimber.setCodigo("KIMBER");

            Emisor amd = new Emisor();
            amd.setCodigo("AMD");

            Emisor gfregio = new Emisor();
            gfregio.setCodigo("GFREGIO");

            Emisor f = new Emisor();
            f.setCodigo("F");

            Emisor tsla = new Emisor();
            tsla.setCodigo("TSLA");

            Emisor amzn = new Emisor();
            amzn.setCodigo("AMZN");

            ///Prueba 
            Emisor mxpi = new Emisor();
            mxpi.setCodigo("MXP");

            Emisor brsf = new Emisor();
            brsf.setCodigo("BRFS");

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
        mxpeso.setTipo(TipoActivo.MONEDA);
        mxpeso.setMonedaDenominacion(mxpcd);
        mxpeso.setNombre("Peso Mexicano");
        mxpeso.setTipoValor("*C");
        mxpeso.setEmisora(ib.getIssuerByCode("MXP"));
        mxpeso.setSerie("");
        mxpeso.setClavePizarra("*C_MXP_");

        mxpcd.setMoneda(mxpeso);

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
        amzn.setTipo(TipoActivo.ACCION);
        amzn.setMonedaDenominacion(cb.getMXPCurrency());
        amzn.setTipoValor("1A");
        amzn.setEmisora(ib.getIssuerByCode("AMZN"));
        amzn.setSerie("*");
        amzn.setNombre("AMAZON COM INC");
        amzn.setClavePizarra("1A_AMZN_*");

        Activo tsla = new Activo();
        tsla.setTipo(TipoActivo.ACCION);
        tsla.setMonedaDenominacion(cb.getMXPCurrency());
        tsla.setTipoValor("1A");
        tsla.setEmisora(ib.getIssuerByCode("TSLA"));
        tsla.setSerie("*");
        tsla.setNombre("TESLA INC");
        tsla.setClavePizarra("1A_TSLA_*");

        Activo fiat = new Activo();
        fiat.setTipo(TipoActivo.ACCION);
        fiat.setMonedaDenominacion(cb.getMXPCurrency());
        fiat.setTipoValor("1A");
        fiat.setEmisora(ib.getIssuerByCode("F"));
        fiat.setSerie("*");
        fiat.setNombre("FIAT SPA");
        fiat.setClavePizarra("1A_F_*");

        Activo gfr = new Activo();
        gfr.setTipo(TipoActivo.ACCION);
        gfr.setMonedaDenominacion(cb.getMXPCurrency());
        gfr.setTipoValor("1");
        gfr.setEmisora(ib.getIssuerByCode("GFREGIO"));
        gfr.setSerie("O");
        gfr.setNombre("AF BANREGIO  S.A. DE C.V. SOFOM");
        gfr.setClavePizarra("1_GFREGIO_O");

        Activo amd = new Activo();
        amd.setTipo(TipoActivo.ACCION);
        amd.setMonedaDenominacion(cb.getMXPCurrency());
        amd.setTipoValor("1A");
        amd.setEmisora(ib.getIssuerByCode("AMD"));
        amd.setSerie("*");
        amd.setNombre("AMD");
        amd.setClavePizarra("1A_AMD_*");

        Activo kimber = new Activo();
        kimber.setTipo(TipoActivo.ACCION);
        kimber.setMonedaDenominacion(cb.getMXPCurrency());
        kimber.setTipoValor("1");
        kimber.setEmisora(ib.getIssuerByCode("KIMBER"));
        kimber.setSerie("A");
        kimber.setNombre("KIMBERLY-CLARK DE MÉXICO S. A. B. DE C. V.");
        kimber.setClavePizarra("1_KIMBER_A");

        Activo ivv = new Activo();
        ivv.setTipo(TipoActivo.ACCION);
        ivv.setMonedaDenominacion(cb.getMXPCurrency());
        ivv.setTipoValor("1I");
        ivv.setEmisora(ib.getIssuerByCode("IVV"));
        ivv.setSerie("*");
        ivv.setNombre("ISHARES CORE S&P 500 ETF");
        ivv.setClavePizarra("1I_IVV_*");

        Activo udibono40 = new Activo();
        udibono40.setTipo(TipoActivo.BONO);
        udibono40.setMonedaDenominacion(cb.getMXPCurrency());
        udibono40.setTipoValor("S");
        udibono40.setEmisora(ib.getIssuerByCode("UDIBONO"));
        udibono40.setSerie("401115");
        udibono40.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono40.setClavePizarra("S_UDIBONO_401115");

        Activo udibono22 = new Activo();
        udibono22.setTipo(TipoActivo.BONO);
        udibono22.setMonedaDenominacion(cb.getMXPCurrency());
        udibono22.setTipoValor("S");
        udibono22.setEmisora(ib.getIssuerByCode("UDIBONO"));
        udibono22.setSerie("220609");
        udibono22.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono22.setClavePizarra("S_UDIBONO_220609");

        Activo udibono16 = new Activo();
        udibono16.setTipo(TipoActivo.BONO);
        udibono16.setMonedaDenominacion(cb.getMXPCurrency());
        udibono16.setTipoValor("S");
        udibono16.setEmisora(ib.getIssuerByCode("UDIBONO"));
        udibono16.setSerie("161215");
        udibono16.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono16.setClavePizarra("S_UDIBONO_160616");

        Activo bonos42 = new Activo();
        bonos42.setTipo(TipoActivo.BONO);
        bonos42.setMonedaDenominacion(cb.getMXPCurrency());
        bonos42.setTipoValor("M");
        bonos42.setEmisora(ib.getIssuerByCode("BONOS"));
        bonos42.setSerie("421113");
        bonos42.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos42.setClavePizarra("M_BONOS_421113");

        Activo bonos24 = new Activo();
        bonos24.setTipo(TipoActivo.BONO);
        bonos24.setMonedaDenominacion(cb.getMXPCurrency());
        bonos24.setTipoValor("M");
        bonos24.setEmisora(ib.getIssuerByCode("BONOS"));
        bonos24.setSerie("241205");
        bonos24.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos24.setClavePizarra("M_BONOS_241205");

        Activo bonos16 = new Activo();
        bonos16.setTipo(TipoActivo.BONO);
        bonos16.setMonedaDenominacion(cb.getMXPCurrency());
        bonos16.setTipoValor("M");
        bonos16.setEmisora(ib.getIssuerByCode("BONOS"));
        bonos16.setSerie("161215");
        bonos16.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos16.setClavePizarra("M_BONOS_161215");

        Activo brfs = new Activo();
        brfs.setTipo(TipoActivo.ACCION);
        brfs.setMonedaDenominacion(cb.getMXPCurrency());
        brfs.setTipoValor("1A");
        brfs.setEmisora(ib.getIssuerByCode("BRFS"));
        brfs.setSerie("N");
        brfs.setNombre("BRASIL FOODS SA");
        brfs.setClavePizarra("1A_BRFS_N");

        Activo lqs = new Activo("Liquidez", "1", ib.getIssuerByCode("LQS"), "1", false);
        lqs.setTipo(TipoActivo.ACCION);

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
            conservador.setNombre("Conservador");
            PerfilRiesgo balanceado = new PerfilRiesgo();
            balanceado.setNombre("Balanceado");
            PerfilRiesgo agresivo = new PerfilRiesgo();
            agresivo.setNombre("Agresivo");

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
            divydeu.setNombre("Dividendo y Deuda");
            divydeu.setPerfilRiesgo(rpb.findByName("Balanceado"));
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

            divydeu.setEstrategiaModelo(lsv);
            if (sgl.persistStrategy(divydeu)) {
                LOG.log(Level.INFO, "Estrategia :{0}", divydeu.getNombre());
            }

            //Estrategia Liquidez
            Estrategia lqs = new Estrategia();
            lqs.setNombre("Liquidez");
            lqs.setPerfilRiesgo(rpb.findByName("Agresivo"));

            lsv.clear();
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMZN_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_TSLA_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_F_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1_GFREGIO_O"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_AMD_*"), lqs, 15.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("1A_BRFS_N"), lqs, 20.0));
            lsv.add(new VectorPortafolioModelo(new Date(), ab.findByTicker("M_BONOS_421113"), lqs, 5.0));
            lqs.setEstrategiaModelo(lsv);
            if (sgl.persistStrategy(lqs)) {
                LOG.log(Level.INFO, "Estrategia: {0}", lqs.getNombre());
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
        b.setNombre("HSBC");
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
        ps.setNombre("Active");

        PortafolioEstatus ps1 = new PortafolioEstatus();
        ps1.setNombre("Suspended");

        PortafolioEstatus ps2 = new PortafolioEstatus();
        ps2.setNombre("Liquidation");

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
            pa.setNumeroCuenta("GYRFEMK_87654");
            pa.setComisionActiva(0.0);
            pa.setBanco(bb.getBankByName("HSBC"));
            pa.setComisionDiscrecional(Boolean.FALSE);

            VectorPortafolio pv = new VectorPortafolio();
            pv.setCuenta(pa);
            pv.setClientes(clb.getAllClients());
            pv.setFecha(new Date());
            pv.setEstatus(pab.getActiveStatus());
            pv.setEstrategia(sgl.getStrategyByName("Dividendo y Deuda"));

            PortafolioCuenta pal = new PortafolioCuenta();
            pal.setNumeroCuenta("HGRFD_7654GHJ");
            pal.setComisionActiva(0.0);
            pal.setBanco(bb.getBankByName("HSBC"));
            pal.setComisionDiscrecional(Boolean.FALSE);

            VectorPortafolio pvl = new VectorPortafolio();
            pvl.setCuenta(pal);
            pvl.setClientes(clb.getAllClients());
            pvl.setFecha(new Date());
            pvl.setEstatus(pab.getActiveStatus());
            pvl.setEstrategia(sgl.getStrategyByName("Liquidez"));

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
        depositMoneyFromClient.setNombre("Deposito en efectivo del Cliente");
        depositMoneyFromClient.setFlujoEfectivo(new Long(1));
        depositMoneyFromClient.setFlujoTitulos(new Long(0));
        depositMoneyFromClient.setCredito(false);
        tl.add(depositMoneyFromClient);

        Transaccion depositTitlesFromClient = new Transaccion();
        depositTitlesFromClient.setNombre("Deposito en titulos del Cliente");
        depositTitlesFromClient.setFlujoEfectivo(new Long(0));
        depositTitlesFromClient.setFlujoTitulos(new Long(1));
        depositTitlesFromClient.setCredito(false);
        tl.add(depositTitlesFromClient);

        Transaccion withdrawalMoneyFromClient = new Transaccion();
        withdrawalMoneyFromClient.setNombre("Retiro de Efectivo del Cliente");
        withdrawalMoneyFromClient.setFlujoEfectivo(new Long(-1));
        withdrawalMoneyFromClient.setFlujoTitulos(new Long(0));
        withdrawalMoneyFromClient.setCredito(false);
        tl.add(withdrawalMoneyFromClient);

        Transaccion withdrawalTitlesFromClient = new Transaccion();
        withdrawalTitlesFromClient.setNombre("Retiro en titulos del Cliente");
        withdrawalTitlesFromClient.setFlujoEfectivo(new Long(0));
        withdrawalTitlesFromClient.setFlujoTitulos(new Long(-1));
        withdrawalTitlesFromClient.setCredito(false);
        tl.add(withdrawalTitlesFromClient);

//////////////////////////////////////////////BUSINESS
        Transaccion refundFromBusiness = new Transaccion();
        refundFromBusiness.setNombre("Reembolso");
        refundFromBusiness.setFlujoEfectivo(new Long(1));
        refundFromBusiness.setFlujoTitulos(new Long(0));
        refundFromBusiness.setCredito(false);
        tl.add(refundFromBusiness);

        Transaccion comissionFromBusiness = new Transaccion();
        comissionFromBusiness.setNombre("Comisión");
        comissionFromBusiness.setFlujoEfectivo(new Long(-1));
        comissionFromBusiness.setFlujoTitulos(new Long(0));
        comissionFromBusiness.setCredito(false);
        tl.add(comissionFromBusiness);

        Transaccion marginCallFromBusiness = new Transaccion();
        marginCallFromBusiness.setNombre("Llamada de Margen");
        marginCallFromBusiness.setFlujoEfectivo(new Long(0));
        marginCallFromBusiness.setFlujoTitulos(new Long(-1));
        marginCallFromBusiness.setCredito(false);
        tl.add(marginCallFromBusiness);

        Transaccion marginCreditFromBusiness = new Transaccion();
        marginCreditFromBusiness.setNombre("Credito Margen");
        marginCreditFromBusiness.setFlujoEfectivo(new Long(1));
        marginCreditFromBusiness.setFlujoTitulos(new Long(0));
        marginCreditFromBusiness.setCredito(true);
        tl.add(marginCreditFromBusiness);

        Transaccion incomingSecurityLendingFromBusiness = new Transaccion();
        incomingSecurityLendingFromBusiness.setNombre("Prestamo de Valores Entrante");
        incomingSecurityLendingFromBusiness.setFlujoEfectivo(new Long(0));
        incomingSecurityLendingFromBusiness.setFlujoTitulos(new Long(1));
        incomingSecurityLendingFromBusiness.setCredito(true);
        tl.add(incomingSecurityLendingFromBusiness);

        Transaccion outgoingSecurityLendingFromBusiness = new Transaccion();
        outgoingSecurityLendingFromBusiness.setNombre("Pestamo de valores Saliente");
        outgoingSecurityLendingFromBusiness.setFlujoEfectivo(new Long(0));
        outgoingSecurityLendingFromBusiness.setFlujoTitulos(new Long(-1));
        outgoingSecurityLendingFromBusiness.setCredito(true);
        tl.add(outgoingSecurityLendingFromBusiness);

        Transaccion amortizationMarginFromBusiness = new Transaccion();
        amortizationMarginFromBusiness.setNombre("Amortización Margen");
        amortizationMarginFromBusiness.setFlujoEfectivo(new Long(-1));
        amortizationMarginFromBusiness.setFlujoTitulos(new Long(0));
        amortizationMarginFromBusiness.setCredito(true);
        tl.add(amortizationMarginFromBusiness);
//////////////////////////////////////////////////////////////// BROKER

        Transaccion refundFromBroker = new Transaccion();
        refundFromBroker.setNombre("Reembolso de Correduría");
        refundFromBroker.setFlujoEfectivo(new Long(1));
        refundFromBroker.setFlujoTitulos(new Long(0));
        refundFromBroker.setCredito(false);
        tl.add(refundFromBroker);

        Transaccion refund2FromBroker = new Transaccion();
        refund2FromBroker.setNombre("Reembolso");
        refund2FromBroker.setFlujoEfectivo(new Long(0));
        refund2FromBroker.setFlujoTitulos(new Long(1));
        refund2FromBroker.setCredito(false);
        tl.add(refund2FromBroker);

        Transaccion buyAsset = new Transaccion();
        buyAsset.setNombre("Compra");
        buyAsset.setFlujoEfectivo(new Long(-1));
        buyAsset.setFlujoTitulos(new Long(1));
        buyAsset.setCredito(false);
        tl.add(buyAsset);

        Transaccion sellAsset = new Transaccion();
        sellAsset.setNombre("Venta");
        sellAsset.setFlujoEfectivo(new Long(1));
        sellAsset.setFlujoTitulos(new Long(-1));
        sellAsset.setCredito(false);
        tl.add(sellAsset);

        Transaccion inicioReporto = new Transaccion();
        inicioReporto.setFlujoTitulos(new Long(1));
        inicioReporto.setFlujoEfectivo(new Long(-1));
        inicioReporto.setNombre("Inicio de Reporto");
        inicioReporto.setCredito(true);
        tl.add(inicioReporto);

        Transaccion finReporto = new Transaccion();
        finReporto.setCredito(true);
        finReporto.setNombre("Fin de Reporto");
        finReporto.setFlujoEfectivo(new Long(1));
        finReporto.setFlujoTitulos(new Long(-1));
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
        u.setNombre("TEST");
        u.setPassword("drftgyhujn");

        em.persist(u);
    }

    private void test() {
        LOG.info("Prueba de que voy a hjacer el objeto");
        Activo a = new Activo();
        a.setNombre("PRUBEAAAAA");
        a.setTipo(TipoActivo.BONO);
        LOG.info("Prueba de que persiste");
        em.persist(a);
    }
}
