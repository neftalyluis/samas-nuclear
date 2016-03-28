/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.batch;

import java.util.Date;
import javax.batch.api.chunk.AbstractItemReader;
import javax.inject.Named;

/**
 *
 * @author neftaly
 */
@Named(value = "VectorAnaliticoReader")
public class VectorAnaliticoReader extends AbstractItemReader {

    @Override
    public Object readItem() throws Exception {
        System.out.println("LOL HEHEHEHE");
        return new Date();
    }
}
