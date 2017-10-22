package com.dqr.hitbtc;

import org.knowm.xchange.dto.marketdata.Trade;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Poloniex Trade Bag.
 * <p>
 * Created by dqromney on 10/19/17.
 */
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

    public void removeObserver(com.dqr.hitbtc.Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        // loop through and notify each observer
        Iterator i = observers.iterator();
        while (i.hasNext()) {
            com.dqr.hitbtc.Observer o = (com.dqr.hitbtc.Observer) i.next();
            o.update(this);
        }
    }

    public void addObserver(com.dqr.hitbtc.Observer o) {
        observers.add(o);
    }}
