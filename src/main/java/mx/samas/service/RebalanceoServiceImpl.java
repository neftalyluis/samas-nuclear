/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mx.samas.domain.Activo;
import mx.samas.domain.Bitacora;
import mx.samas.domain.Estrategia;
import mx.samas.domain.Grupo;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.VectorPosicion;
import mx.samas.repository.BitacoraRepository;
import mx.samas.repository.PortafolioModeloRepository;
import mx.samas.repository.VectorPosicionRepository;
import mx.samas.util.Arbol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class RebalanceoServiceImpl implements RebalanceoService {

    @Autowired
    private VectorPosicionRepository vectorPosicionRepository;

    @Autowired
    private PortafolioModeloRepository portafolioModeloRepository;

    @Autowired
    private BitacoraRepository bitacoraRepository;

    @Override
    public Map<String, Double> presupuestoParaPortafolio(Portafolio p, LocalDate fechaValor) {

        ///y Vt? D:
        //Suponiendo que no es sabado o domingo o dia inhabil, hay que optimizar esto
        //pi*qi
        HashMap<String, Double> mapPrecioPorPosicion = new HashMap<>();
        //Obtenemos las posiciones del dia anterior
        List<VectorPosicion> posicionesAyer = vectorPosicionRepository.findByPortafolioAndFecha(p, fechaValor);
        for (VectorPosicion vp : posicionesAyer) {
            mapPrecioPorPosicion.put(vp.getActivo().getClavePizarra(), vp.getValuacion() * vp.getCantidad());
        }

        //ai
        HashMap<String, Double> mapDianas = new HashMap<>();
        //Obtenemos las dianas del Portafolio
        List<VectorPortafolioModelo> dianas = portafolioModeloRepository.findByEstrategiaAndCreado(p.getEstrategia(), fechaValor);
        for (VectorPortafolioModelo vpm : dianas) {
            mapDianas.put(vpm.getActivo().getClavePizarra(), vpm.getDiana());
        }

        //vj
        HashMap<String, Double> mapVj = new HashMap<>();
        for (VectorPortafolioModelo vpm : dianas) {
            Double vj = dianas.stream()
                    .filter(v -> !v.getActivo().getClavePizarra()
                            .equalsIgnoreCase(vpm.getActivo().getClavePizarra()))
                    .mapToDouble(VectorPortafolioModelo::getDiana)
                    .sum();
            mapVj.put(vpm.getActivo().getClavePizarra(), vj);
        }

        //Delta vi
        HashMap<String, Double> mapPresupuesto = new HashMap<>();
        //Llamamos presupuesto() 
        for (String clavePizarra : mapDianas.keySet()) {
            mapPresupuesto.put(
                    clavePizarra,
                    presupuesto(
                            mapPrecioPorPosicion.get(clavePizarra),
                            mapDianas.get(clavePizarra),
                            mapVj.get(clavePizarra
                            )));
        }

        Activo m = p.getMonedaDenominacion();

        return mapPresupuesto;

    }

    private Double presupuesto(Double piqi, Double ai, Double vj) {
        return (ai / 1 - ai) * vj - piqi;
    }

    @Override
    public Double poderDeCompra(Portafolio p, LocalDate fechaValor) {

        LocalDate hoy = LocalDate.now();

        List<Bitacora> lb = bitacoraRepository
                .findByPortafolioAndOperacionAndLiquidacion(p, hoy, fechaValor);

        Double flujoEntreDias = lb.stream().mapToDouble(Bitacora::getPrecio).sum();

        return flujoEntreDias;
    }

    @Override
    public Map<Grupo, Double> balancePorGrupos(Portafolio p, LocalDate fechaValor) {

        //Hay que buscar que esto la haga la query, me caga que tengas que usar el Eager en la estrategia de fetch
        Estrategia e = p.getEstrategia();

        //Hay que crear una clase que encapsule esto y te regrese los nodos hijos y padres (:
        List<Grupo> listaGrupos = e.getGrupoLista();
        for (Grupo gr : listaGrupos) {
            if (gr.getGrupoPadre() == null) {
                Arbol<Grupo> arbolGrupos = new Arbol(gr);
            }
        }

        List<VectorPosicion> posicionesAyer = vectorPosicionRepository.findByPortafolioAndFecha(p, LocalDate.now());

        //Esto debe de salir en una query de JPQL, paps
        List<Activo> listActivosEnPosiciones = new ArrayList<>();
        for (VectorPosicion posicion : posicionesAyer) {
            listActivosEnPosiciones.add(posicion.getActivo());
        }

        return null;
    }

}
