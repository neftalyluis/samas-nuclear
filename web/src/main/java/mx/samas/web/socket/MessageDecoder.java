/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonException;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 *
 * @author neftaly
 */
public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String arg0) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(arg0)).readObject();
        return new Message(jsonObject);
    }

    @Override
    public boolean willDecode(String arg0) {
        try {
            Json.createReader(new StringReader(arg0)).readObject();
            return true;
        } catch (JsonException e) {
            return false;
        }
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("Init Decode");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy Decode");
    }

}
