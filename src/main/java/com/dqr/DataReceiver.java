package com.dqr;

import com.dqr.poloniex.TradeDataBag;
import com.dqr.poloniex.TradeAdder;
import lombok.extern.java.Log;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.ExchangeFactory;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.poloniex.PoloniexExchange;
import org.knowm.xchange.poloniex.service.PoloniexMarketDataServiceRaw;
import org.knowm.xchange.service.marketdata.MarketDataService;

import java.io.IOException;
import java.util.Timer;
import java.util.concurrent.CountDownLatch;


/**
 * Crypto Currency Data Receiver (HitBtc)
 */
@Log
public class DataReceiver {

    final static CountDownLatch messageLatch = new CountDownLatch(1);
    final static Long FETCH_FREQUENCY = 1000L * 5L;

    private Exchange hitbtcExchange;
    private Exchange poloniex;
    private MarketDataService marketDataService;
    private CurrencyPair currencyPair;

    public static void main(String[] args) throws IOException {
        DataReceiver dr = new DataReceiver();
        MarketDataService dataService = dr.init(args);
        dr.execute((PoloniexMarketDataServiceRaw) dataService);
    }

    private MarketDataService init(String[] args) throws IOException {
        log.info("init()...");
        poloniex = ExchangeFactory.INSTANCE.createExchange(PoloniexExchange.class.getName());
        poloniex.remoteInit();
        MarketDataService dataService = poloniex.getMarketDataService();
        return dataService;

//        hitbtcExchange =  HitbtcUtils.createExchange();
//        hitbtcExchange.remoteInit();
//        MarketDataService dataService = hitbtcExchange.getMarketDataService();
//        return dataService;
    }

    //    private void execute(MarketDataService dataService) throws IOException {
    private void execute(PoloniexMarketDataServiceRaw dataService) throws IOException {
        log.info("execute()...");

        Timer timer = new Timer();
        TradeDataBag tradeDataBag = new TradeDataBag();
        TradeAdder tradeAdder = new TradeAdder(tradeDataBag);
        // currencyPair = new CurrencyPair("BTC", "USDT");
        // [STEEM/BTC, GNT/ETH, BELA/BTC, RADS/BTC, PASC/BTC, XBC/BTC, GNO/ETH, XRP/BTC, NXT/USDT, GAME/BTC, PINK/BTC, ARDR/BTC, STEEM/ETH, EMC2/BTC, NOTE/BTC, STR/BTC, ZEC/XMR, DASH/USDT, BCN/BTC, SYS/BTC, LTC/USDT, SJCX/BTC, AMP/BTC, LSK/BTC, BTC/USDT, DGB/BTC, BTM/BTC, LTC/BTC, GRC/BTC, BCY/BTC, NXC/BTC, FCT/BTC, DASH/BTC, POT/BTC, ETC/ETH, OMNI/BTC, DCR/BTC, NAUT/BTC, MAID/BTC, NXT/XMR, GNT/BTC, NXT/BTC, GNO/BTC, FLO/BTC, XCP/BTC, VTC/BTC, ZEC/ETH, PPC/BTC, XRP/USDT, BLK/BTC, BTS/BTC, STR/USDT, DOGE/BTC, REP/ETH, LTC/XMR, SC/BTC, MAID/XMR, BTCD/BTC, HUC/BTC, BLK/XMR, XPM/BTC, SBD/BTC, RIC/BTC, XMR/USDT, BCN/XMR, REP/USDT, ETH/USDT, NMC/BTC, LSK/ETH, ZEC/USDT, FLDC/BTC, BTCD/XMR, BURST/BTC, ETC/USDT, XMR/BTC, EXP/BTC, REP/BTC, NEOS/BTC, ETH/BTC, ZEC/BTC, ETC/BTC, STRAT/BTC, CLAM/BTC, VIA/BTC, LBC/BTC, VRC/BTC, XVC/BTC, NAV/BTC, DASH/XMR, XEM/BTC, GAS/BTC, BCH/USDT, GAS/ETH, OMG/BTC, BCH/BTC, ZRX/BTC, CVC/BTC, ZRX/ETH, BCH/ETH, OMG/ETH, CVC/ETH]
        currencyPair = new CurrencyPair("STR", "BTC");

//        System.out.println(dataService.getPoloniexCurrencyInfo());
//        System.out.println(poloniex.getExchangeSymbols());
//        System.out.println(dataService.getAllPoloniexTickers());
//        System.out.println(dataService.getPoloniexTicker(currencyPair));
//        System.out.println(dataService.getAllPoloniexDepths());
//        System.out.println(dataService.getAllPoloniexDepths(3));
//        System.out.println(dataService.getPoloniexDepth(currencyPair));
//        System.out.println(dataService.getPoloniexDepth(currencyPair, 3));

        // timer.schedule(new DataTask(dataService, CurrencyPair.BTC_USD, tradeDataBag), 0, FETCH_FREQUENCY);
        System.out.println("Currency: " + currencyPair.toString());
        timer.schedule(new DataTask(dataService, currencyPair, tradeDataBag), 0, FETCH_FREQUENCY);

//        try {
//            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
//            // String uri = "ws://echo.websocket.org:80/";
//            // String uri = "ws://demo-api.hitbtc.com:80/";
//            String uri = "ws://api.hitbtc.com:80";
//            System.out.println("Connecting to " + uri);
//            container.connectToServer(MyClientEndpoint.class, URI.create(uri));
//            messageLatch.await(100, TimeUnit.SECONDS);
//        } catch (DeploymentException | InterruptedException | IOException ex) {
//            log.log(Level.SEVERE, null, ex);
//        }
    }

}
