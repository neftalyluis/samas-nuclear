/*
 * Copyright 2017 JoinFaces.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mx.samas.rebalanceo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.samas.domain.Estrategia;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.VectorPosicion;

/**
 *
 * @author samas
 */
public class Eversion {

    private EversionDeterminada determinada;
    private EversionIndeterminada indeterminada;

    private Map<String, Double> mapaAforo;
    private Map<String, Double> mapaTitulos;
    private Map<String, Double> mapaImporteDeterminado;
    private Map<String, Double> mapaParticipacion;
    private Map<String, Double> mapaImporteIndeterminado;

    /**
     * @param e Variable de tipo Estrategia
     * @param posicionesList Variable de tipo List, que almacena una lista de VectoresPosicion.
     * @param modeloList Variable de tipo List, que almacena una lista de VectoresPortafolioModelo.
     * @param preciosList Variable de tipo List, que almacena una lista de VectoresActivo.
     * @param p Variable de tipo Presupuesto
     */
    public Eversion(Estrategia e, List<VectorPosicion> posicionesList,
            List<VectorPortafolioModelo> modeloList, List<VectorActivo> preciosList,
            Presupuesto p) {

    }

    private Map<String, Double> calculaAforo(Estrategia e, List<VectorPosicion> posiciones) {

        HashMap<String, Double> map = new HashMap<>();
        Double concentracion = e.getConcentracionPorExposicion();
        for (VectorPosicion vp : posiciones) {
            map.put(vp.getActivo().getClavePizarra(), 
                    (concentracion - vp.getPorcentajeRespectoPortafolio()) * vp.getValuacion());
            
        }

        return map;
    }
    
    private Map<String, Double> calculaTitulos(){
        return null;
    }

    /**
     * @return the determinada
     */
    public EversionDeterminada getDeterminada() {
        return determinada;
    }

    /**
     * @return the indeterminada
     */
    public EversionIndeterminada getIndeterminada() {
        return indeterminada;
    }

}
