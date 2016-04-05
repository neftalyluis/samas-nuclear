/**
 * Copyright © 2013, 2013, Oracle and/or its affiliates. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package mx.samas.ejb.mdb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;

/**
 * This is the SessionBean used by the WebSocket server endpoint to dispatch
 * incoming messages to the JMS Queue
 *
 * @author Bruno Borges <bruno.borges at oracle.com>
 */
@Stateless
public class QueueSenderSessionBean {

    @Resource(mappedName = "java:app/jms/myQueue")
    private Queue myQueue;

    @Inject
    private JMSContext jmsContext;

    public void sendMessage(String message) {
        System.out.println("Holi desde SB");
        jmsContext.createProducer().send(myQueue, message);
        System.out.println("Ya envie");
    }

}
