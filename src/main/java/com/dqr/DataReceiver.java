package com.dqr;

import lombok.extern.java.Log;

import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Crypto Currency Data Receiver (HitBtc)
 */
@Log
public class DataReceiver implements IDataReceiver {

    final static CountDownLatch messageLatch = new CountDownLatch(1);

    public void init(String[] args) throws IOException {
        log.info("init()...");
    }

    public void execute() {
        log.info("execute()...");
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            // String uri = "ws://echo.websocket.org:80/";
            // String uri = "ws://demo-api.hitbtc.com:80/";
            String uri = "ws://api.hitbtc.com:80";
            System.out.println("Connecting to " + uri);
            container.connectToServer(MyClientEndpoint.class, URI.create(uri));
            messageLatch.await(100, TimeUnit.SECONDS);
        } catch (DeploymentException | InterruptedException | IOException ex) {
            log.log(Level.SEVERE, null, ex);
        }
    }

}
