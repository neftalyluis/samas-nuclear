/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Transaccion;
import mx.samas.domain.projection.TransaccionProjection;

/**
 *
 * @author samas
 */
public interface TransaccionService {

    public TransaccionProjection findProjectedByNombre(String nombre);

    public void createTransaccionesFromList(List<Transaccion> transacciones);

    public TransaccionProjection findProjectedById(Long id);

    public List<TransaccionProjection> getAllProjectedTransacciones();
    
    public List<TransaccionProjection> getAllProjectedWithDuenoFuente(String nombre);

    public Transaccion findById(Long id);

    public List<Transaccion> getAllTransacciones();

    public Transaccion findByNombre(String nombre);

}
