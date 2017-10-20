package com.dqr;

import org.knowm.xchange.dto.marketdata.Trade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class TradeDataBag {
    private HashSet set = new HashSet();
    private ArrayList list = new ArrayList();
    private ArrayList observers = new ArrayList();

    public void add(Trade t) {
        set.add(t);
        list.add(t);
        notifyObservers();
    }

    public Iterator iterator() {
        return set.iterator();
        // return list.iterator();
    }

    public Trade remove(Trade trade) {
        set.remove(trade);
        return trade;
    }

    public Trade remove(int index) {
        if (index < list.size()) {
            Trade t = (Trade) list.remove(index);
            notifyObservers();
            return t;
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
}
