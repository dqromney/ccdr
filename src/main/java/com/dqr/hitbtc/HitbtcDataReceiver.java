package com.dqr.hitbtc;

import com.dqr.IDataReceiver;
import lombok.extern.java.Log;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Timer;

/**
 * Hit BTC Exchange Data Receiver.
 *
 * Created by dqromney on 10/21/17.
 */
@Log
public class HitbtcDataReceiver implements IDataReceiver {
    // TODO Configuration candidate
    final static Long FETCH_FREQUENCY = 1000L * 5L;

    private Exchange exchange;
    private MarketDataService marketDataService = null;
    // TODO Configuration candidate
    CurrencyPair currencyPair = null;

    @Override
    public void init(String[] args) throws IOException {
        exchange =  HitbtcUtils.createExchange();
        exchange.remoteInit();
        marketDataService = exchange.getMarketDataService();
        currencyPair = CurrencyPair.BTC_USD;
    }

    @Override
    public void execute() {
        Timer timer = new Timer();
        TradeDataBag tradeDataBag = new TradeDataBag();
        TradeAdder tradeAdder = new TradeAdder(tradeDataBag);
        timer.schedule(new DataTask(marketDataService, CurrencyPair.BTC_USD, tradeDataBag), 0, FETCH_FREQUENCY);
    }
}
