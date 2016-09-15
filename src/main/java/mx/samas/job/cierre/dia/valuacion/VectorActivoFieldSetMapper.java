/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import mx.samas.domain.VectorActivo;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 * @author samas
 */
public class VectorActivoFieldSetMapper implements FieldSetMapper<VectorActivo>{
/**
 * FALTA CAMBIAR VECTOR ACTIVO POR EL DTO!!!!
 * @param fieldSet
 * @return
 * @throws BindException 
 */
    @Override
    public VectorActivo mapFieldSet(FieldSet fieldSet) throws BindException {
        VectorActivo activo = new VectorActivo();
//        (fieldSet.readString(1), fieldSet.readString(2),
//                fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);

//        Activo a = new Activo(clean(record.get(1)), clean(record.get(2)),
//                clean(record.get(3)), null, clean(record.get(9)),
//                tipoActivoResolver.resolveFromTipoValor(clean(record.get(1))),
//                Boolean.FALSE, null);
//        		suggestedPodcast.setCategories(fieldSet.readString("CATEGORIES"));
        return activo;    }
    
}
