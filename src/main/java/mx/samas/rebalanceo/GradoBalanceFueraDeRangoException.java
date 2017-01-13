/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.rebalanceo;

/**
 *
 * @author samas
 */
public class GradoBalanceFueraDeRangoException extends RuntimeException{
    public GradoBalanceFueraDeRangoException(String causa){
        super(causa);
    }
}
