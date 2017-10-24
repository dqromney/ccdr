package com.dqr;

import com.dqr.hitbtc.HitbtcDataReceiver;
import com.dqr.poloniex.PoloniexDataReceiver;
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
        }

        // HitBTC Data feed: All currencies
        DataReceiver dr = new DataReceiver();
        dr.init(args);
        dr.execute();
    }
}
