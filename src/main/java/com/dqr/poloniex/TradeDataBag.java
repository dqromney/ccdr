package com.dqr.poloniex;

import org.knowm.xchange.poloniex.dto.marketdata.PoloniexPublicTrade;

import java.util.*;

/**
 * Poloniex Trade Bag.
 *
 * Created by dqromney on 10/19/17.
 */
public class TradeDataBag {
    private HashMap<String, PoloniexPublicTrade> map = new HashMap();
    private ArrayList observers = new ArrayList();

    public void add(PoloniexPublicTrade t) {
        if (!map.containsKey(t.getTradeID())) {
            map.put(t.getTradeID(), t);
            notifyObservers();
        } else {
            System.err.println("Duplicate: " + t.toString());
        }
    }

    public Iterator iterator() {
        return map.values().iterator();
    }

    public PoloniexPublicTrade remove(PoloniexPublicTrade trade) {
        if (map.containsKey(trade.getTradeID())) {
            map.remove(trade.getTradeID());
            return trade;
        }
        return null;
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        // loop through and notify each observer
        Iterator i = observers.iterator();
        while (i.hasNext()) {
            Observer o = (Observer) i.next();
            o.update(this);
        }
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public SortedSet<String> sortKeyByTradeId() {
        SortedSet<String> keys = new TreeSet<String>(map.keySet());
        return keys;
    }

}
