/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.errorhandling;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Esta excepcion se lanza cuando la suma de todos los SliceVector de una
 * Strategy no son iguales a 100
 *
 * @author neftaly
 */
@XmlRootElement
public class NotACompleteStrategyException extends RuntimeException {
    
    @XmlElement
    private String description;
    
    public NotACompleteStrategyException(){
        this.description = "LOL, hubo un error";
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

}
