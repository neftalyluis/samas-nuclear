/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author neftaly
 */
public class Notification {

    private String sender;
    private List<String> clientList;
    private JsonObject message;

    /**
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * @param sender the sender to set
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * @return the clientList
     */
    public List<String> getClientList() {
        return clientList;
    }

    /**
     * @param clientList the clientList to set
     */
    public void setClientList(List<String> clientList) {
        this.clientList = clientList;
    }

    /**
     * @return the message
     */
    public JsonObject getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(JsonObject message) {
        this.message = message;
    }
}
