/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.io.StringWriter;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

/**
 *
 * @author neftaly
 */
public class MessageEncoder implements Encoder.Text<Message> {

    @Override
    public String encode(Message arg0) throws EncodeException {
        JAXBContext jaxbContext = null;
        StringWriter st = null;
        try {
            jaxbContext = JAXBContext.newInstance(Message.class);

            Marshaller marshaller = jaxbContext.createMarshaller();
            st = new StringWriter();
            marshaller.marshal(arg0, st);
            System.out.println("OutGoing XML " + st.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return st.toString();
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println("init encode");
    }

    @Override
    public void destroy() {
        System.out.println("destroy enconde");
    }

}
