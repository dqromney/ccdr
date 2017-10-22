package com.dqr.hitbtc;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Trade;
import org.knowm.xchange.dto.marketdata.Trades;
import org.knowm.xchange.hitbtc.dto.marketdata.HitbtcTrades;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.TimerTask;

/**
 * Created by dqromney on 10/21/17.
 */
public class DataTask extends TimerTask {

    static MarketDataService service;
    static CurrencyPair currencyPair;
    private TradeDataBag bag;

    public DataTask(MarketDataService service, CurrencyPair currencyPair, TradeDataBag tradeDataBag) {
        this.service = service;
        this.currencyPair = currencyPair;
        this.bag = tradeDataBag;
    }

    /**
     * The action to be performed by this timer task.
     */
    @Override
    public void run() {
        try {
            Trades trades = this.service.getTrades(currencyPair, System.currentTimeMillis() - HitbtcDataReceiver.FETCH_FREQUENCY,
                    HitbtcTrades.HitbtcTradesSortField.SORT_BY_TIMESTAMP, HitbtcTrades.HitbtcTradesSortDirection.SORT_DESCENDING, 0L, 1000L);
            if (!trades.getTrades().isEmpty()) {
                Long tradesLastID = Long.valueOf(trades.getTrades().get(trades.getTrades().size() - 1).getId());
                System.out.println("\nTrades, last " + HitbtcDataReceiver.FETCH_FREQUENCY / 1000L + " seconds, Size= " + trades.getTrades().size());
                for (Trade trade : trades.getTrades()) {
                    if (tradesLastID >= Long.valueOf(trade.getId())) {
                        bag.add(trade);
                    } else {
                        System.err.println("Error: " + trade.toString());
                    }
                }
            } else {
                System.out.print('.');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
