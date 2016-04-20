/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import javax.ejb.MessageDriven;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.jms.JMSDestinationDefinition;
import javax.jms.MessageListener;

/**
 *
 * @author neftaly
 */
@JMSDestinationDefinition(
        name = "java:app/jms/myQueue",
        interfaceName = "javax.jms.Queue",
        destinationName = "myQueue"
)
@MessageDriven(mappedName = "java:app/jms/myQueue")
public class WebSocketMDB implements MessageListener {

    @Inject
    @WSJMSMessage
    Event<javax.jms.Message> jmsEvent;

    @Override
    public void onMessage(javax.jms.Message message) {
        jmsEvent.fire(message);
    }

}
