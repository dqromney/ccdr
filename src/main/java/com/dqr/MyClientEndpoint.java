package com.dqr;

import lombok.extern.java.Log;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by dqromney on 10/5/17.
 */
@Log
@ClientEndpoint
public class MyClientEndpoint {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
        try {
            String name = "Duke";
            System.out.println("Sending message to endpoint: " + name);
            session.getBasicRemote().sendText(name);
        } catch (IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

    @OnMessage
    public void processMessage(String message) {
        //System.out.println("\nReceived message in client: " + message);
        System.out.println(message);
        //PoloniexDataReceiver.messageLatch.countDown();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }
}
