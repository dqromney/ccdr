package com.dqr.poloniex;

import com.dqr.hitbtc.dto.marketdata.MarketDataIncrementalRefresh;
import com.dqr.hitbtc.dto.marketdata.MarketDataSnapshotFullRefresh;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.java.Log;

import javax.websocket.*;
import java.io.IOException;
import java.util.logging.Level;

/**
 * Created by dqromney on 10/5/17.
 */
@Log
@ClientEndpoint
public class PoloniexEndpoint {
    public static final ObjectMapper mapper = new ObjectMapper();

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
    public void processMessage(String message) throws JsonProcessingException {
        // System.out.println("\nReceived message in client: " + message);
        // System.out.println(message);
//        MarketDataIncrementalRefresh marketDataInc;
//        MarketDataSnapshotFullRefresh marketDataFull;
//        if (message.contains("MarketDataSnapshotFullRefresh"))  {
//            marketDataFull = parseMarketDataSnapshotFullRefresh(message);
//            System.out.println(marketDataFull.toString());
//        } else {
//            marketDataInc = parseMarketDataIncrementalRefresh(message);
//            System.out.println(marketDataInc.toString());
//        }
        //Pretty JSON print
        // System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marketData));
        
        // HitbtcFeedDataReceiver.messageLatch.countDown();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    private MarketDataIncrementalRefresh parseMarketDataIncrementalRefresh(String pMssage) {
        MarketDataIncrementalRefresh marketData = null;
        try {
            // ObjectMapper mapper = new ObjectMapper();
            marketData = mapper.readValue(pMssage, MarketDataIncrementalRefresh.class);
            // System.out.println(marketData.toString());
        
            //Pretty print
            // System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marketData));
        // JsonParseException
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return marketData;
    }

    private MarketDataSnapshotFullRefresh parseMarketDataSnapshotFullRefresh(String pMessage) {
        MarketDataSnapshotFullRefresh marketData = null;
        try {
            // ObjectMapper mapper = new ObjectMapper();
            marketData = mapper.readValue(pMessage, MarketDataSnapshotFullRefresh.class);
            // System.out.println(marketData.toString());

            //Pretty print
            // System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marketData));

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return marketData;
    }
}
