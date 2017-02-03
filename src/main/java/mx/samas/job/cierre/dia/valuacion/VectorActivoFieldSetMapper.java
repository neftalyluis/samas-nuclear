/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.time.LocalDate;
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
     * @param fieldSet Variable de tipo FieldSet que almacenara un conjunto de cadenas y las agrupa.
     * @return Un nuevo constructor VectorActivoDTO con sus respectivos parametros.
     * @throws BindException
     */
    @Override
    public VectorActivoDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        String clavePizarra = fieldSet.readString(1) + "_" + fieldSet.readString(2) + "_" + fieldSet.readString(3);
        return new VectorActivoDTO(clavePizarra, LocalDate.now(), fieldSet.readDouble(5));
    }

}
