/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.job.cierre.dia.posicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
import mx.samas.domain.Cuenta;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPosicion;
import mx.samas.service.BitacoraService;
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
         * ¿Seria mejor idea usar un HashMap?
         */
        List<VectorPosicion> posicionesNuevas = new ArrayList<>();

        //Iteramos en todos los portafolios
        for (Portafolio p : item.getPortafolios()) {
            //Obtenemos las posiciones de ese portafolio
            List<VectorPosicion> posiciones = vectorPosicionService
                    .getLastPosicionesFromPortafolio(p);

            //Obtenemos las entradas de Bitacora que pertenezcan a ese Portafolio
            List<Bitacora> bitacoraParaPortafolio = bitacoraService
                    .getBitacoraListFromLastDay(p);

            HashMap<Activo, VectorPosicion> mapaPosicionParaPortafolio= new HashMap<>();
            
            //Iteramos en las posiciones de ese portafolio
            for (VectorPosicion pos : posiciones) {
                //Se va a cruzar con los que tenga el mismo activo
                for (Bitacora b : bitacoraParaPortafolio) {
                    //Si son del mismo tipo hacemos la operacion respectiva
                    if (pos.getActivo().equals(b.getActivo())) {
                        //Clonamos la Posicion, con nueva fecha
                        VectorPosicion nuevo = new VectorPosicion(pos);
                        Long cantidad = pos.getCantidad() + b.getTitulos();
                        Double valuacion = pos.getValuacion() + b.getImporte();

                        //Si en la suma ya no hay titulos, se refleja que ya no 
                        //hay posicion y por lo tanto ya no se persiste
                        if (cantidad != 0) {
                            nuevo.setCantidad(cantidad);
                            nuevo.setValuacion(valuacion);
                            //Agregamos a la lista a persistir
                            posicionesNuevas.add(nuevo);
                            //Quitamos ese elemento de la lista
                            bitacoraParaPortafolio.remove(b);
                        }

                    }
                }
            }

            //Si la lista al final del cruce no esta vacia, significa que entonces 
            //Hay posicion en nuevos activos, se crean sus respectivas
            if (!bitacoraParaPortafolio.isEmpty()) {
                for (Bitacora nuevasPosicion : bitacoraParaPortafolio) {
                    VectorPosicion vp = new VectorPosicion();
                    
                }
            }

        }
        return posicionesNuevas;
    }

}
