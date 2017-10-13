package com.dqr;

import lombok.extern.java.Log;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.dto.marketdata.Trades;
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
        Trades trades;
        int count = 0;
        String lastId = "";
        long lastIdNum = 0;
        long beginTime;
        long endTime = -1000L * 60L;

        timer.schedule(new DataTask(service), 0, FETCH_FREQUENCY);
/*
        while(count++ < 100) {
//            beginTime = System.currentTimeMillis();
            trades = service.getTrades(CurrencyPair.BTC_USD);
//            trades = service.getTrades(CurrencyPair.BTC_USD, System.currentTimeMillis() - 1000L * 60L,
//                HitbtcTrades.HitbtcTradesSortField.SORT_BY_TIMESTAMP, HitbtcTrades.HitbtcTradesSortDirection.SORT_DESCENDING, lastIdNum, 10L);
//            trades = service.getTrades(CurrencyPair.BTC_USD, System.currentTimeMillis() - (endTime - beginTime),
//                HitbtcTrades.HitbtcTradesSortField.SORT_BY_TIMESTAMP, HitbtcTrades.HitbtcTradesSortDirection.SORT_DESCENDING, lastId, 1000L);
//            endTime = System.currentTimeMillis();
            if (trades.getTrades().size() > 0 && trades.getlastID() > lastIdNum) {
                System.out.println("\nTrades, last minute, Size= " + trades.getTrades().size());
                String finalLastId = lastId;
                long finalLastIdNum = lastIdNum;
                trades.getTrades().forEach((Trade trade) -> {
                    if (Long.valueOf(trade.getId()) > finalLastIdNum) {
                        System.out.println(trade.toString());
                    }
                });
                lastId = new String(String.valueOf(new Long(trades.getlastID())));
                lastIdNum = trades.getlastID();
            } else {
                System.out.print('.');
            }
        }
*/
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
