package com.dqr.poloniex;

import org.knowm.xchange.poloniex.dto.marketdata.PoloniexPublicTrade;

import java.util.Iterator;

public class TradeAdder implements Observer {

    private TradeDataBag bag;

    public TradeAdder(TradeDataBag bag) {
        this.bag = bag;
        bag.addObserver(this);
    }

    @Override
    public void update(TradeDataBag o) {
        if (o == bag) {
//            System.out.println("The contents of the TradeDataBag have changed.");

//            SortedSet<String> sortedKeys =  bag.sortKeyByTradeId();
//            sortedKeys.forEach(k -> System.out.println(k));

            int counter = 0;
            Iterator i = bag.iterator();
            while (i.hasNext()) {
                PoloniexPublicTrade trade = (PoloniexPublicTrade) i.next();
                System.out.println("IObserver: " + trade.toString());
                counter ++;
            }
            System.out.println("Total trades received: : " + counter);
        }
    }
}
