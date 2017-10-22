package com.dqr.poloniex;

import com.dqr.IDataReceiver;
import lombok.extern.java.Log;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.poloniex.service.PoloniexMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Timer;

/**
 * Created by dqromney on 10/21/17.
 */
@Log
public class PoloniexDataReceiver implements IDataReceiver {
    // TODO Configuration candidate
    final static Long FETCH_FREQUENCY = 1000L * 5L;

    private Exchange exchange;
    private MarketDataService marketDataService = null;
    // TODO Configuration candidate
    CurrencyPair currencyPair = null;

    @Override
    public void init(String[] args) throws IOException {
        exchange = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
        exchange.remoteInit();
        marketDataService = exchange.getMarketDataService();
        currencyPair = new CurrencyPair("BTC", "USDT");
    }

    @Override
    public void execute() {
        Timer timer = new Timer();
        TradeDataBag tradeDataBag = new TradeDataBag();
        TradeAdder tradeAdder = new TradeAdder(tradeDataBag);
        timer.schedule(new DataTask((PoloniexMarketDataServiceRaw) marketDataService, currencyPair, tradeDataBag), 0, FETCH_FREQUENCY);
    }
}
