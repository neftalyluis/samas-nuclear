/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.io.StringReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author neftaly
 */
public class MessageDecoder implements Decoder.Text<Message> {

    @Override
    public Message decode(String arg0) throws DecodeException {
        Message m = null;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(Message.class);
            Unmarshaller unmarsh = jaxbContext.createUnmarshaller();

            StringReader sr = new StringReader(arg0);
            m = (Message) unmarsh.unmarshal(sr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public boolean willDecode(String arg0) {
        return (arg0 != null);
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init decode");
    }

    @Override
    public void destroy() {
        System.out.println("destroy decode");
    }

}
