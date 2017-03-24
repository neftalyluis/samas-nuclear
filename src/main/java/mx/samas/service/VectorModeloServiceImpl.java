/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import mx.samas.domain.Activo;
import mx.samas.domain.Modelo;
import mx.samas.domain.VectorModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import mx.samas.repository.ActivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import mx.samas.repository.VectorModeloRepository;
import mx.samas.repository.ModeloRepository;

/**
 *
 * @author samas
 */
@Service
public class VectorModeloServiceImpl implements VectorModeloService {

    @Autowired
    private ModeloRepository estrategiaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Autowired
    private VectorModeloRepository vectorPortafolioModeloRepository;

    @Override
    public List<VectorModelo> getLastPortafolioModeloFromEstrategia(Long id) {

        Modelo estrategia = estrategiaRepository.findOne(id);
        List<VectorModelo> modelos = estrategia.getEstrategiaModelo();
        List<VectorModelo> lastModel = getModelosWithDate(getMaxDate(modelos), modelos);

        return lastModel;
    }

    @Override
    public List<VectorModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model) {

        Modelo e = estrategiaRepository.findOne(id);
        List<VectorModelo> fromDTO = createPortafolioModeloListFromDTO(model, e);
        List<VectorModelo> fromEstrategia = e.getEstrategiaModelo();
        fromEstrategia.addAll(fromDTO);
        e.setEstrategiaModelo(fromEstrategia);
        estrategiaRepository.save(e);

        return fromDTO;
    }

    @Override
    public List<VectorModelo> getAllModelosFromEstrategia(Long id) {
        return estrategiaRepository.findOne(id).getEstrategiaModelo();
    }

    //TODO: Esta implementacion esta Rota
    private LocalDate getMaxDate(List<VectorModelo> list) {
        LocalDate maxDate = null;
        for (VectorModelo model : list) {
            LocalDate dateFromEntity = model.getCreado();
            if (maxDate == null) {
                maxDate = dateFromEntity;
            } else if (dateFromEntity.isAfter(maxDate)) {
                maxDate = dateFromEntity;

            }
        }
        return maxDate;
    }

    //Igual de Rota
    private List<VectorModelo> getModelosWithDate(LocalDate date, List<VectorModelo> modelList) {
        List<VectorModelo> newList = new ArrayList<>();
        for (VectorModelo pm : modelList) {
            if (pm.getCreado().compareTo(date) == 0) {
                newList.add(pm);
            }
        }
        return newList;
    }

    public List<VectorModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Modelo e) {
        List<VectorModelo> newList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : models.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            Activo a = activoRepository.findFirstByClavePizarra(key);
            //TODO
            VectorModelo pm = new VectorModelo();
            newList.add(pm);
        }
        return newList;
    }

    @Override
    public List<VectorModelo> getAll() {
        return vectorPortafolioModeloRepository.findAll();
    }

}
