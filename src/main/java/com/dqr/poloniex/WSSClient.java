package com.dqr.poloniex;

import com.dqr.poloniex.handler.PoloniexSubscription;
import com.dqr.poloniex.handler.PoloniexSubscriptionExceptionHandler;
import lombok.extern.java.Log;
import rx.functions.Action1;
import ws.wamp.jawampa.ApplicationError;
import ws.wamp.jawampa.PubSubData;
import ws.wamp.jawampa.WampClient;
import ws.wamp.jawampa.WampClientBuilder;
import ws.wamp.jawampa.transport.netty.NettyWampClientConnectorProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

/**
 * @author David
 */
@Log
public class WSSClient implements AutoCloseable {
    // private final static Logger LOG = LogManager.getLogger();
    private final WampClient wampClient;
    private final Map<String, Action1<PubSubData>> subscriptions;

    public WSSClient(String uri, String realm) throws ApplicationError, Exception {
        this.subscriptions = new HashMap<>();
        WampClientBuilder builder = new WampClientBuilder();
        builder.withConnectorProvider(new NettyWampClientConnectorProvider())
                .withUri(uri)
                .withRealm(realm)
                .withInfiniteReconnects()
                .withReconnectInterval(5, TimeUnit.SECONDS);

        wampClient = builder.build();
    }

    public void subscribe(PoloniexSubscription feedEventHandler) {
        this.subscriptions.put(feedEventHandler.feedName, feedEventHandler);
    }

    /***
     *
     * @param runTimeInMillis The subscription time expressed in milliseconds. The minimum runtime is 1 minute.
     */
    public void run(long runTimeInMillis) {
        try {
            log.info("WSSCient.run() : Enter");
            wampClient.statusChanged().subscribe((WampClient.State newState) -> {
                if (newState instanceof WampClient.ConnectedState) {
                    log.info("Connected");

                    for (Entry<String, Action1<PubSubData>> subscription : this.subscriptions.entrySet()) {
                        wampClient.makeSubscription(subscription.getKey()).subscribe(subscription.getValue(), new PoloniexSubscriptionExceptionHandler(subscription.getKey()));
                    }
                } else if (newState instanceof WampClient.DisconnectedState) {
                    log.info("Disconnected");
                } else if (newState instanceof WampClient.ConnectingState) {
                    log.info("Connecting...");
                }
            });

            wampClient.open();
            long startTime = System.currentTimeMillis();

            while (wampClient.getTerminationFuture().isDone() == false && (startTime + runTimeInMillis > System.currentTimeMillis())) {
                TimeUnit.MINUTES.sleep(1);
            }
        } catch (Exception ex) {
            log.log(java.util.logging.Level.SEVERE, String.format("Caught exception - %s", ex.getMessage()), ex);
        } finally {
            log.info("WSSCient.run() : Exit");
        }
    }

    @Override
    public void close() throws Exception {
        wampClient.close().toBlocking().last();
    }
}