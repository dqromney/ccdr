package com.dqr;

import org.knowm.xchange.dto.marketdata.Trade;

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
            System.out.println("The contents of the TradeDataBag have changed.");
            int counter = 0;
            Iterator i = bag.iterator();
            while (i.hasNext()) {
                Trade trade = (Trade) i.next();
                System.out.println("Observer: " + trade.toString());
                counter += 1;
            }
            System.out.println("Total trades received: : " + counter);
        }
    }
}
