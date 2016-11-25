/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.posicion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import mx.samas.domain.Activo;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPosicion;
import mx.samas.service.BitacoraService;
import mx.samas.service.PortafolioService;
import mx.samas.service.VectorPosicionService;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author samas
 */
public class VectorPosicionProcessor implements ItemProcessor<Cuenta, List<VectorPosicion>> {

    @Autowired
    private VectorPosicionService vectorPosicionService;

    @Autowired
    private BitacoraService bitacoraService;
    
    @Autowired
    private PortafolioService portafolioService;

    /**
     * Processor que lee cada cuenta y genera las nuevas posiciones desde la
     * Bitacora
     *
     * @param item Cada cuenta que envia el Reader
     * @return La lista de Posiciones Nuevas a Persistir
     * @throws Exception
     */
    @Override
    public List<VectorPosicion> process(Cuenta item) throws Exception {

        /**
         * ¿Seria mejor idea usar un HashMap? Yo digo que si :v
         */
        List<VectorPosicion> posicionesNuevas = new ArrayList<>();

        
        //Iteramos en todos los portafolios
        for (Portafolio p : portafolioService.getPortafoliosFromCuenta(item)) {

            //Obtenemos las posiciones de ese portafolio y convertimos a un Map
            Map<Activo, VectorPosicion> posicionesActuales
                    = vectorPosicionService
                    .getLastPosicionesFromPortafolio(p)
                    .stream()
                    .collect(
                            Collectors.toMap(VectorPosicion::getActivo, Function.identity()));

            //Hacemos sumatoria con un Lambda
            bitacoraService
                    .getBitacoraListFromLastDay(p).stream().forEach((b) -> {
                if (posicionesActuales.containsKey(b.getActivo())) {
                    VectorPosicion modificar = posicionesActuales.get(b.getActivo());
                    Long cantidad = modificar.getCantidad() + b.getTitulos();
                    Double valuacion = modificar.getValuacion() + b.getImporte();
                    modificar.setCantidad(cantidad);
                    modificar.setValuacion(valuacion);
                    modificar.setFecha(new Date());
                    posicionesActuales.put(b.getActivo(), modificar);
                } else {
                    VectorPosicion nuevaPosicion = new VectorPosicion();
                    nuevaPosicion.setActivo(b.getActivo());
                    nuevaPosicion.setCantidad(b.getTitulos());
                    nuevaPosicion.setFecha(new Date());
                    nuevaPosicion.setPortafolio(p);
                    nuevaPosicion.setValuacion(b.getImporte());
                    posicionesActuales.put(b.getActivo(), nuevaPosicion);
                }
            });

            posicionesNuevas.addAll(posicionesActuales.values());
        }
        return posicionesNuevas;
    }

}
