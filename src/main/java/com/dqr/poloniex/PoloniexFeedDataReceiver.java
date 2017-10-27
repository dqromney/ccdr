package com.dqr.poloniex;

import com.dqr.IDataReceiver;
import com.dqr.poloniex.handler.PoloniexSubscription;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Crypto Currency Data Receiver (Poloniex)
 *
 * @see https://github.com/TheCookieLab/poloniex-api-java/blob/master/src/main/java/com/cf/example/PoloniexWSSClientExample.java
 */
@Log
public class PoloniexFeedDataReceiver implements IDataReceiver {

    final static CountDownLatch messageLatch = new CountDownLatch(1);

    private static final String ENDPOINT_URL = "wss://api.poloniex.com";
    private static final String DEFAULT_REALM = "realm2";

    private WSSClient poloniexWSSClient;

    public void init(String[] args) throws IOException {
        log.info("init()...");
    }

    public void execute() {
        log.info("execute()...");
        try {
            new PoloniexFeedDataReceiver().run();

        } catch (Exception e) {
            log.severe(String.format("An exception occurred when running PoloniexWSSClientExample - %1$s", e.getMessage()));
            System.exit(-1);
        }
    }

    public void run() throws Exception {
        try (WSSClient poloniexWSSClient = new WSSClient(ENDPOINT_URL, DEFAULT_REALM)) {
            poloniexWSSClient.subscribe(PoloniexSubscription.TICKER);
            poloniexWSSClient.run(60000);
        }
    }

}
