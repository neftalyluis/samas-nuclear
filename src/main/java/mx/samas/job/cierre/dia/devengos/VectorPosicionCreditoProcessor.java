/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.devengos;

import java.util.List;
import mx.samas.domain.Cuenta;
import mx.samas.domain.VectorPosicion;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author samas
 */
public class VectorPosicionCreditoProcessor implements ItemProcessor<Cuenta, List<VectorPosicion>>{

    @Override
    public List<VectorPosicion> process(Cuenta item) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
