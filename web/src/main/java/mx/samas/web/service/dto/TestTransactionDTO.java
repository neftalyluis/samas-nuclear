/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.service.dto;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 */
@XmlRootElement(name = "Transaction")
public class TestTransactionDTO {
    
    private Long entryID;
    private List<TestOperationDTO> operations;

    /**
     * @return the entryID
     */
    public Long getEntryID() {
        return entryID;
    }

    /**
     * @param entryID the entryID to set
     */
    public void setEntryID(Long entryID) {
        this.entryID = entryID;
    }

    /**
     * @return the operations
     */
    public List<TestOperationDTO> getOperations() {
        return operations;
    }

    /**
     * @param operations the operations to set
     */
    public void setOperations(List<TestOperationDTO> operations) {
        this.operations = operations;
    }
    
}
