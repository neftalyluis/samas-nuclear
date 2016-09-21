/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.dto.VectorActivoDTO;
import mx.samas.service.ActivoService;
import mx.samas.service.VectorActivoService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class VectorActivoItemWriter implements ItemWriter<VectorActivoDTO> {

    @Autowired
    private VectorActivoService vectorActivoService;

    @Autowired
    private ActivoService activoService;

    @Override
    public void write(List<? extends VectorActivoDTO> items) throws Exception {
        for (VectorActivoDTO item : items) {
            Activo a = activoService.getByClavePizarra(item.getClavePizarra());
            VectorActivo va = item.getVector();
            va.setActivo(a);
            vectorActivoService.createVectorActivo(va);
        }
    }

}
