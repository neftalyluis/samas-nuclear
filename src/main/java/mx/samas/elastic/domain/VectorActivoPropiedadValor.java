/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.elastic.domain;

import java.util.Map;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 *
 * @author samas
 */
@Document(indexName = "vectoractivo", type = "VectorActivoPropiedadValor")
public class VectorActivoPropiedadValor {

    /**
     * id es igual a TV_EMISORA_SERIE_FECHA
     */
    @Id
    private String id;

    @Field(type = FieldType.Nested)
    private Map<String, Object> propiedadesValor;

    public VectorActivoPropiedadValor(String id, Map<String, Object> propiedadesValor) {
        this.id = id;
        this.propiedadesValor = propiedadesValor;
    }

    /**
     * @return the idx
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the propiedadesValor
     */
    public Map<String, Object> getPropiedadesValor() {
        return propiedadesValor;
    }

    /**
     * @param propiedadesValor the propiedadesValor to set
     */
    public void setPropiedadesValor(Map<String, Object> propiedadesValor) {
        this.propiedadesValor = propiedadesValor;
    }
}
