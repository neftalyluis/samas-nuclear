/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.bootstraping.activo;

import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.service.ActivoService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * ItemWriter que persiste todos los Activos creador por el Job
 *
 * @author samas
 */
public class ActivoItemWriter implements ItemWriter<Activo> {

    @Autowired
    private ActivoService activoService;

    @Override
    public void write(List<? extends Activo> list) throws Exception {
        for (Activo a : list) {
            activoService.createActivo(a);
        }
    }

}
