package com.dqr;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.hitbtc.dto.marketdata.HitbtcTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Data Task.
 *
 * Created by dqromney on 10/12/17.
 */
class DataTask extends TimerTask {
    static MarketDataService service;
    static CurrencyPair currencyPair;
    static Long lastId = 0L;

    private TradeDataBag bag;

    public DataTask(MarketDataService service, CurrencyPair currencyPair, TradeDataBag tradeDataBag) {
        this.service = service;
        this.currencyPair = currencyPair;
        this.bag = tradeDataBag;
//        bag.addObserver(this);
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {
            Trades trades = this.service.getTrades(currencyPair, System.currentTimeMillis() - DataReceiver.FETCH_FREQUENCY,
                HitbtcTrades.HitbtcTradesSortField.SORT_BY_TIMESTAMP, HitbtcTrades.HitbtcTradesSortDirection.SORT_DESCENDING, 0L, 1000L);
            if (!trades.getTrades().isEmpty()) {
                System.out.println("\nTrades, last " + DataReceiver.FETCH_FREQUENCY / 1000L + " seconds, Size= " + trades.getTrades().size());
                for(Trade trade: trades.getTrades()) {
                    if (trades.getlastID() > lastId) {
                        System.out.println(trade.toString());
                        bag.add(trade);
                    } else {
                        System.err.println("Error: " + trade.toString());
                    }
                }
            } else {
                System.out.print('.');
            }
            lastId = trades.getlastID();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
