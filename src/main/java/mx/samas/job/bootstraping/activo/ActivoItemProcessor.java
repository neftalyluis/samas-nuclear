/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.bootstraping.activo;

import mx.samas.domain.Activo;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author samas
 */
public class ActivoItemProcessor implements ItemProcessor<Activo, Activo> {

    @Override
    public Activo process(Activo i) throws Exception {
        return null;
    }

}
