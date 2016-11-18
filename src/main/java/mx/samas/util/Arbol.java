/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util;

import java.util.ArrayList;

/**
 * Arbol generico
 *
 * @author samas
 * @param <T> el tipo que va a aceptar el Arbol
 */
public class Arbol<T> {

    private Nodo<T> root;

    public Arbol(T rootData) {
        root = new Nodo<>();
        root.data = rootData;
        root.children = new ArrayList<>();
    }

//    public void agregarHijo(T hijo) {
//        root.
//    }

    public static class Nodo<T> {

        private T data;
        private Nodo<T> parent;
        private ArrayList<Nodo<T>> children;
    }
}
