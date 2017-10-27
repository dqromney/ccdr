package com.dqr;

import com.dqr.hitbtc.HitbtcFeedDataReceiver;
import com.dqr.hitbtc.HitbtcDataReceiver;
import com.dqr.poloniex.PoloniexDataReceiver;
import com.dqr.poloniex.PoloniexFeedDataReceiver;
import lombok.extern.java.Log;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Main driver.
 * <p>
 * Created by dqromney on 10/21/17.
 */
@Log
public class Main {

    private static JSONObject jObject = null;

    public static void main(String[] args) throws IOException {

        // BTC/USD
        if (false) {
            PoloniexDataReceiver poloniexDr = new PoloniexDataReceiver();
            poloniexDr.init(args);
            poloniexDr.execute();

            // BTC/USD
            HitbtcDataReceiver hitbtcDr = new HitbtcDataReceiver();
            hitbtcDr.init(args);
            hitbtcDr.execute();

            // HitBTC Data feed: All currencies
            HitbtcFeedDataReceiver dr = new HitbtcFeedDataReceiver();
            dr.init(args);
            dr.execute();
        }
        // Poloniex Data feed: All currencies
        PoloniexFeedDataReceiver pDr = new PoloniexFeedDataReceiver();
        pDr.init(args);
        pDr.execute();
    }
}
