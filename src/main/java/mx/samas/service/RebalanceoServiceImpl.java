/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import mx.samas.domain.Activo;
import mx.samas.domain.Portafolio;
import mx.samas.domain.VectorPortafolioModelo;
import mx.samas.domain.VectorPosicion;
import mx.samas.repository.PortafolioModeloRepository;
import mx.samas.repository.VectorPosicionRepository;
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

    @Override
    public HashMap<String, Double> presupuestoParaPortafolio(Portafolio p) {

        ///y Vt? D:
        //Suponiendo que no es sabado o domingo o dia inhabil, hay que optimizar esto
        LocalDate hoy = LocalDate.now().minusDays(1);
        Date hoyDate = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //pi*qi
        HashMap<String, Double> mapPrecioPorPosicion = new HashMap<>();
        //Obtenemos las posiciones del dia anterior
        List<VectorPosicion> posicionesAyer = vectorPosicionRepository.findByPortafolioAndFecha(p, hoyDate);
        for (VectorPosicion vp : posicionesAyer) {
            mapPrecioPorPosicion.put(vp.getActivo().getClavePizarra(), vp.getValuacion() * vp.getCantidad());
        }

        //ai
        HashMap<String, Double> mapDianas = new HashMap<>();
        //Obtenemos las dianas del Portafolio
        List<VectorPortafolioModelo> dianas = portafolioModeloRepository.findByEstrategiaAndCreado(p.getEstrategia(), hoyDate);
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

}
