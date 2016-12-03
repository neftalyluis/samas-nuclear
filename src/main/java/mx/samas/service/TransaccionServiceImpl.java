/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Transaccion;
import mx.samas.domain.projection.TransaccionProjection;
import mx.samas.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class TransaccionServiceImpl implements TransaccionService {

    @Autowired
    private TransaccionRepository transaccionRepository;

    @Override
    public Transaccion findByNombre(String nombre) {
        return transaccionRepository.getByNombre(nombre);
    }

    @Override
    public void createTransaccionesFromList(List<Transaccion> transacciones) {
        transaccionRepository.save(transacciones);
    }

    @Override
    public Transaccion findById(Long id) {
        return transaccionRepository.findOne(id);
    }

    @Override
    public List<Transaccion> getAllTransacciones() {
        return (List<Transaccion>) transaccionRepository.findAll();
    }

    @Override
    public TransaccionProjection findProjectedByNombre(String nombre) {
        return transaccionRepository.getProjectedByNombre(nombre);
    }

    @Override
    public TransaccionProjection findProjectedById(Long id) {
        return transaccionRepository.getProjectedById(id);
    }

    @Override
    public List<TransaccionProjection> getAllProjectedTransacciones() {
        return transaccionRepository.getAllProjected();
    }

    @Override
    public List<TransaccionProjection> getAllProjectedWithDuenoFuente(String nombre) {
        return transaccionRepository.getProjectedByDuenoFuenteNombre(nombre);
    }

}
