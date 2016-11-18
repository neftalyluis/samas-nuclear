/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.job;

import java.util.HashMap;
import mx.samas.domain.elastic.VectorActivoPropiedadValor;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 * @author samas
 */
class VectorActivoPropiedadValorFieldSetMapper implements FieldSetMapper<VectorActivoPropiedadValor> {

    @Override
    public VectorActivoPropiedadValor mapFieldSet(FieldSet fieldSet) throws BindException {
        VectorActivoPropiedadValor va = new VectorActivoPropiedadValor();
        va.setId(fieldSet.readString(1) + "_" + fieldSet.readString(2) + "_"
                + fieldSet.readString(3) + "_" + fieldSet.readString(0));

        HashMap<String, Object> propiedadesValor = new HashMap<>();

        for (int i = 3; i < fieldSet.getFieldCount(); i++) {
            if (!fieldSet.readString(i).equals("-") && !fieldSet.readString(i).equals("NA")) {

                propiedadesValor.put("propiedad: " + i, fieldSet.readString(i).replace('-', '\0'));
            }
        }

        va.setPropiedadesValor(propiedadesValor);
        return va;
    }

}
