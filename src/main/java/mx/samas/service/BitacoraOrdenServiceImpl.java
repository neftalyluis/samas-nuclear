/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.Bitacora;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.Transaccion;
import mx.samas.domain.dto.BitacoraOrdenDTO;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;
import mx.samas.domain.dto.BitacoraOrdenValorDTO;
import mx.samas.repository.BitacoraOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BitacoraOrdenServiceImpl implements BitacoraOrdenService {

    private static final Logger LOG = Logger.getLogger(BitacoraOrdenServiceImpl.class.getName());

    @Autowired
    private BitacoraOrdenRepository bitacoraOrdenRepository;

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private BitacoraService bitacoraService;

    @Autowired
    private ActivoService activoService;

    @Autowired
    private CuentaService cuentaService;

    @Override
    public BitacoraOrden createOrden(BitacoraOrdenDTO dto) {
        BitacoraOrden nuevaOrden = new BitacoraOrden();
        nuevaOrden.setNombre(dto.getNombre());
        List<Transaccion> lt = new ArrayList<>();
        for (Long idTransaccion : dto.getTransacciones()) {
            Transaccion t = transaccionService.findById(idTransaccion);
            if (t != null) {
                lt.add(t);
            } else {
                throw new RuntimeException("No se encontro la transaccion con Id: " + idTransaccion);
            }
        }

        nuevaOrden.setTransacciones(lt);

        return bitacoraOrdenRepository.save(nuevaOrden);
    }

    @Override
    public BitacoraOrden findOrdenByNombre(String nombre) {
        return bitacoraOrdenRepository.findByNombre(nombre);
    }

    @Override
    public BitacoraOrden findOrdenById(Long id) {
        return bitacoraOrdenRepository.findOne(id);
    }

    @Override
    public List<Bitacora> executeOrden(BitacoraOrdenEjecutorDTO ordenDto) {
        //Buscamos la Orden en la DB por el Id
        BitacoraOrden ordenEntidad = findOrdenById(ordenDto.getIdOperacion());
        //Obtenemos las transacciones que componen esa orden
        List<Transaccion> listaTransacciones = ordenEntidad.getTransacciones();
        //Instaciamos nuestra lista de entradas de la Bitacora, por si acaso... :D
        List<Bitacora> listaTransaccionesABitacora = new ArrayList<>();

        //Iteramos en todos los valores
        for (BitacoraOrdenValorDTO bov : ordenDto.getValorTransacciones()) {
            //Buscamos una Transaccion que sea la misma con la del Valor
            for (Transaccion t : listaTransacciones) {
                if (t.getId().equals(bov.getTransaccionId())) {
                    try {

                        Bitacora b = new Bitacora();
                        b.setTransaccion(t);
                        b.setFechaEjecucion(bov.getFechaEjecucion());
                        b.setFechaIngreso(bov.getFechaIngreso());
                        b.setFechaLiquidacion(bov.getFechaLiquidacion());
                        b.setFolioOperacion(ordenDto.getFolioOperacion());
                        if (ordenEntidad.getUsaActivo()) {
                            b.setActivo(activoService.getByClavePizarra(ordenDto.getClavePizarra()));
                        }
                        b.setContrato(cuentaService.getCuentaByCadena(ordenDto.getNumeroContrato()));
//                    ///MMMMMM, hay que checar que ondas con la tasa
//                    b.setTasa(Double.NaN);
//                    Por ahora no pelamos el mercado, pero no es tan dificil hecharlo a andar
//                    b.setMercado(mercado);
                        //Validamos que el flujo de titulos y efectivo concuerde con el de la transaccion
                        //ToDo: La validacion y sus excepciones
                        b.setTitulos(bov.getTitulos());
                        b.setPrecio(bov.getEfectivo());

                        listaTransaccionesABitacora.add(b);
                    } catch (Exception e) {
                        LOG.log(Level.INFO, "Error en la construccion de la orden: {0}", e.getMessage());
                    }
                }
            }
        }
        return bitacoraService.saveBitacoraEntries(listaTransaccionesABitacora);
    }

    @Override
    public BitacoraOrden createOrden(BitacoraOrden dto) {
        return bitacoraOrdenRepository.save(dto);
    }

}
