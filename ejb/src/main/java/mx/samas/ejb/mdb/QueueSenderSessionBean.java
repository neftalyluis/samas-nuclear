/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 *
 * @author neftaly
 */
@Stateless
public class QueueSenderSessionBean {

    @Resource(mappedName = "/jms/myQueue")
    private Queue myQueue;

    @Inject
    private JMSContext jmsContext;

    public void sendMessage(String message) {
        System.out.println("Pasamos por el SessionBean, papu");
        jmsContext.createProducer().send(myQueue, message);
    }

}
