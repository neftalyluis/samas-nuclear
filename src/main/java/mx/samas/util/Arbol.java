/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.util;

import java.util.ArrayList;
import java.util.List;
import mx.samas.domain.Grupo;

/**
 * Arbol generico
 *
 * @author samas
 * @param <T> el tipo que va a aceptar el Arbol
 */
public class Arbol<T extends Grupo> {

    private Nodo<T> root;

    public Arbol(T rootData) {
        root = new Nodo<>(rootData);
    }

    /**
     * Crea un Arbol desde una lista de Grupos
     *
     * @param listData
     */
    public Arbol(List<T> listData) {
        //Ordenamos por el grupo padre, donde cero es el nodo inicial
        listData.sort((o1, o2) -> o1.getGrupoPadre().compareTo(o2.getGrupoPadre()));
        //Insertamos el nodo inicial
        root = new Nodo<>(listData.get(0));
        listData.remove(0);
    }
    
    public T getValueAt(Long pos) {
        return null;
    }

    /**
     * 
     * @param pos
     * @param value 
     */
    public void setValueAt(Long pos, T value) {

    }

    /**
     *  xs
     * @param <T> 
     */
    public static class Nodo<T> {

        private T data;
        private Nodo<T> parent;
        private List<Nodo<T>> children;

        public Nodo(T rootData) {
            this.data = rootData;
            //Disculpame madre por mi vida loca
            this.parent = null;
            this.children = new ArrayList<>();

        }
    }
}
