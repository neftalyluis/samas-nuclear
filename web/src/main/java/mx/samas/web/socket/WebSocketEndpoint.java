/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author neftaly
 */

@ServerEndpoint("/websocket")
public class WebSocketEndpoint implements Serializable {

    @Inject
    private QueueSenderSessionBean senderBean;
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void onOpen(final Session session) {
        try {
            session.getBasicRemote().sendText("session opened");
            sessions.add(session);

            if (senderBean == null) {
                Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.INFO, "senderBean is null");
            }
        } catch (Exception ex) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void onMessage(final String message, final Session client) {
        try {
            client.getBasicRemote().sendText("sending message to SessionBean...");
        } catch (IOException ex) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (senderBean != null) {
            senderBean.sendMessage(message);
        }
    }

    @OnClose
    public void onClose(final Session session) {
        try {
            session.getBasicRemote().sendText("WebSocket Session closed");
            sessions.remove(session);
        } catch (Exception ex) {
            Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void onJMSMessage(@Observes @WSJMSMessage javax.jms.Message msg) {
        Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.INFO, "Got JMS Message at WebSocket!");

        sessions.forEach(s -> {
            try {
                s.getBasicRemote().sendText("message from JMS: " + msg.getBody(String.class));
            } catch (IOException | JMSException ex) {
                Logger.getLogger(WebSocketEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
