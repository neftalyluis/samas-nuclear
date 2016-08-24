/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.util.ArrayList;
import java.util.Date;
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

    private Date getMaxDate(List<VectorPortafolioModelo> list) {
        Date maxDate = null;
        for (VectorPortafolioModelo model : list) {
            Date dateFromEntity = model.getCreado();
            if (maxDate == null) {
                maxDate = dateFromEntity;
            } else if (dateFromEntity.compareTo(maxDate) > 0) {
                maxDate = dateFromEntity;

            }
        }
        return maxDate;
    }

    private List<VectorPortafolioModelo> getModelosWithDate(Date date, List<VectorPortafolioModelo> modelList) {
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
        for (Map.Entry<String, Double> entry : models.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            Activo a = activoRepository.findFirstByClavePizarra(key);
            VectorPortafolioModelo pm = new VectorPortafolioModelo(a, value, e);
            newList.add(pm);
        }
        return newList;
    }

}