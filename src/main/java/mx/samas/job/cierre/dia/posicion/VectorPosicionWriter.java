    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.posicion;

import java.util.List;
import mx.samas.domain.VectorPosicion;
import org.springframework.batch.item.ItemWriter;

/**
 *
 * @author samas
 */
public class VectorPosicionWriter implements ItemWriter<VectorPosicion>{

    @Override
    public void write(List<? extends VectorPosicion> items) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
