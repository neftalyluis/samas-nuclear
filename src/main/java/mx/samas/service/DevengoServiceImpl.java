/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import mx.samas.domain.Flujo;
import mx.samas.domain.CalendarioComercial;
import org.springframework.stereotype.Service;

/**
 *
 * @author samas
 */
@Service
public class DevengoServiceImpl implements DevengoService {

    @Override
    public Double calculate(Double tipoDeCambio, Long posicion, Double valorNominal, Double tasa, Double dias) {
        return tipoDeCambio * posicion * valorNominal * tasa * dias;
    }

    @Override
    public Long calculaPlazoComercial(LocalDate fechaInicio, LocalDate fechaTermino, CalendarioComercial calendario) {
        Long noHabiles = 0L;
        for (LocalDate d : calendario.getNoHabiles()) {
            if (estaEntreFechas(fechaInicio, fechaTermino, d)) {
                noHabiles++;
            }
        }
        return ChronoUnit.DAYS.between(fechaInicio, fechaTermino) - noHabiles;
    }

    boolean estaEntreFechas(LocalDate inicio, LocalDate fin, LocalDate fecha) {
        return !fecha.isBefore(inicio) && !fecha.isAfter(fin);
    }

    @Override
    public Double calculaDevengo(Flujo bono, LocalDate dia, Long posicion, Double valorNominal, Double tipoDeCambio, LocalDate inicioBono) {
        return tipoDeCambio * posicion * bono.getTasaReferencia().getTasa() * calculaPlazoComercial(inicioBono, dia, bono.getMercado().getCalendario());
    }

}
