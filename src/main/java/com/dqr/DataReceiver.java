package com.dqr;

import lombok.extern.java.Log;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;


/**
 * Crypto Currency Data Receiver (HitBtc)
 */
@Log
public class DataReceiver {

    final static CountDownLatch messageLatch = new CountDownLatch(1);
    final static Long FETCH_FREQUENCY = 1000L * 5L;
    
    private Exchange hitbtcExchange;
    private MarketDataService marketDataService;

    public static void main(String[] args) throws IOException {
        DataReceiver dr = new DataReceiver();
        MarketDataService service = dr.init(args);
        dr.execute(service);
    }

    private MarketDataService init(String[] args) throws IOException {
        log.info("init()...");
        hitbtcExchange =  HitbtcUtils.createExchange();
        hitbtcExchange.remoteInit();
        return hitbtcExchange.getMarketDataService();
    }

    private void execute(MarketDataService service) throws IOException {
        log.info("execute()...");

        Timer timer = new Timer();

        timer.schedule(new DataTask(service, CurrencyPair.BTC_USD), 0, FETCH_FREQUENCY);

//        try {
//            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//            // String uri = "ws://echo.websocket.org:80/";
//            // String uri = "ws://demo-api.hitbtc.com:80/";
//            String uri = "ws://api.hitbtc.com:80";
//            System.out.println("Connecting to " + uri);
//            container.connectToServer(MyClientEndpoint.class, URI.create(uri));
//            messageLatch.await(100, TimeUnit.SECONDS);
//        } catch (DeploymentException | InterruptedException | IOException ex) {
//            log.log(Level.SEVERE, null, ex);
//        }
    }

}
