package com.dqr.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

/**
 * Market Data Incremental Refresh DTO.
 * Ex.
 * {"MarketDataIncrementalRefreshV1":{"seqNo":8394743,"symbol":"BCCETH","exchangeStatus":"working","ask":[{"price":"1.167047","size":1},{"price":"1.167149","size":0},{"price":"1.178648","size":0},{"price":"1.178772","size":460},{"price":"1.187218","size":490},{"price":"1.187757","size":0},{"price":"1.197839","size":1500},{"price":"1.213769","size":0},{"price":"1.224863","size":1020},{"price":"1.227981","size":0},{"price":"1.254917","size":13040},{"price":"1.255336","size":0}],"bid":[{"price":"1.113452","size":1},{"price":"1.113350","size":0},{"price":"1.101929","size":470},{"price":"1.101850","size":0},{"price":"1.096193","size":400},{"price":"1.096100","size":0},{"price":"1.084628","size":1530},{"price":"1.084588","size":0},{"price":"1.073044","size":0},{"price":"1.072655","size":3562},{"price":"1.060332","size":0},{"price":"1.054030","size":14040},{"price":"0.425910","size":0},{"price":"0.424520","size":3000}],"trade":[{"price":"1.141820","size":65,"tradeId":56232922,"timestamp":1508647477727,"side":"buy"}],"timestamp":1508647477086}}
 * {"MarketDataIncrementalRefreshV1":{"seqNo":10116322,"symbol":"DASHBTC","exchangeStatus":"working","ask":[{"price":"0.049565","size":0},{"price":"0.049752","size":4910},{"price":"0.049776","size":0},{"price":"0.049858","size":4240},{"price":"0.049860","size":0},{"price":"0.049938","size":24240},{"price":"0.049950","size":0},{"price":"0.050054","size":17140},{"price":"0.050056","size":0},{"price":"0.050159","size":54240},{"price":"0.050161","size":0},{"price":"0.050221","size":234658},{"price":"0.050235","size":0},{"price":"0.050892","size":84631},{"price":"0.051002","size":0}],"bid":[{"price":"0.049237","size":230},{"price":"0.049236","size":0},{"price":"0.049063","size":410},{"price":"0.049062","size":0},{"price":"0.048877","size":4040},{"price":"0.048876","size":2350},{"price":"0.048875","size":0},{"price":"0.048868","size":21100},{"price":"0.048866","size":0},{"price":"0.048853","size":15350},{"price":"0.048851","size":0},{"price":"0.048843","size":15350},{"price":"0.048841","size":0},{"price":"0.048837","size":21350},{"price":"0.048835","size":0},{"price":"0.048833","size":15350},{"price":"0.048831","size":0},{"price":"0.048826","size":25350},{"price":"0.048824","size":0},{"price":"0.048823","size":15239},{"price":"0.048822","size":31410},{"price":"0.048815","size":55350},{"price":"0.048813","size":0},{"price":"0.048811","size":35239},{"price":"0.048809","size":0},{"price":"0.048805","size":45239},{"price":"0.048803","size":0},{"price":"0.048800","size":65239},{"price":"0.048798","size":0},{"price":"0.048795","size":52350},{"price":"0.048793","size":0},{"price":"0.048790","size":62351},{"price":"0.048788","size":0},{"price":"0.048786","size":32150},{"price":"0.048784","size":0},{"price":"0.048780","size":62350},{"price":"0.048778","size":0},{"price":"0.048775","size":260329},{"price":"0.048773","size":262451},{"price":"0.048769","size":232451},{"price":"0.048555","size":0}],"trade":[],"timestamp":1508809078475}}
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public final class MarketDataIncrementalRefresh {
    public final MarketDataIncRefresh marketDataIncRefresh;

    @JsonCreator
    public MarketDataIncrementalRefresh(@JsonProperty("MarketDataIncrementalRefresh") MarketDataIncRefresh marketDataIncRefresh){
        this.marketDataIncRefresh = marketDataIncRefresh;
    }

    public static final class MarketDataIncRefresh {
        public final long seqNo;
        public final String symbol;
        public final String exchangeStatus;
        public final Ask ask[];
        public final Bid bid[];
        public final Trade trade[];
        public final long timestamp;

        @JsonCreator
        public MarketDataIncRefresh(@JsonProperty("seqNo") long seqNo, @JsonProperty("symbol") String symbol, @JsonProperty("exchangeStatus") String exchangeStatus, @JsonProperty("ask") Ask[] ask, @JsonProperty("bid") Bid[] bid, @JsonProperty("trade") Trade[] trade, @JsonProperty("timestamp") long timestamp){
            this.seqNo = seqNo;
            this.symbol = symbol;
            this.exchangeStatus = exchangeStatus;
            this.ask = ask;
            this.bid = bid;
            this.trade = trade;
            this.timestamp = timestamp;
        }

        public static final class Ask {
            public final String price;
            public final long size;

            @JsonCreator
            public Ask(@JsonProperty("price") String price, @JsonProperty("size") long size){
                this.price = price;
                this.size = size;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Ask{");
                sb.append("price='").append(price).append('\'');
                sb.append(", size=").append(size);
                sb.append('}');
                return sb.toString();
            }
        }

        public static final class Bid {
            public final String price;
            public final long size;

            @JsonCreator
            public Bid(@JsonProperty("price") String price, @JsonProperty("size") long size){
                this.price = price;
                this.size = size;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Bid{");
                sb.append("price='").append(price).append('\'');
                sb.append(", size=").append(size);
                sb.append('}');
                return sb.toString();
            }
        }

        public static final class Trade {
            public final String price;
            public final long size;
            public final long tradeId;
            public final long timestamp;
            public final String side;

            @JsonCreator
            public Trade(@JsonProperty("price") String price, @JsonProperty("size") long size, @JsonProperty("tradeId") long tradeId, @JsonProperty("timestamp") long timestamp, @JsonProperty("side") String side){
                this.price = price;
                this.size = size;
                this.tradeId = tradeId;
                this.timestamp = timestamp;
                this.side = side;
            }

            @Override
            public String toString() {
                final StringBuilder sb = new StringBuilder("Trade{");
                sb.append("price='").append(price).append('\'');
                sb.append(", size=").append(size);
                sb.append(", tradeId=").append(tradeId);
                sb.append(", timestamp=").append(timestamp);
                sb.append(", side='").append(side).append('\'');
                sb.append('}');
                return sb.toString();
            }
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("MarketDataIncRefresh{");
            sb.append("seqNo=").append(seqNo);
            sb.append(", symbol='").append(symbol).append('\'');
            sb.append(", exchangeStatus='").append(exchangeStatus).append('\'');
            sb.append(", ask=").append(Arrays.toString(ask));
            sb.append(", bid=").append(Arrays.toString(bid));
            sb.append(", trade=").append(Arrays.toString(trade));
            sb.append(", timestamp=").append(timestamp);
            sb.append('}');
            return sb.toString();
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MarketDataIncrementalRefresh{");
        sb.append("marketDataIncRefresh=").append(marketDataIncRefresh);
        sb.append('}');
        return sb.toString();
    }
}