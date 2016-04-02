/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.mdb;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 *
 * @author neftaly
 */
@JMSDestinationDefinition(
        name = "/jms/myQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "myQueue"
)
@MessageDriven(mappedName = "/jms/myQueue")
/**
 * El pedo esta en el evento de CDI del WS, checalo
 * 
 */
public class WebSocketMDB implements MessageListener {

    @Inject
    @WSJMSMessage
    Event<Message> jmsEvent;

    @Override
    public void onMessage(Message msg) {

        System.out.println("Pasamos por el MDB, papu <3");
        jmsEvent.fire(msg);
    }

}
