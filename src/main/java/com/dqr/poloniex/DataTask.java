package com.dqr.poloniex;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.poloniex.dto.marketdata.PoloniexPublicTrade;
import org.knowm.xchange.poloniex.service.PoloniexMarketDataServiceRaw;

import java.io.IOException;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by dqromney on 10/21/17.
 */
public class DataTask extends TimerTask {

    static PoloniexMarketDataServiceRaw service;
    static CurrencyPair currencyPair;
    private TradeDataBag bag;

    public DataTask(PoloniexMarketDataServiceRaw service, CurrencyPair currencyPair, TradeDataBag tradeDataBag) {
        this.service = service;
        this.currencyPair = currencyPair;
        this.bag = tradeDataBag;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        long now = new Date().getTime() / 1000;
        try {
            PoloniexPublicTrade[] trades =
                    this.service.getPoloniexPublicTrades(currencyPair,
                            now - PoloniexDataReceiver.FETCH_FREQUENCY / 1000,
                            null);
            if (trades.length > 0) {
                for (PoloniexPublicTrade trade : trades) {
                    bag.add(trade);
                }
            } else {
                System.out.print('.');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
