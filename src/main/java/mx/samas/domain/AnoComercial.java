/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain;

/**
 *
 * @author samas
 */
public enum AnoComercial {
    BISIESTO(366),
    COMERCIAL(360),
    NATURAL(365);
    
    private final int ano;

    AnoComercial(int ano) {
        this.ano = ano;
    }

    public int getAno() {
        return this.ano;
    }
}
