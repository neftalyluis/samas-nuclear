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
import mx.samas.domain.Estrategia;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.dto.PortafolioModeloDTO;
import mx.samas.repository.ActivoRepository;
import mx.samas.repository.EstrategiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class VectorPortafolioModeloServiceImpl implements VectorPortafolioModeloService {

    @Autowired
    private EstrategiaRepository estrategiaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Override
    public List<VectorPortafolioModelo> getLastPortafolioModeloFromEstrategia(Long id) {

        Estrategia estrategia = estrategiaRepository.findOne(id);
        List<VectorPortafolioModelo> modelos = estrategia.getEstrategiaModelo();
        List<VectorPortafolioModelo> lastModel = getModelosWithDate(getMaxDate(modelos), modelos);

        return lastModel;
    }

    @Override
    public List<VectorPortafolioModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model) {

        Estrategia e = estrategiaRepository.findOne(id);
        List<VectorPortafolioModelo> fromDTO = createPortafolioModeloListFromDTO(model, e);
        List<VectorPortafolioModelo> fromEstrategia = e.getEstrategiaModelo();
        fromEstrategia.addAll(fromDTO);
        e.setEstrategiaModelo(fromEstrategia);
        estrategiaRepository.save(e);

        return fromDTO;
    }

    @Override
    public List<VectorPortafolioModelo> getAllModelosFromEstrategia(Long id) {
        return estrategiaRepository.findOne(id).getEstrategiaModelo();
    }

    //TODO: Esta implementacion esta Rota
    private LocalDate getMaxDate(List<VectorPortafolioModelo> list) {
        LocalDate maxDate = null;
        for (VectorPortafolioModelo model : list) {
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
    private List<VectorPortafolioModelo> getModelosWithDate(LocalDate date, List<VectorPortafolioModelo> modelList) {
        List<VectorPortafolioModelo> newList = new ArrayList<>();
        for (VectorPortafolioModelo pm : modelList) {
            if (pm.getCreado().compareTo(date) == 0) {
                newList.add(pm);
            }
        }
        return newList;
    }

    public List<VectorPortafolioModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Estrategia e) {
        List<VectorPortafolioModelo> newList = new ArrayList<>();
        for (Map.Entry<String, Long> entry : models.entrySet()) {
            String key = entry.getKey();
            Long value = entry.getValue();
            Activo a = activoRepository.findFirstByClavePizarra(key);
            VectorPortafolioModelo pm = new VectorPortafolioModelo(a, new Double(value), e);
            newList.add(pm);
        }
        return newList;
    }

}
