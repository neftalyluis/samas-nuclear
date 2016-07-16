/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.cliente;

import okhttp3.MediaType;

/**
 *
 * @author samas
 */
public class Configuration {

    public static final String URL = "http://localhost:8080/";
    public static final String USER = "samas";
    public static final String PASSWORD = "test";
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");
}
