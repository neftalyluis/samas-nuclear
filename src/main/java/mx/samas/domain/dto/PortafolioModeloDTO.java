/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.domain.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author samas
 */
public class PortafolioModeloDTO extends HashMap<String, Double> {

    public boolean validate() {
        Double total = 0.0;
        for (Map.Entry<String, Double> entry : this.entrySet()) {
            total += entry.getValue();
        }
        
        return total == 100.0;
    }
}
