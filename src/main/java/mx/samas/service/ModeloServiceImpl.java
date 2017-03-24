/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Modelo;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.domain.dto.PortafolioModeloDTO;
import mx.samas.domain.projection.EstrategiaModeloProjection;
import mx.samas.domain.projection.EstrategiaProjection;
import mx.samas.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.samas.repository.ModeloRepository;

/**
 *
 * @author samas
 */
@Service
public class ModeloServiceImpl implements ModeloService {

    @Autowired
    private ModeloRepository estrategiaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private VectorModeloService portafolioModeloService;

    @Override
    public List<Modelo> getAll() {
        return (List<Modelo>) estrategiaRepository.findAll();
    }

    @Override
    public Modelo createFromDTO(EstrategiaDTO estrategia) {
        Modelo e = new Modelo(estrategia.getNombre());
        PortafolioModeloDTO modeloDTO = estrategia.getModelos();
        e.setEstrategiaModelo(portafolioModeloService.createPortafolioModeloListFromDTO(modeloDTO, e));
        estrategiaRepository.save(e);
        return e;
    }

    @Override
    public void createEstrategia(Modelo e) {
        estrategiaRepository.save(e);
    }

    @Override
    public Modelo getEstrategiaWithId(Long id) {
        return estrategiaRepository.findOne(id);
    }

    @Override
    public EstrategiaProjection getEstrategiaProjectedWithId(Long id) {
        return estrategiaRepository.findOneProjectedById(id);
    }

    @Override
    public List<EstrategiaModeloProjection> getLastEstrategiaModeloFromIdEstrategia(Long id) {
        return estrategiaRepository.getLastModeloWithIdEstrategia(id);
    }

}
