/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import java.util.logging.Logger;
import mx.samas.domain.Activo;
import mx.samas.domain.Cliente;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Estrategia;
import mx.samas.domain.Portafolio;
import mx.samas.domain.PortafolioEstatus;
import mx.samas.domain.TipoServicio;
import mx.samas.domain.dto.PortafolioDTO;
import mx.samas.domain.projection.PortafolioProjection;
import mx.samas.repository.PortafolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class PortafolioServiceImpl implements PortafolioService {

    private static final Logger LOG = Logger.getLogger(PortafolioServiceImpl.class.getName());

    @Autowired
    private PortafolioRepository portafolioRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private EstrategiaService estrategiaService;

    @Autowired
    private ActivoService activoService;

    @Autowired
    private TipoServicioService tipoServicioService;

    @Autowired
    private PortafolioEstatusService portafolioEstatusService;

    @Override
    public Portafolio createPortafolio(Portafolio p) {
        return portafolioRepository.save(p);
    }

    @Override
    public List<Portafolio> getPortafoliosFromCuenta(Cuenta c) {
        return portafolioRepository.findByCorredores(c);
    }

    @Override
    public Portafolio createPortafolioFromDto(PortafolioDTO p) {

        List<Cliente> clientes = clienteService.getClientesFromIdList(p.getClientes());
        List<Cuenta> cuentas = cuentaService.createFromDto(p.getCuentas());
        Estrategia es = estrategiaService.getEstrategiaWithId(p.getEstrategiaId());
        Activo monedaDenominacion = activoService.getByClavePizarra(p.getMonedaDenominacion());
        TipoServicio servicio = tipoServicioService.getTipoServicioById(p.getTipoServicioId());
        PortafolioEstatus estatusInicial = portafolioEstatusService.getEstatusInicial();

        Portafolio entity = new Portafolio(p.getCuentaEje(), es, servicio,
                estatusInicial, monedaDenominacion, clientes, cuentas, p.getMargen());

        return portafolioRepository.save(entity);
    }

    @Override
    public Portafolio getById(Long id) {
        return portafolioRepository.findOne(id);
    }

    @Override
    public PortafolioProjection getProjectedById(Long id) {
        return portafolioRepository.findById(id);
    }

    @Override
    public List<Portafolio> getAll() {
        return portafolioRepository.findAll();
    }

}
