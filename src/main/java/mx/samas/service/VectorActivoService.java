/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.ActivoPropiedadValor;

/**
 *
 * @author samas
 */
public interface VectorActivoService {

    public List<VectorActivo> getVectorFromActivo(Long id);

    public List<VectorActivo> getVectorFromActivo(String clavePizarra);

    public VectorActivo addVectorToActivo(Long id, VectorActivo vector);

    public List<ActivoPropiedadValor> getPropiedadesFromVectorActivo(Long id);

    public void createVectorActivoFromList(List<VectorActivo> lva);
    
    public VectorActivo createVectorActivo(VectorActivo va);

}
