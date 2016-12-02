/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.bootstrap;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.Banco;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.Cliente;
import mx.samas.domain.Cuenta;
import mx.samas.domain.DuenoFuente;
import mx.samas.domain.Estrategia;
import mx.samas.domain.FuenteDatos;
import mx.samas.domain.Perfil;
import mx.samas.domain.Portafolio;
import mx.samas.domain.PortafolioEstatus;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.TipoActivo;
import mx.samas.domain.TipoDato;
import mx.samas.domain.TipoServicio;
import mx.samas.domain.Transaccion;
import mx.samas.domain.Usuario;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;
import mx.samas.domain.dto.BitacoraOrdenValorDTO;
import mx.samas.elastic.repository.VectorActivoPropiedadValorRepository;
import mx.samas.job.SAMASJobs;
import mx.samas.service.ActivoPropiedadService;
import mx.samas.service.ActivoService;
import mx.samas.service.BancoService;
import mx.samas.service.BitacoraOrdenService;
import mx.samas.service.ClienteService;
import mx.samas.service.CuentaService;
import mx.samas.service.DuenoFuenteService;
import mx.samas.service.EstrategiaService;
import mx.samas.service.PerfilService;
import mx.samas.service.PortafolioEstatusService;
import mx.samas.service.PortafolioService;
import mx.samas.service.TipoServicioService;
import mx.samas.service.TransaccionService;
import mx.samas.service.UsuarioService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
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

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private BitacoraOrdenService bitacoraOrdenService;

    @Autowired
    private PortafolioService portafolioService;

    @Autowired
    private ActivoPropiedadService activoPropiedadService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private ApplicationContext appContext;

    @Autowired
    private VectorActivoPropiedadValorRepository propsElastic;
    
    @Autowired
    private TipoServicioService tipoServicioService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        LOG.info("========================================");
        LOG.info("  / ____|  /\\   |  \\/  |   /\\    / ____|");
        LOG.info(" | (___   /  \\  | \\  / |  /  \\  | (___  ");
        LOG.info("  \\___ \\ / /\\ \\ | |\\/| | / /\\ \\  \\___ \\ ");
        LOG.info("  ____) / ____ \\| |  | |/ ____ \\ ____) |");
        LOG.info(" |_____/_/    \\_\\_|  |_/_/    \\_\\_____/");
        LOG.info("========================================");

        testBatch();
        activoPropiedades();
        persistPerfiles();
        persistBancos();
        persistClientesAndCuenta();
        testVectorActivoBatch();
        persistPortfolioEstatus();
        persistEstrategiasAndPortafolioModelo();
        persistTransacciones();
        useOperationDeposito();
        useOperationCompraAccion();
        elasticBatch();
    }

    private boolean persistPerfiles() {
        try {

            ArrayList<Perfil> perfiles = new ArrayList();

            Perfil fo = new Perfil();
            fo.setNombre("FrontOffice");

            Perfil bo = new Perfil();
            bo.setNombre("BackOffice");

            Perfil mo = new Perfil();
            mo.setNombre("MiddleOffice");

            Perfil com = new Perfil();
            com.setNombre("Complaints");

            Perfil adm = new Perfil();
            adm.setNombre("Admin");

            perfiles.add(fo);
            perfiles.add(bo);
            perfiles.add(mo);
            perfiles.add(com);
            perfiles.add(adm);
            perfilService.createPerfilesFromList(perfiles);
            LOG.info("--PerfilesDeUsuario");

            Usuario samas = new Usuario();
            samas.setNombreCompleto("SAMAS Admin");
            samas.setNombreUsuario("samas");
            samas.setPassword("test");
            samas.setPerfiles(perfiles);
            usuarioService.createUsuario(samas);
            LOG.info("--Usuario SAMAS");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Perfiles de Usuario, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistBancos() {
        Banco b = new Banco();
        b.setNombre("HSBC");

        Banco ban = new Banco();
        ban.setNombre("Bancomer");
        try {
            bancoService.createBanco(b);
            bancoService.createBanco(ban);
            LOG.info("--Bancos");
            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir el Bank, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistClientesAndCuenta() {
        try {

            Cliente j = new Cliente("Juan");
            Cliente a = new Cliente("Eduardo");
            Cliente e = new Cliente("Ricardo");

            clienteService.createCliente(e);
            clienteService.createCliente(a);
            clienteService.createCliente(j);

            LOG.info("--Clientes");
//TODO: Limpiar esto
            Cuenta c = new Cuenta();
            c.setIdCuenta("ABCD_1234");
            c.setBanco(bancoService.getByNombre("HSBC"));

            Cuenta ba = new Cuenta();
            ba.setIdCuenta("LUANDJR_654");
            ba.setBanco(bancoService.getByNombre("Bancomer"));

            cuentaService.createOrUpdateCuenta(c);
            cuentaService.createOrUpdateCuenta(ba);
            LOG.info("--Cuentas");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los Clientes, la excepcion es: {0}", e.getMessage());
            return false;
        }
    }

    private boolean persistEstrategiasAndPortafolioModelo() {
        try {
            //TipoServicio, no se me ocurre que poner lmao
            TipoServicio ts = new TipoServicio("No discrecional", Boolean.FALSE);
            tipoServicioService.create(ts);
            ///Cuenta Padre(?)
            Cuenta c = cuentaService.getByIdCuenta("ABCD_1234");
            Cuenta ba = cuentaService.getByIdCuenta("LUANDJR_654");
            List<Cuenta> cuentas = new LinkedList<>();
            cuentas.add(c);
            cuentas.add(ba);
            
            
            //Estrategia Dividendo y Deuda
            Estrategia divydeu = new Estrategia();
            divydeu.setNombre("Dividendo y Deuda");
            List<VectorPortafolioModelo> lsv = new ArrayList<>();
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1A_AMZN_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1A_TSLA_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1A_F_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1_GFREGIO_O"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1A_AMD_*"), divydeu, 4.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1_KIMBER_A"), divydeu, 7.5));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("1I_IVV_*"), divydeu, 7.5));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("M_BONOS_161215"), divydeu, 15.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("M_BONOS_241205"), divydeu, 10.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("M_BONOS_421113"), divydeu, 5.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("S_UDIBONO_160616"), divydeu, 15.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("S_UDIBONO_220609"), divydeu, 10.0));
            lsv.add(new VectorPortafolioModelo(LocalDate.now(), activoService.getByClavePizarra("S_UDIBONO_401115"), divydeu, 10.0));

            divydeu.setEstrategiaModelo(lsv);
            estrategiaService.createEstrategia(divydeu);

            Portafolio p1 = new Portafolio();
            p1.setEstrategia(divydeu);
            p1.setFecha(LocalDate.now());
            p1.setEstatus(portafolioEstatusService.getPortafolioEstatusByNombre("Active"));
            p1.setClientes(clienteService.getAllClientes());
            p1.setMonedaDenominacion(activoService.getByClavePizarra("1A_AMZN_*"));
            p1.setCorredores(cuentas);
            p1.setCuentaEje(ba.getIdCuenta());
            p1.setMargen(12.0);
            p1.setTipoServicio(ts);
            portafolioService.createPortafolio(p1);

            LOG.info("--Estrategias y Portafolios");
            LOG.info("--Actualizamos Cuenta");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir las Estrategias y sus Modelos, la excepcion es: {0} ", e);
            return false;
        }

    }

    private boolean persistTransacciones() {

        DuenoFuente bu = new DuenoFuente("Business");
        DuenoFuente cl = new DuenoFuente("Client");
        DuenoFuente po = new DuenoFuente("Portfolio");
        DuenoFuente br = new DuenoFuente("Broker");
        DuenoFuente ha = new DuenoFuente("Treasury");

        List<Transaccion> tl = new ArrayList<>();
//////////////////////////////////////////////CLIENT
        Transaccion depositMoneyFromClient = new Transaccion();
        depositMoneyFromClient.setNombre("Deposito en efectivo del Cliente");
        depositMoneyFromClient.setFlujoEfectivo(new Long(1));
        depositMoneyFromClient.setFlujoTitulos(new Long(0));
        depositMoneyFromClient.setCredito(false);
        depositMoneyFromClient.setFuenteTransaccion(cl);
        tl.add(depositMoneyFromClient);

        Transaccion depositTitlesFromClient = new Transaccion();
        depositTitlesFromClient.setNombre("Deposito en titulos del Cliente");
        depositTitlesFromClient.setFlujoEfectivo(new Long(0));
        depositTitlesFromClient.setFlujoTitulos(new Long(1));
        depositTitlesFromClient.setCredito(false);
        depositTitlesFromClient.setFuenteTransaccion(cl);
        tl.add(depositTitlesFromClient);

        Transaccion withdrawalMoneyFromClient = new Transaccion();
        withdrawalMoneyFromClient.setNombre("Retiro de Efectivo del Cliente");
        withdrawalMoneyFromClient.setFlujoEfectivo(new Long(-1));
        withdrawalMoneyFromClient.setFlujoTitulos(new Long(0));
        withdrawalMoneyFromClient.setCredito(false);
        withdrawalMoneyFromClient.setFuenteTransaccion(cl);
        tl.add(withdrawalMoneyFromClient);

        Transaccion withdrawalTitlesFromClient = new Transaccion();
        withdrawalTitlesFromClient.setNombre("Retiro en titulos del Cliente");
        withdrawalTitlesFromClient.setFlujoEfectivo(new Long(0));
        withdrawalTitlesFromClient.setFlujoTitulos(new Long(-1));
        withdrawalTitlesFromClient.setCredito(false);
        withdrawalTitlesFromClient.setFuenteTransaccion(cl);
        tl.add(withdrawalTitlesFromClient);

//////////////////////////////////////////////BUSINESS
        Transaccion refundFromBusiness = new Transaccion();
        refundFromBusiness.setNombre("Reembolso");
        refundFromBusiness.setFlujoEfectivo(new Long(1));
        refundFromBusiness.setFlujoTitulos(new Long(0));
        refundFromBusiness.setCredito(false);
        refundFromBusiness.setFuenteTransaccion(bu);
        tl.add(refundFromBusiness);

        Transaccion comissionFromBusiness = new Transaccion();
        comissionFromBusiness.setNombre("Comisión");
        comissionFromBusiness.setFlujoEfectivo(new Long(-1));
        comissionFromBusiness.setFlujoTitulos(new Long(0));
        comissionFromBusiness.setCredito(false);
        comissionFromBusiness.setFuenteTransaccion(bu);
        tl.add(comissionFromBusiness);

        Transaccion marginCallFromBusiness = new Transaccion();
        marginCallFromBusiness.setNombre("Llamada de Margen");
        marginCallFromBusiness.setFlujoEfectivo(new Long(0));
        marginCallFromBusiness.setFlujoTitulos(new Long(-1));
        marginCallFromBusiness.setCredito(false);
        marginCallFromBusiness.setFuenteTransaccion(bu);
        tl.add(marginCallFromBusiness);

        Transaccion marginCreditFromBusiness = new Transaccion();
        marginCreditFromBusiness.setNombre("Credito Margen");
        marginCreditFromBusiness.setFlujoEfectivo(new Long(1));
        marginCreditFromBusiness.setFlujoTitulos(new Long(0));
        marginCreditFromBusiness.setCredito(true);
        marginCreditFromBusiness.setFuenteTransaccion(bu);
        tl.add(marginCreditFromBusiness);

        Transaccion incomingSecurityLendingFromBusiness = new Transaccion();
        incomingSecurityLendingFromBusiness.setNombre("Prestamo de Valores Entrante");
        incomingSecurityLendingFromBusiness.setFlujoEfectivo(new Long(0));
        incomingSecurityLendingFromBusiness.setFlujoTitulos(new Long(1));
        incomingSecurityLendingFromBusiness.setCredito(true);
        incomingSecurityLendingFromBusiness.setFuenteTransaccion(bu);
        tl.add(incomingSecurityLendingFromBusiness);

        Transaccion outgoingSecurityLendingFromBusiness = new Transaccion();
        outgoingSecurityLendingFromBusiness.setNombre("Pestamo de valores Saliente");
        outgoingSecurityLendingFromBusiness.setFlujoEfectivo(new Long(0));
        outgoingSecurityLendingFromBusiness.setFlujoTitulos(new Long(-1));
        outgoingSecurityLendingFromBusiness.setCredito(true);
        outgoingSecurityLendingFromBusiness.setFuenteTransaccion(bu);
        tl.add(outgoingSecurityLendingFromBusiness);

        Transaccion amortizationMarginFromBusiness = new Transaccion();
        amortizationMarginFromBusiness.setNombre("Amortización Margen");
        amortizationMarginFromBusiness.setFlujoEfectivo(new Long(-1));
        amortizationMarginFromBusiness.setFlujoTitulos(new Long(0));
        amortizationMarginFromBusiness.setCredito(true);
        amortizationMarginFromBusiness.setFuenteTransaccion(bu);
        tl.add(amortizationMarginFromBusiness);
//////////////////////////////////////////////////////////////// BROKER

        Transaccion refundFromBroker = new Transaccion();
        refundFromBroker.setNombre("Reembolso de Correduría");
        refundFromBroker.setFlujoEfectivo(new Long(1));
        refundFromBroker.setFlujoTitulos(new Long(0));
        refundFromBroker.setCredito(false);
        refundFromBroker.setFuenteTransaccion(br);
        tl.add(refundFromBroker);

        Transaccion refund2FromBroker = new Transaccion();
        refund2FromBroker.setNombre("Reembolso");
        refund2FromBroker.setFlujoEfectivo(new Long(0));
        refund2FromBroker.setFlujoTitulos(new Long(1));
        refund2FromBroker.setCredito(false);
        refund2FromBroker.setFuenteTransaccion(br);
        tl.add(refund2FromBroker);

        Transaccion buyAsset = new Transaccion();
        buyAsset.setNombre("Compra");
        buyAsset.setFlujoEfectivo(new Long(-1));
        buyAsset.setFlujoTitulos(new Long(1));
        buyAsset.setCredito(false);
        buyAsset.setFuenteTransaccion(br);
        tl.add(buyAsset);

        Transaccion sellAsset = new Transaccion();
        sellAsset.setNombre("Venta");
        sellAsset.setFlujoEfectivo(new Long(1));
        sellAsset.setFlujoTitulos(new Long(-1));
        sellAsset.setCredito(false);
        sellAsset.setFuenteTransaccion(br);
        tl.add(sellAsset);

        Transaccion inicioReporto = new Transaccion();
        inicioReporto.setFlujoTitulos(new Long(1));
        inicioReporto.setFlujoEfectivo(new Long(-1));
        inicioReporto.setNombre("Inicio de Reporto");
        inicioReporto.setCredito(true);
        inicioReporto.setFuenteTransaccion(br);
        tl.add(inicioReporto);

        Transaccion finReporto = new Transaccion();
        finReporto.setCredito(true);
        finReporto.setNombre("Fin de Reporto");
        finReporto.setFlujoEfectivo(new Long(1));
        finReporto.setFlujoTitulos(new Long(-1));
        finReporto.setFuenteTransaccion(br);
        tl.add(finReporto);

//////////////////////////////////////////////////////////////////GOBIERNO
        Transaccion isr = new Transaccion();
        isr.setCredito(false);
        isr.setNombre("ISR");
        isr.setFlujoEfectivo(new Long(-1));
        isr.setFlujoTitulos(new Long(0));
        isr.setFuenteTransaccion(ha);
        tl.add(isr);

        Transaccion iva = new Transaccion();
        iva.setCredito(false);
        iva.setNombre("IVA");
        iva.setFlujoEfectivo(new Long(-1));
        iva.setFlujoTitulos(new Long(0));
        iva.setFuenteTransaccion(ha);
        tl.add(iva);
//////////////////////////////////////////////////////////////////Portafolio

        Transaccion redito = new Transaccion();
        redito.setCredito(false);
        redito.setNombre("Reditos");
        redito.setFlujoEfectivo(new Long(1));
        redito.setFlujoTitulos(new Long(0));
        redito.setFuenteTransaccion(po);
        tl.add(redito);

        try {
            duenoFuenteService.createDuenoFuente(bu);
            duenoFuenteService.createDuenoFuente(cl);
            duenoFuenteService.createDuenoFuente(po);
            duenoFuenteService.createDuenoFuente(br);
            duenoFuenteService.createDuenoFuente(ha);
            LOG.info("--DuenosFuente");

            transaccionService.createTransaccionesFromList(tl);
            LOG.info("--Transacciones");

            BitacoraOrden ordenCompra = new BitacoraOrden();
            List<Transaccion> listaOrden = new ArrayList<>();
            listaOrden.add(comissionFromBusiness);
            listaOrden.add(buyAsset);
            listaOrden.add(iva);
            ordenCompra.setNombre("Compra");
            ordenCompra.setUsaActivo(Boolean.TRUE);
            ordenCompra.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenCompra);

            BitacoraOrden ordenVenta = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(comissionFromBusiness);
            listaOrden.add(sellAsset);
            listaOrden.add(iva);
            ordenVenta.setNombre("Venta");
            ordenVenta.setUsaActivo(Boolean.TRUE);
            ordenVenta.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenVenta);

            BitacoraOrden ordenDeposito = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(depositMoneyFromClient);
            ordenDeposito.setNombre("Deposito de Efectivo");
            ordenDeposito.setUsaActivo(Boolean.FALSE);
            ordenDeposito.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenDeposito);

            BitacoraOrden ordenRedito = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(isr);
            listaOrden.add(redito);
            ordenRedito.setNombre("Reditos");
            ordenRedito.setUsaActivo(Boolean.FALSE);
            ordenRedito.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenRedito);

            LOG.info("--Ordenes");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir nuestras transacciones, la excepcion es: {0}", e.getMessage());
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

    private void useOperationDeposito() {
        BitacoraOrden orden = bitacoraOrdenService.findOneOrdenByNombre("Deposito de Efectivo");
        Transaccion desposito = transaccionService.findByNombre("Deposito en efectivo del Cliente");
        List<BitacoraOrdenValorDTO> valores = new ArrayList<>();
        LocalDate a = LocalDate.now();
        BitacoraOrdenValorDTO depo = new BitacoraOrdenValorDTO();
        depo.setEfectivo(76543.200);
        depo.setTitulos(0L);
        depo.setTransaccionId(desposito.getId());
        depo.setFechaEjecucion(LocalDateTime.now());
        depo.setFechaIngreso(LocalDateTime.now());
        depo.setFechaLiquidacion(a);
        valores.add(depo);
        BitacoraOrdenEjecutorDTO exec = new BitacoraOrdenEjecutorDTO();
        exec.setClavePizarra("NOPË");
        exec.setFolioOperacion("asdfg2345");
        exec.setIdOperacion(orden.getId());
        exec.setNumeroContrato("ggtrfe");
        exec.setValorTransacciones(valores);

        bitacoraOrdenService.executeOrden(exec);
        LOG.info("--Operacion Deposito");
    }

    private void useOperationCompraAccion() {
        BitacoraOrden orden = bitacoraOrdenService.findOneOrdenByNombre("Compra");

        Transaccion compraActivo = transaccionService.findByNombre("Compra");
        Transaccion comision = transaccionService.findByNombre("Comisión");
        Transaccion iva = transaccionService.findByNombre("IVA");

        List<BitacoraOrdenValorDTO> valores = new ArrayList<>();
        LocalDate a = LocalDate.now();

        BitacoraOrdenValorDTO comp = new BitacoraOrdenValorDTO();
        comp.setEfectivo(-399839.05);
        comp.setTitulos(121L);
        comp.setTransaccionId(compraActivo.getId());
        comp.setFechaEjecucion(LocalDateTime.now());
        comp.setFechaIngreso(LocalDateTime.now());
        comp.setFechaLiquidacion(a);
        valores.add(comp);

        BitacoraOrdenValorDTO comi = new BitacoraOrdenValorDTO();
        comi.setEfectivo(-319.87);
        comi.setTitulos(0L);
        comi.setTransaccionId(comision.getId());
        comi.setFechaEjecucion(LocalDateTime.now());
        comi.setFechaIngreso(LocalDateTime.now());
        comi.setFechaLiquidacion(a);
        valores.add(comi);

        BitacoraOrdenValorDTO iv = new BitacoraOrdenValorDTO();
        iv.setEfectivo(-5412.25);
        iv.setTitulos(0L);
        iv.setTransaccionId(iva.getId());
        iv.setFechaEjecucion(LocalDateTime.now());
        iv.setFechaIngreso(LocalDateTime.now());
        iv.setFechaLiquidacion(a);
        valores.add(iv);

        BitacoraOrdenEjecutorDTO exec = new BitacoraOrdenEjecutorDTO();
        exec.setClavePizarra("1A_AMZN_*");
        exec.setFolioOperacion("asdfg2654345");
        exec.setIdOperacion(orden.getId());
        exec.setNumeroContrato("ggtrfe");
        exec.setValorTransacciones(valores);

        bitacoraOrdenService.executeOrden(exec);
        LOG.info("--Operacion Compra");
    }

    private void testBatch() {
        LOG.info("Batch Activo");
        Job job = (Job) appContext.getBean(SAMASJobs.BOOTSTRAP_ACTIVO);

        JobParameters jpb = new JobParameters();

        try {
            JobExecution a = jobLauncher.run(job, jpb);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            Logger.getLogger(EntityBootstraping.class.getName()).log(Level.SEVERE, "El Batch De Activos falló", ex);
        }
    }

    private void testVectorActivoBatch() {
        LOG.info("Batch VectorActivo");
        Job job = (Job) appContext.getBean(SAMASJobs.VALUACION_VECTOR);

        JobParameters jpb = new JobParameters();

        try {
            JobExecution a = jobLauncher.run(job, jpb);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            Logger.getLogger(EntityBootstraping.class.getName()).log(Level.SEVERE, "El Batch de Vector falló", ex);
        }

    }

    private void activoPropiedades() {

        //Equity 
        //Distintivas Normativas
        ActivoPropiedad priv = new ActivoPropiedad();
        priv.setNombre("Privado");
        priv.setDescripcion("Bolsa Privada");

        priv.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        priv.setIndice(null);
        priv.setTipoActivo(TipoActivo.ACCION);
        priv.setTipoDato(TipoDato.BOOLEAN);

        ActivoPropiedad fund = new ActivoPropiedad();
        fund.setNombre("Fund");
        fund.setDescripcion("Fund");

        fund.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        fund.setIndice(null);
        fund.setTipoActivo(TipoActivo.ACCION);
        fund.setTipoDato(TipoDato.BOOLEAN);

        ActivoPropiedad fee = new ActivoPropiedad();
        fee.setNombre("Fee");
        fee.setDescripcion("Fee");

        fee.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        fee.setIndice(null);
        fee.setTipoActivo(TipoActivo.ACCION);
        fee.setTipoDato(TipoDato.DOUBLE);

        ///Bonos
        ///Normativas Imperativas
        ActivoPropiedad vencimiento = new ActivoPropiedad();
        vencimiento.setNombre("FechaVencimiento");
        vencimiento.setDescripcion("Fecha de Vencimiento del Bono");

        vencimiento.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        vencimiento.setIndice(null);
        vencimiento.setTipoActivo(TipoActivo.BONO);
        vencimiento.setTipoDato(TipoDato.DATE);

        ActivoPropiedad tasaReferencia = new ActivoPropiedad();
        tasaReferencia.setNombre("TasaReferencia");
        tasaReferencia.setDescripcion("Tasa de Referencia del Bono");

        tasaReferencia.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        tasaReferencia.setIndice(null);
        tasaReferencia.setTipoActivo(TipoActivo.BONO);
        tasaReferencia.setTipoDato(TipoDato.DOUBLE);

        ///Normativas Distintivas
        ActivoPropiedad amortizable = new ActivoPropiedad();
        amortizable.setNombre("Amortizable?");
        amortizable.setDescripcion("Define si el Bono Amortiza");
        amortizable.setOrigenDatos(FuenteDatos.CSV_USUARIO);
        amortizable.setIndice(null);
        amortizable.setTipoActivo(TipoActivo.BONO);
        amortizable.setTipoDato(TipoDato.BOOLEAN);

        activoPropiedadService.createPropiedad(priv);
        activoPropiedadService.createPropiedad(fund);
        activoPropiedadService.createPropiedad(fee);
        activoPropiedadService.createPropiedad(vencimiento);
        activoPropiedadService.createPropiedad(tasaReferencia);
        activoPropiedadService.createPropiedad(amortizable);

    }

    private void persistPropiedadesActivo() {

        ActivoPropiedad vna = new ActivoPropiedad("Valor Nominal Actualizado",
                "ValorNA", TipoDato.DATE, 52, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad preSuc = new ActivoPropiedad("Precio Sucio",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad tasaren = new ActivoPropiedad("Tasa de Rendimiento",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad fechaVen = new ActivoPropiedad("Fecha de Vencimiento",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad sobretasa = new ActivoPropiedad("Sobretasa",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad tasaCupon = new ActivoPropiedad("Tasa Cupon",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad moodys = new ActivoPropiedad("Precio Sucio",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad sp = new ActivoPropiedad("Precio Sucio",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad fith = new ActivoPropiedad("Precio Sucio",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);

        ActivoPropiedad hrRatings = new ActivoPropiedad("Precio Sucio",
                "Precio Limpio mas reditos devengados",
                TipoDato.DATE, 4, FuenteDatos.VECTOR_PIP,
                TipoActivo.BONO, Boolean.TRUE);
    }

    private void elasticBatch() {
        LOG.info("Batch VectorActivo en Elastic");
        Job job = (Job) appContext.getBean(SAMASJobs.ELASTIC_BOOTSTRAP);
        JobParameters jpb = new JobParameters();

        try {
            JobExecution a = jobLauncher.run(job, jpb);
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException ex) {
            Logger.getLogger(EntityBootstraping.class.getName()).log(Level.SEVERE, "El Batch de Vector falló", ex);
        }
    }
}
