/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.posicion;

import java.util.List;
import mx.samas.domain.VectorPosicion;
import mx.samas.service.VectorPosicionService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class VectorPosicionWriter implements ItemWriter<List<VectorPosicion>> {

    @Autowired
    private VectorPosicionService vectorPosicionService;

    @Override
    public void write(List<? extends List<VectorPosicion>> items) throws Exception {
        items.stream().forEach((listaPosiciones) -> {
            vectorPosicionService.persistPosiciones(listaPosiciones);
        });
    }

}
