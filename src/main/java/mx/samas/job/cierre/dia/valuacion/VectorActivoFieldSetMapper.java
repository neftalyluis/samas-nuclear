/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.time.LocalDate;
import java.time.ZoneId;
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

    private LocalDate fechaL;

    public VectorActivoFieldSetMapper(Date fecha) {
        this.fechaL = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * @param fieldSet Variable de tipo FieldSet que almacenara un conjunto de
     * cadenas y las agrupa.
     * @return Un nuevo constructor VectorActivoDTO con sus respectivos
     * parametros.
     * @throws BindException
     */
    @Override
    public VectorActivoDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        String clavePizarra = fieldSet.readString(1) + "_" + fieldSet.readString(2) + "_" + fieldSet.readString(3);
        return new VectorActivoDTO(clavePizarra, fechaL, fieldSet.readDouble(5));
    }

}
