/*
 * Copyright 2017 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.samas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.DuenoFuente;
import mx.samas.domain.Perfil;
import mx.samas.domain.PortafolioEstatus;
import mx.samas.domain.Transaccion;
import mx.samas.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BootstrapServiceImpl implements BootstrapService {

    private static final Logger LOG = Logger.getLogger(BootstrapServiceImpl.class.getName());

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
    private BitacoraOrdenService bitacoraOrdenService;

    @Autowired
    private BatchService batchService;

    @Override
    public void execute() {
        persistPerfiles();
        persistPortfolioEstatus();
        persistTransacciones();
        activo();

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
            LOG.log(Level.WARNING, "No pudimos persistir los Perfiles de Usuario, la excepcion es: {0}", e);
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
        outgoingSecurityLendingFromBusiness.setNombre("Prestamo de valores Saliente");
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
        refundFromBroker.setNombre("Reembolso en efectivo de Correduría");
        refundFromBroker.setFlujoEfectivo(new Long(1));
        refundFromBroker.setFlujoTitulos(new Long(0));
        refundFromBroker.setCredito(false);
        refundFromBroker.setFuenteTransaccion(br);
        tl.add(refundFromBroker);

        Transaccion refund2FromBroker = new Transaccion();
        refund2FromBroker.setNombre("Reembolso en titulos de Correduría");
        refund2FromBroker.setFlujoEfectivo(new Long(0));
        refund2FromBroker.setFlujoTitulos(new Long(1));
        refund2FromBroker.setCredito(false);
        refund2FromBroker.setFuenteTransaccion(br);
        tl.add(refund2FromBroker);

        Transaccion buyAsset = new Transaccion();
        buyAsset.setNombre("Compra de titulos");
        buyAsset.setFlujoEfectivo(new Long(-1));
        buyAsset.setFlujoTitulos(new Long(1));
        buyAsset.setCredito(false);
        buyAsset.setFuenteTransaccion(br);
        tl.add(buyAsset);

        Transaccion sellAsset = new Transaccion();
        sellAsset.setNombre("Venta de titulos");
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
            listaOrden = new ArrayList<>();

            BitacoraOrden ordenVenta = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(comissionFromBusiness);
            listaOrden.add(sellAsset);
            listaOrden.add(iva);
            ordenVenta.setNombre("Venta");
            ordenVenta.setUsaActivo(Boolean.TRUE);
            ordenVenta.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenVenta);
            listaOrden = new ArrayList<>();

            BitacoraOrden ordenDeposito = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(depositMoneyFromClient);
            ordenDeposito.setNombre("Deposito de Efectivo");
            ordenDeposito.setUsaActivo(Boolean.FALSE);
            ordenDeposito.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenDeposito);
            listaOrden = new ArrayList<>();

            BitacoraOrden ordenRedito = new BitacoraOrden();
            listaOrden.clear();
            listaOrden.add(isr);
            listaOrden.add(redito);
            ordenRedito.setNombre("Reditos");
            ordenRedito.setUsaActivo(Boolean.FALSE);
            ordenRedito.setTransacciones(listaOrden);
            bitacoraOrdenService.createOrden(ordenRedito);
            listaOrden = new ArrayList<>();

            LOG.info("--Ordenes");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir nuestras transacciones, la excepcion es: {0}", e);
            return false;
        }

    }

    private boolean persistPortfolioEstatus() {
        PortafolioEstatus ps = new PortafolioEstatus();
        ps.setNombre("Activo");
        ps.setOrden(0L);

        PortafolioEstatus ps1 = new PortafolioEstatus();
        ps1.setNombre("Suspendido");
        ps1.setOrden(1L);

        PortafolioEstatus ps2 = new PortafolioEstatus();
        ps2.setNombre("Liquidacion");
        ps2.setOrden(2L);

        try {
            portafolioEstatusService.createPortafolioEstatus(ps);
            portafolioEstatusService.createPortafolioEstatus(ps1);
            portafolioEstatusService.createPortafolioEstatus(ps2);
            LOG.info("--PortafolioEstatus");

            return true;
        } catch (Exception e) {
            LOG.log(Level.WARNING, "No pudimos persistir los PortfolioStatus, la excepcion es: {0}", e);
            return false;
        }
    }

    @Override
    public void activo() {
        batchService.bootstrapActivo();
    }
}
