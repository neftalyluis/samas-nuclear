/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.util.List;
import java.util.logging.Logger;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.VectorActivo;
import mx.samas.service.VectorActivoService;
import mx.samas.util.propiedad.reader.ActivoPropiedadReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class VectorPropiedadesItemProcessor implements ItemProcessor<Activo, VectorActivo> {
    
    private static final Logger LOG = Logger.getLogger(VectorPropiedadesItemProcessor.class.getName());
    
    @Autowired
    private VectorActivoService vectorActivoService;
    
    @Override
    public VectorActivo process(Activo item) throws Exception {
        
        VectorActivo va = vectorActivoService.getLastVectorFromActivo(item);
//        List<ActivoPropiedad> propiedadVectorialList = item.getPropiedades();
//        ActivoPropiedadReader a;
//        a.execute();
        
        return va;
    }
    
}
