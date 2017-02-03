/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.rebalanceo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import mx.samas.domain.Estrategia;
import mx.samas.domain.VectorActivo;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.VectorPosicion;

/** TENGO DUDAS AQUI
 *
 * @author samas
 */
public class Presupuesto {

    private static final Logger LOG = Logger.getLogger(Presupuesto.class.getName());

    /**
     * @param precios Variable de tipo List, que almacena una lista de VectoresActivo.
     * @param fechaValor Variable de tipo Long
     * @param e Variable de tipo Estrategia
     * @param ultimasPosiciones Variable de tipo List, que almacena una lista de VectoresPosicion.
     * @param gradoBalance Variable de tipo Double
     * @param creditoMargen Variable de tipo Booleano, solo puede tener como valores "verdadero" o "falso".
     * @param liquidez Variable de tipo Double 
     */
    public Presupuesto(List<VectorActivo> precios, Long fechaValor, Estrategia e,
            List<VectorPosicion> ultimasPosiciones, Double gradoBalance,
            Boolean creditoMargen, Double liquidez) {

        //Valiacion del grado balance
        HashMap<String, TuplePortafolio> mapaTupla = createFromPosicionAndEstrategia(ultimasPosiciones, e.getEstrategiaModelo());
        checarDesbalance(mapaTupla, gradoBalance, liquidez);

        //Lineas 97 a 101 de Julia
//        if (creditoMargen) {
//
//        }
        Double deltaA = null;
    }

    private Double calcularDiana() {
        
//        for(){
//            
//        }
        
        return null;
    }

    private HashMap<String, TuplePortafolio> createFromPosicionAndEstrategia(List<VectorPosicion> posiciones, List<VectorPortafolioModelo> modelos) {
        HashMap<String, TuplePortafolio> tuplaMap = new HashMap<>();

        for (VectorPosicion posicion : posiciones) {
            tuplaMap.put(posicion.getActivo().getClavePizarra(), new TuplePortafolio(posicion.getValuacion(), Double.NaN));
        }

        for (VectorPortafolioModelo modelo : modelos) {
            TuplePortafolio tupla = tuplaMap.get(modelo.getActivo().getClavePizarra());
            if (tupla == null) {
                LOG.log(Level.INFO, "No existe posicion en el modelo :{0}", modelo.getActivo().getClavePizarra());
            } else {
                tupla.setDiana(modelo.getDiana());
                tuplaMap.put(modelo.getActivo().getClavePizarra(), tupla);
            }
        }

        return tuplaMap;
    }

    private void checarDesbalance(HashMap<String, TuplePortafolio> mapaTupla, Double gradoBalance, Double liquidez) {
        for (Map.Entry<String, TuplePortafolio> entry : mapaTupla.entrySet()) {
            Double balance = entry.getValue().getPosicion() * (1 - liquidez) / entry.getValue().getDiana();
            if (0.0 > gradoBalance || gradoBalance > balance) {
                throw new GradoBalanceFueraDeRangoException("Grado Balance fuera de rango para: " + entry.getKey());
            }
        }
    }
}
