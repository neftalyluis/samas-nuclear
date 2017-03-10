/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.bootstraping.activo;

import mx.samas.domain.Accion;
import mx.samas.domain.Activo;
import mx.samas.domain.Bono;
import mx.samas.domain.Derivado;
import mx.samas.domain.Moneda;
import mx.samas.domain.TipoActivo;
import mx.samas.util.TipoActivoResolver;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 *
 * Este FieldSetMapper se encarga de convertir el FieldSet del Job a un tipo de
 * Activo existente
 *
 * @author samas
 */
public class ActivoFieldSetMapper implements FieldSetMapper<Activo> {

    private TipoActivoResolver tipoActivoResolver;

    @Override
    public Activo mapFieldSet(FieldSet fieldSet) throws BindException {

        tipoActivoResolver = TipoActivoResolver.getInstance();
   
        Activo a = null;
        switch (tipoActivoResolver.resolveFromTipoValor(fieldSet.readString(1))) {
            case ACCION:
                a = new Accion(fieldSet.readString(1), fieldSet.readString(2),
                        fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);
                a.setTipo(TipoActivo.ACCION);
                break;
            case BONO:
                a = new Bono(fieldSet.readString(1), fieldSet.readString(2),
                        fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);
                a.setTipo(TipoActivo.BONO);

                break;
            case DERIVADO:
                a = new Derivado(fieldSet.readString(1), fieldSet.readString(2),
                        fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);
                a.setTipo(TipoActivo.DERIVADO);

                break;
            case MONEDA:
                a = new Moneda(fieldSet.readString(1), fieldSet.readString(2),
                        fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);
                a.setTipo(TipoActivo.MONEDA);

                break;
            case ACTIVO:
                a = new Activo(fieldSet.readString(1), fieldSet.readString(2),
                        fieldSet.readString(3), null, fieldSet.readString(9), null, Boolean.FALSE, null);
                a.setTipo(TipoActivo.ACTIVO);

                break;
        }
        return a;
    }
}
