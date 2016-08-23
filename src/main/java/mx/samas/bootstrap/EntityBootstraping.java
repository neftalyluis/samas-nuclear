/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.bootstrap;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.Activo;
import mx.samas.domain.Banco;
import mx.samas.domain.Cliente;
import mx.samas.domain.DuenoFuente;
import mx.samas.domain.Estrategia;
import mx.samas.domain.PortafolioEstatus;
import mx.samas.domain.PortafolioModelo;
import mx.samas.domain.TipoActivo;
import mx.samas.domain.Transaccion;
import mx.samas.service.ActivoService;
import mx.samas.service.BancoService;
import mx.samas.service.ClienteService;
import mx.samas.service.DuenoFuenteService;
import mx.samas.service.EstrategiaService;
import mx.samas.service.PortafolioEstatusService;
import mx.samas.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 *
 * @author samas
 */
@Component
public class EntityBootstraping implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = Logger.getLogger(EntityBootstraping.class.getName());

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ActivoService activoService;

    @Autowired
    private EstrategiaService estrategiaService;

    @Autowired
    private BancoService bancoService;

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private DuenoFuenteService duenoFuenteService;

    @Autowired
    private PortafolioEstatusService portafolioEstatusService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("========================================");
        System.out.println("  / ____|  /\\   |  \\/  |   /\\    / ____|\n"
                + " | (___   /  \\  | \\  / |  /  \\  | (___  \n"
                + "  \\___ \\ / /\\ \\ | |\\/| | / /\\ \\  \\___ \\ \n"
                + "  ____) / ____ \\| |  | |/ ____ \\ ____) |\n"
                + " |_____/_/    \\_\\_|  |_/_/    \\_\\_____/");
        System.out.println("========================================");
        persistClientes();
        persistActivos();
        persistEstrategiasAndPortafolioModelo();
        persistBancos();
        persistTransacciones();
        persistSourceOwners();
        persistPortfolioEstatus();
    }

    private boolean persistClientes() {
        try {

            Cliente j = new Cliente("Juan");
            Cliente a = new Cliente("Eduardo");
            Cliente e = new Cliente("Ricardo");

            clienteService.createCliente(e);
            clienteService.createCliente(a);
            clienteService.createCliente(j);

            LOG.info("--Clientes");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Clientes, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistActivos() {

        Activo amzn = new Activo();
        amzn.setTipo(TipoActivo.ACCION);
//        amzn.setMonedaDenominacion(cb.getMXPCurrency());
        amzn.setTipoValor("1A");
        amzn.setEmisora("AMZN");
        amzn.setSerie("*");
        amzn.setNombre("AMAZON COM INC");
        amzn.setClavePizarra("1A_AMZN_*");

        Activo tsla = new Activo();
        tsla.setTipo(TipoActivo.ACCION);
//        tsla.setMonedaDenominacion(cb.getMXPCurrency());
        tsla.setTipoValor("1A");
        tsla.setEmisora("TSLA");
        tsla.setSerie("*");
        tsla.setNombre("TESLA INC");
        tsla.setClavePizarra("1A_TSLA_*");

        Activo fiat = new Activo();
        fiat.setTipo(TipoActivo.ACCION);
//        fiat.setMonedaDenominacion(cb.getMXPCurrency());
        fiat.setTipoValor("1A");
        fiat.setEmisora("F");
        fiat.setSerie("*");
        fiat.setNombre("FIAT SPA");
        fiat.setClavePizarra("1A_F_*");

        Activo gfr = new Activo();
        gfr.setTipo(TipoActivo.ACCION);
//        gfr.setMonedaDenominacion(cb.getMXPCurrency());
        gfr.setTipoValor("1");
        gfr.setEmisora("GFREGIO");
        gfr.setSerie("O");
        gfr.setNombre("AF BANREGIO  S.A. DE C.V. SOFOM");
        gfr.setClavePizarra("1_GFREGIO_O");

        Activo amd = new Activo();
        amd.setTipo(TipoActivo.ACCION);
//        amd.setMonedaDenominacion(cb.getMXPCurrency());
        amd.setTipoValor("1A");
        amd.setEmisora("AMD");
        amd.setSerie("*");
        amd.setNombre("AMD");
        amd.setClavePizarra("1A_AMD_*");

        Activo kimber = new Activo();
        kimber.setTipo(TipoActivo.ACCION);
//        kimber.setMonedaDenominacion(cb.getMXPCurrency());
        kimber.setTipoValor("1");
        kimber.setEmisora("KIMBER");
        kimber.setSerie("A");
        kimber.setNombre("KIMBERLY-CLARK DE MÉXICO S. A. B. DE C. V.");
        kimber.setClavePizarra("1_KIMBER_A");

        Activo ivv = new Activo();
        ivv.setTipo(TipoActivo.ACCION);
//        ivv.setMonedaDenominacion(cb.getMXPCurrency());
        ivv.setTipoValor("1I");
        ivv.setEmisora("IVV");
        ivv.setSerie("*");
        ivv.setNombre("ISHARES CORE S&P 500 ETF");
        ivv.setClavePizarra("1I_IVV_*");

        Activo udibono40 = new Activo();
        udibono40.setTipo(TipoActivo.BONO);
//        udibono40.setMonedaDenominacion(cb.getMXPCurrency());
        udibono40.setTipoValor("S");
        udibono40.setEmisora("UDIBONO");
        udibono40.setSerie("401115");
        udibono40.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono40.setClavePizarra("S_UDIBONO_401115");

        Activo udibono22 = new Activo();
        udibono22.setTipo(TipoActivo.BONO);
//        udibono22.setMonedaDenominacion(cb.getMXPCurrency());
        udibono22.setTipoValor("S");
        udibono22.setEmisora("UDIBONO");
        udibono22.setSerie("220609");
        udibono22.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono22.setClavePizarra("S_UDIBONO_220609");

        Activo udibono16 = new Activo();
        udibono16.setTipo(TipoActivo.BONO);
//        udibono16.setMonedaDenominacion(cb.getMXPCurrency());
        udibono16.setTipoValor("S");
        udibono16.setEmisora("UDIBONO");
        udibono16.setSerie("161215");
        udibono16.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        udibono16.setClavePizarra("S_UDIBONO_160616");

        Activo bonos42 = new Activo();
        bonos42.setTipo(TipoActivo.BONO);
//        bonos42.setMonedaDenominacion(cb.getMXPCurrency());
        bonos42.setTipoValor("M");
        bonos42.setEmisora("BONOS");
        bonos42.setSerie("421113");
        bonos42.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos42.setClavePizarra("M_BONOS_421113");

        Activo bonos24 = new Activo();
        bonos24.setTipo(TipoActivo.BONO);
//        bonos24.setMonedaDenominacion(cb.getMXPCurrency());
        bonos24.setTipoValor("M");
        bonos24.setEmisora("BONOS");
        bonos24.setSerie("241205");
        bonos24.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos24.setClavePizarra("M_BONOS_241205");

        Activo bonos16 = new Activo();
        bonos16.setTipo(TipoActivo.BONO);
//        bonos16.setMonedaDenominacion(cb.getMXPCurrency());
        bonos16.setTipoValor("M");
        bonos16.setEmisora("BONOS");
        bonos16.setSerie("161215");
        bonos16.setNombre("SECRETARÍA DE HACIENDA Y CRÉDITO PÚBLICO");
        bonos16.setClavePizarra("M_BONOS_161215");

        Activo brfs = new Activo();
        brfs.setTipo(TipoActivo.ACCION);
//        brfs.setMonedaDenominacion(cb.getMXPCurrency());
        brfs.setTipoValor("1A");
        brfs.setEmisora("BRFS");
        brfs.setSerie("N");
        brfs.setNombre("BRASIL FOODS SA");
        brfs.setClavePizarra("1A_BRFS_N");

        Activo lqs = new Activo("Liquidez", "1", "LQS", "1");
        lqs.setTipo(TipoActivo.ACCION);

        try {

            activoService.createActivo(amzn);
            activoService.createActivo(tsla);
            activoService.createActivo(fiat);
            activoService.createActivo(gfr);
            activoService.createActivo(amd);
            activoService.createActivo(kimber);
            activoService.createActivo(ivv);
            activoService.createActivo(udibono16);
            activoService.createActivo(udibono22);
            activoService.createActivo(udibono40);
            activoService.createActivo(bonos16);
            activoService.createActivo(bonos24);
            activoService.createActivo(bonos42);
            activoService.createActivo(brfs);
            activoService.createActivo(lqs);

            LOG.info("--Activos");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Assets iniciales, la excepcion es: {0}", e.getMessage());
            return false;
        }

    }

    private boolean persistEstrategiasAndPortafolioModelo() {
        try {
            //Estrategia Dividendo y Deuda
            Estrategia divydeu = new Estrategia();
            divydeu.setNombre("Dividendo y Deuda");
//            divydeu.setPerfilRiesgo(rpb.findByName("Balanceado"));
            List<PortafolioModelo> lsv = new ArrayList<>();
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_AMZN_*"), divydeu, 4.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_TSLA_*"), divydeu, 4.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_F_*"), divydeu, 4.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1_GFREGIO_O"), divydeu, 4.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_AMD_*"), divydeu, 4.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1_KIMBER_A"), divydeu, 7.5));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1I_IVV_*"), divydeu, 7.5));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("M_BONOS_161215"), divydeu, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("M_BONOS_241205"), divydeu, 10.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("M_BONOS_421113"), divydeu, 5.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("S_UDIBONO_160616"), divydeu, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("S_UDIBONO_220609"), divydeu, 10.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("S_UDIBONO_401115"), divydeu, 5.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1_LQS_1"), divydeu, 5.0));

            divydeu.setEstrategiaModelo(lsv);
            estrategiaService.createEstrategia(divydeu);

            //Estrategia Liquidez
            Estrategia lqs = new Estrategia();
            lqs.setNombre("Liquidez");
//            lqs.setPerfilRiesgo(rpb.findByName("Agresivo"));

            lsv.clear();
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_AMZN_*"), lqs, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_TSLA_*"), lqs, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_F_*"), lqs, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1_GFREGIO_O"), lqs, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_AMD_*"), lqs, 15.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("1A_BRFS_N"), lqs, 20.0));
            lsv.add(new PortafolioModelo(new Date(), activoService.getByClavePizarra("M_BONOS_421113"), lqs, 5.0));
            lqs.setEstrategiaModelo(lsv);
            estrategiaService.createEstrategia(lqs);
            LOG.info("--Estrategias");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir las Estrategias y sus Modelos, la excepcion es: {0} ", e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    private boolean persistBancos() {
        Banco b = new Banco();
        b.setNombre("HSBC");
        try {
            bancoService.createBanco(b);
            LOG.info("--Bancos");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Bank, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistTransacciones() {

        List<Transaccion> tl = new ArrayList<>();
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
            transaccionService.createTransaccionesFromList(tl);
            LOG.info("--Transacciones");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir nuestra transaccion, la excepcion es: {0}", e.getMessage());
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

            duenoFuenteService.createDuenoFuente(bu);
            duenoFuenteService.createDuenoFuente(cl);
            duenoFuenteService.createDuenoFuente(po);
            duenoFuenteService.createDuenoFuente(br);
            duenoFuenteService.createDuenoFuente(ha);
            LOG.info("--DuenosFuente");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los SourceOwners, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistPortfolioEstatus() {
        PortafolioEstatus ps = new PortafolioEstatus();
        ps.setNombre("Active");

        PortafolioEstatus ps1 = new PortafolioEstatus();
        ps1.setNombre("Suspended");

        PortafolioEstatus ps2 = new PortafolioEstatus();
        ps2.setNombre("Liquidation");

        try {
            portafolioEstatusService.createPortafolioEstatus(ps);
            portafolioEstatusService.createPortafolioEstatus(ps1);
            portafolioEstatusService.createPortafolioEstatus(ps2);
            LOG.info("--PortafolioEstatus");
            
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los PortfolioStatus, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }
}
