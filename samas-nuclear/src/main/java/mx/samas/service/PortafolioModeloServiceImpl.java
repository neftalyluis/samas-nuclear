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
import mx.samas.domain.PortafolioModelo;
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
public class PortafolioModeloServiceImpl implements PortafolioModeloService {

    @Autowired
    private EstrategiaRepository estrategiaRepository;

    @Autowired
    private ActivoRepository activoRepository;

    @Override
    public List<PortafolioModelo> getLastPortafolioModeloFromEstrategia(Long id) {

        Estrategia estrategia = estrategiaRepository.findOne(id);
        List<PortafolioModelo> modelos = estrategia.getEstrategiaModelo();
        List<PortafolioModelo> lastModel = getModelosWithDate(getMaxDate(modelos), modelos);

        return lastModel;
    }

    @Override
    public List<PortafolioModelo> createNewPortafolioModeloListForEstrategia(Long id, PortafolioModeloDTO model) {

        Estrategia e = estrategiaRepository.findOne(id);
        List<PortafolioModelo> fromDTO = createPortafolioModeloListFromDTO(model, e);
        List<PortafolioModelo> fromEstrategia = e.getEstrategiaModelo();
        fromEstrategia.addAll(fromDTO);
        e.setEstrategiaModelo(fromEstrategia);
        estrategiaRepository.save(e);

        return fromDTO;
    }

    @Override
    public List<PortafolioModelo> getAllModelosFromEstrategia(Long id) {
        return estrategiaRepository.findOne(id).getEstrategiaModelo();
    }

    private Date getMaxDate(List<PortafolioModelo> list) {
        Date maxDate = null;
        for (PortafolioModelo model : list) {
            Date dateFromEntity = model.getCreado();
            if (maxDate == null) {
                maxDate = dateFromEntity;
            } else if (dateFromEntity.compareTo(maxDate) > 0) {
                maxDate = dateFromEntity;

            }
        }
        return maxDate;
    }

    private List<PortafolioModelo> getModelosWithDate(Date date, List<PortafolioModelo> modelList) {
        List<PortafolioModelo> newList = new ArrayList<>();
        for (PortafolioModelo pm : modelList) {
            if (pm.getCreado().compareTo(date) == 0) {
                newList.add(pm);
            }
        }
        return newList;
    }

    public List<PortafolioModelo> createPortafolioModeloListFromDTO(PortafolioModeloDTO models, Estrategia e) {
        List<PortafolioModelo> newList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : models.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue();
            Activo a = activoRepository.findFirstByClavePizarra(key);
            PortafolioModelo pm = new PortafolioModelo(a, value, e);
            newList.add(pm);
        }
        return newList;
    }

}
