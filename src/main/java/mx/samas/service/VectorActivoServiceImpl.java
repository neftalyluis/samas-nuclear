/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.repository.ActivoRepository;
import mx.samas.repository.VectorActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class VectorActivoServiceImpl implements VectorActivoService {

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private VectorActivoRepository vectorActivoRepository;

    @Override
    public List<VectorActivo> getVectorFromActivo(Long id) {
        Activo u = activoRepository.findOne(id);
        return u.getVectores();
    }

    @Override
    public List<VectorActivo> getVectorFromActivo(String clavePizarra) {
        Activo u = activoRepository.findFirstByClavePizarra(clavePizarra);
        return u.getVectores();
    }

    @Override
    public VectorActivo addVectorToActivo(Long id, VectorActivo vector) {
        Activo u = activoRepository.findOne(id);
        u.getVectores().add(vector);
        return vector;
    }

    @Override
    public List<ActivoPropiedadValor> getPropiedadesFromVectorActivo(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createVectorActivoFromList(List<VectorActivo> lva) {
        vectorActivoRepository.save(lva);
    }

    @Override
    public VectorActivo createVectorActivo(VectorActivo va) {
        return vectorActivoRepository.save(va);
    }

    @Override
    public VectorActivo getLastVectorFromActivo(Activo a) {
        return vectorActivoRepository.findFirstByActivoOrderByFechaDesc(a);
    }

    @Override
    public VectorActivo getLastVectorFromActivo(String clavePizarra) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
