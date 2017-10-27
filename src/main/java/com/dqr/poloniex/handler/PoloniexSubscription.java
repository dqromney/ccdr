package com.dqr.poloniex.handler;

import lombok.extern.java.Log;
//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import rx.functions.Action1;
import ws.wamp.jawampa.PubSubData;

import java.util.function.Supplier;

/**
 *
 * @author David
 */
@Log
public class PoloniexSubscription implements Action1<PubSubData>
{
    public static final PoloniexSubscription TICKER = new PoloniexSubscription("ticker");

    // protected final static Logger LOG = LogManager.getLogger();
    public final String feedName;

    public PoloniexSubscription(String feedName)
    {
        this.feedName = feedName;
    }

    @Override
    public void call(PubSubData event)
    {
        try
        {
            log.fine((Supplier<String>) event.arguments());
        }
        catch (Exception ex)
        {
            log.warning("Exception processing event data - " + ex.getMessage());
        }
    }
}