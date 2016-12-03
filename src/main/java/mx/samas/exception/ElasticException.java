/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.exception;

/**
 *
 * @author samas
 */
public class ElasticException extends RuntimeException {

    public ElasticException(String msg) {
        super(msg);
    }
}
