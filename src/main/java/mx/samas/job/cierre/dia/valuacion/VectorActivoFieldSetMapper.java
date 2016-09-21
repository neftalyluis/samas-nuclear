/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.util.Date;
import mx.samas.domain.dto.VectorActivoDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 * @author samas
 */
public class VectorActivoFieldSetMapper implements FieldSetMapper<VectorActivoDTO> {

    /**
     * FALTA CAMBIAR VECTOR ACTIVO POR EL DTO!!!!
     *
     * @param fieldSet
     * @return
     * @throws BindException
     */
    @Override
    public VectorActivoDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        String clavePizarra = fieldSet.readString(1) + "_" + fieldSet.readString(2) + "_" + fieldSet.readString(3);
        VectorActivoDTO activo = new VectorActivoDTO(clavePizarra, new Date(), fieldSet.readDouble(5));
        return activo;
    }

}
