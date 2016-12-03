/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.List;
import mx.samas.domain.Estrategia;
import mx.samas.domain.dto.EstrategiaDTO;
import mx.samas.domain.dto.PortafolioModeloDTO;
import mx.samas.domain.projection.EstrategiaModeloProjection;
import mx.samas.domain.projection.EstrategiaProjection;
import mx.samas.repository.ActivoRepository;
import mx.samas.repository.EstrategiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class EstrategiaServiceImpl implements EstrategiaService {

    @Autowired
    private EstrategiaRepository estrategiaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private VectorPortafolioModeloService portafolioModeloService;

    @Override
    public List<Estrategia> getAll() {
        return (List<Estrategia>) estrategiaRepository.findAll();
    }

    @Override
    public Estrategia createFromDTO(EstrategiaDTO estrategia) {
        Estrategia e = new Estrategia(estrategia.getNombre());
        PortafolioModeloDTO modeloDTO = estrategia.getModelos();
        e.setEstrategiaModelo(portafolioModeloService.createPortafolioModeloListFromDTO(modeloDTO, e));
        estrategiaRepository.save(e);
        return e;
    }

    @Override
    public void createEstrategia(Estrategia e) {
        estrategiaRepository.save(e);
    }

    @Override
    public Estrategia getEstrategiaWithId(Long id) {
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
