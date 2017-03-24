/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.service;

import java.time.LocalDate;
import mx.samas.domain.Flujo;
import mx.samas.domain.CalendarioComercial;

/**
 *
 * @author samas
 */
public interface DevengoService {

    //Donde dias es division entre plazo comercial y año comercial
    public Double calculate(Double tipoDeCambio, Long posicion, Double unitario, Double tasa, Double dias);

    public Long calculaPlazoComercial(LocalDate fechaInicio, LocalDate fechaTermino, CalendarioComercial calendario);

    public Double calculaDevengo(Flujo bono, LocalDate dia, Long posicion, Double valorNominal, Double tipoDeCambio, LocalDate inicioBono);

}
