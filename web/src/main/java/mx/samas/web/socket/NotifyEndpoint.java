/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.samas.web.socket;

import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author neftaly
 */
@ServerEndpoint(value = "/notifications", encoders = {MessageEncoder.class}, decoders = {MessageDecoder.class})
public class NotifyEndpoint implements Serializable {

    private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());

    @OnMessage
    public void broadcastFigure(Message msg, Session session) throws IOException, EncodeException {
        System.out.println("broadcastFigure: " + msg);
        for (Session peer : peers) {
            if (!peer.equals(session)) {
                peer.getBasicRemote().sendObject(msg);
            }
        }
    }

    @OnOpen
    public Message onOpen(Session peer) throws IOException, EncodeException {
        peers.add(peer);
        Message a = new Message("gbhnj", 0, true);
        System.out.println(peer.getId());
        peer.getBasicRemote().sendObject(a);
        return a;
    }

    @OnClose
    private void onClose(Session peer) {
        peers.remove(peer);
        System.out.println("Adios: "+peer.getId());
    }
}
