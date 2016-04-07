/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author neftaly
 */
public class MessageEncoder implements Encoder.Text<Message>{

    @Override
    public String encode(Message arg0) throws EncodeException {
        return arg0.getJson().toString();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("Init Encode");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Encode");
    }
    
}
