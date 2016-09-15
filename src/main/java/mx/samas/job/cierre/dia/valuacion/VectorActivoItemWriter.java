/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.util.List;
import mx.samas.domain.VectorActivo;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author samas
 */
public class VectorActivoItemWriter implements ItemWriter<VectorActivo>{

    @Override
    public void write(List<? extends VectorActivo> items) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
