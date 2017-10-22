//package com.dqr;
//
//import com.dqr.poloniex.TradeDataBag;
//import org.knowm.xchange.currency.CurrencyPair;
//import org.knowm.xchange.dto.marketdata.Trade;
//import org.knowm.xchange.poloniex.dto.marketdata.PoloniexPublicTrade;
//import org.knowm.xchange.poloniex.service.PoloniexMarketDataServiceRaw;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.TimerTask;
//
///**
// * Data Task.
// * <p>
// * Created by dqromney on 10/12/17.
// */
//class DataTask extends TimerTask {
//    // static MarketDataService service;
//    static PoloniexMarketDataServiceRaw service;
//    static CurrencyPair currencyPair;
//    static Long lastId = 0L;
//
//    private TradeDataBag bag;
//
//    public DataTask(PoloniexMarketDataServiceRaw service, CurrencyPair currencyPair, TradeDataBag tradeDataBag) {
//        this.service = service;
//        this.currencyPair = currencyPair;
//        this.bag = tradeDataBag;
//    }
//
//    /**
//     * The action to be performed by this timer task.
//     */
//    @Override
//    public void run() {
//        try {
////            Trades trades = this.service.getTrades(currencyPair, System.currentTimeMillis() - PoloniexDataReceiver.FETCH_FREQUENCY,
////                HitbtcTrades.HitbtcTradesSortField.SORT_BY_TIMESTAMP, HitbtcTrades.HitbtcTradesSortDirection.SORT_DESCENDING, 0L, 1000L);
//            long now = new Date().getTime() / 1000;
//            // PoloniexPublicTrade[] trades = this.service.getPoloniexPublicTrades(currencyPair, System.currentTimeMillis() - PoloniexDataReceiver.FETCH_FREQUENCY, null);
//            PoloniexPublicTrade[] trades = this.service.getPoloniexPublicTrades(currencyPair, now - PoloniexDataReceiver.FETCH_FREQUENCY/1000, null);
//            int length = trades.length;
//            if (length > 0) {
////                Long tradesLastID = Long.valueOf(trades[length-1].getTradeID());
//                System.out.println("\nTrades, Size= " + trades.length);
//                for (PoloniexPublicTrade trade : trades) {
////                    if(tradesLastID >= Long.valueOf(trade.getTradeID())) {
//                        System.out.println(trade.toString());
//                        bag.add(trade);
////                    } else {
////                        System.err.println("Error: " + trade.toString());
////                    }
//                }
//            } else {
//                System.out.print('.');
//            }
////            if (!trades.getTrades().isEmpty()) {
////                Long tradesLastID = Long.valueOf(trades.getTrades().get(trades.getTrades().size()-1).getId());
////                System.out.println("\nTrades, last " + PoloniexDataReceiver.FETCH_FREQUENCY / 1000L + " seconds, Size= " + trades.getTrades().size());
////                for(Trade trade: trades.getTrades()) {
////                    if (tradesLastID >= Long.valueOf(trade.getId())) {
////                    // if (Long.valueOf(trade.getId()) <= lastId) {
////                        System.out.println(trade.toString());
////                        bag.add(trade);
////                    } else {
////                        System.err.println("Error: " + trade.toString());
////                    }
//////                    lastId = Long.valueOf(trades.getTrades().get(trades.getTrades().size()-1).getId());
////                }
////            } else {
////                System.out.print('.');
////            }
////            lastId = trades.getlastID();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
