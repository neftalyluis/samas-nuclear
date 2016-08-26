/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.ArrayList;
import java.util.List;
import mx.samas.domain.BitacoraOrden;
import mx.samas.domain.Transaccion;
import mx.samas.domain.dto.BitacoraOrdenDTO;
import mx.samas.domain.dto.BitacoraOrdenEjecutorDTO;
import mx.samas.repository.BitacoraOrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class BitacoraOrdenServiceImpl implements BitacoraOrdenService {

    @Autowired
    private BitacoraOrdenRepository bitacoraOrdenRepository;

    @Autowired
    private TransaccionService transaccionService;

    @Autowired
    private BitacoraService bitacoraService;

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
    public void executeOrden(BitacoraOrdenEjecutorDTO ordenDto) {
//        BitacoraOrden ordenEntidad = findOrdenById(ordenDto.getIdOperacion());
//        List<Transaccion> listaTransacciones = ordenEntidad.getTransacciones();
//
//        List<Bitacora> listaTransaccionesABitacora = new ArrayList<>();
//        
//        for (BitacoraOrdenValorDTO bov : ordenDto.getValorTransacciones()) {
//            for (Transaccion t : listaTransacciones) {
//                if (t.getId().equals(bov.getTransaccionId())) {
//                    Bitacora b = new Bitacora();
//                    b.se
//                }
//            }
//        }
    }

    @Override
    public BitacoraOrden createOrden(BitacoraOrden dto) {
        return bitacoraOrdenRepository.save(dto);
    }

}
