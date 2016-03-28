/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.batch;

import java.util.Date;
import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

/**
 *
 * @author neftaly
 */
@Named(value = "VectorAnaliticoProcessor")
public class VectorAnaliticoProcessor implements ItemProcessor{

    @Override
    public Object processItem(Object item) throws Exception {
        System.out.println("LOLPROCESS");
        return new Date();
    }
    
}
