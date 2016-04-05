/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neftaly
 */
@XmlRootElement
public class Message {

    private String name;
    private int number;
    private boolean isCool;
    
    public Message(){
        this.name = "TEST";
        this.number = 13;
        this.isCool = true;
    }

    public Message(String newName, int number, boolean isCool) {
        this.name = newName;
        this.number = number;
        this.isCool = isCool;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isIsCool() {
        return isCool;
    }

    public void setIsCool(boolean isCool) {
        this.isCool = isCool;
    }

}
