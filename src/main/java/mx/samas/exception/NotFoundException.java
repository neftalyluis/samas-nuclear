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
public class NotFoundException extends RuntimeException {

    /**
     * @param msg Esta variable guardara una cadena que representa un mensaje de excepcion de Elasticsearch.
     */
    public NotFoundException(String msg) {
        super(msg);
    }
}
