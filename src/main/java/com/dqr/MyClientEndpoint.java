package com.dqr;

import com.dqr.dto.marketdata.MarketDataIncrementalRefresh;
import com.dqr.dto.marketdata.MarketDataSnapshotFullRefresh;
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
public class MyClientEndpoint {
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
        MarketDataIncrementalRefresh marketDataInc;
        MarketDataSnapshotFullRefresh marketDataFull;
        if (message.contains("MarketDataSnapshotFullRefresh"))  {
            marketDataFull = parseFull(message);
            System.out.println(marketDataFull.toString());
        } else {
            marketDataInc = parseInc(message);
            System.out.println(marketDataInc.toString());
        }
        //Pretty JSON print
        // System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(marketData));
        
        // DataReceiver.messageLatch.countDown();
    }

    @OnError
    public void processError(Throwable t) {
        t.printStackTrace();
    }

    private MarketDataIncrementalRefresh parseInc(String pMssage) {
        MarketDataIncrementalRefresh marketData = null;
        try {
            // ObjectMapper mapper = new ObjectMapper();
            marketData = mapper.readValue(pMssage, MarketDataIncrementalRefresh.class);
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

    private MarketDataSnapshotFullRefresh parseFull(String pMessage) {
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
