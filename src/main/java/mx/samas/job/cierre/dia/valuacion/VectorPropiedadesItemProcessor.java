/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.valuacion;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import mx.samas.domain.Activo;
import mx.samas.domain.ActivoPropiedad;
import mx.samas.domain.ActivoPropiedadValor;
import mx.samas.domain.FuenteDatos;
import mx.samas.domain.VectorActivo;
import mx.samas.service.VectorActivoService;
import mx.samas.util.ActivoPropiedadReader;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author samas
 */
public class VectorPropiedadesItemProcessor implements ItemProcessor<Activo, VectorActivo> {

    private static final Logger LOG = Logger.getLogger(VectorPropiedadesItemProcessor.class.getName());

    @Autowired
    private VectorActivoService vectorActivoService;

    @Override
    public VectorActivo process(Activo item) throws Exception {

        VectorActivo va = vectorActivoService.getLastVectorFromActivo(item);

        List<ActivoPropiedad> propiedadVectorialList = item.getPropiedades();

        if (propiedadVectorialList.isEmpty()) {
            LOG.log(Level.INFO, "{0} : No tiene Propiedades o es lista Nula", item.getClavePizarra());
            return va;
        }

        ActivoPropiedadReader readerPip = new ActivoPropiedadReader(
                item.getClavePizarra(), "VectorTest.csv",
                propiedadVectorialList
                .stream()
                .filter(ap -> ap.getOrigenDatos() == FuenteDatos.VECTOR_PIP)
                .collect(Collectors.toList()));
        readerPip.execute();

        ActivoPropiedadReader readerTyc = new ActivoPropiedadReader(
                item.getClavePizarra(), "TyC.csv", propiedadVectorialList
                .stream()
                .filter(ap -> ap.getOrigenDatos() == FuenteDatos.TERMINOS_PIP)
                .collect(Collectors.toList()), '|');
        readerTyc.execute();

        //Por ahora exceptuamos las propiedades del CSV de Usuario y las de Julia
        List<ActivoPropiedadValor> lapv = new ArrayList<>();
        lapv.addAll(readerTyc.getPropiedadesValor());
        lapv.addAll(readerPip.getPropiedadesValor());

        va.setPropiedadesValor(lapv);

        return va;
    }

}
