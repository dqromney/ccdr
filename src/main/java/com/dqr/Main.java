package com.dqr;

import com.dqr.hitbtc.HitbtcDataReceiver;
import com.dqr.poloniex.PoloniexDataReceiver;
import lombok.extern.java.Log;

import java.io.IOException;

/**
 * Main driver.
 *
 * Created by dqromney on 10/21/17.
 */
@Log
public class Main {

    public static void main(String[] args) throws IOException {
        // BTC/USD
        PoloniexDataReceiver poloniexDr = new PoloniexDataReceiver();
        poloniexDr.init(args);
        poloniexDr.execute();

        // BTC/USD
        HitbtcDataReceiver hitbtcDr = new HitbtcDataReceiver();
        hitbtcDr.init(args);
        hitbtcDr.execute();

        // Everyting
        DataReceiver dr = new DataReceiver();
        dr.init(args);
        dr.execute();
    }
}
