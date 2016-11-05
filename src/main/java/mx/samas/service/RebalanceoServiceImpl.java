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
    public List<Double> presupuestoParaPortafolio(Portafolio p) {

        //pi*qi
        HashMap<String, Double> mapPrecioPorPosicion = new HashMap<>();
        //ai
        HashMap<String, Double> mapDianas = new HashMap<>();
        //vj
        HashMap<String, Double> mapVj = new HashMap<>();
        //Delta vi
        HashMap<String, Double> mapPresupuesto = new HashMap<>();

        //Suponiendo que no es sabado o domingo o dia inhabil, hay que optimizar esto
        LocalDate hoy = LocalDate.now().minusDays(1);
        Date hoyDate = Date.from(hoy.atStartOfDay(ZoneId.systemDefault()).toInstant());

        //Obtenemos las posiciones del dia anterior
        List<VectorPosicion> posicionesAyer = vectorPosicionRepository.findByPortafolioAndFecha(p, hoyDate);
        if (posicionesAyer.isEmpty()) {
            //Esta virgen el portafolio
            
        } else {
            //No lo esta, LMAO
        }
        
        
        //Obtenemos las dianas del Portafolio
        List<VectorPortafolioModelo> dianas = portafolioModeloRepository.findByEstrategiaAndCreado(p.getEstrategia(), hoyDate);

        return null;

    }

    private Double presupuesto(Double piqi, Double ai, Double vj) {
        return (ai / 1 - ai) * vj - piqi;
    }

}
