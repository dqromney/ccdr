package com.dqr.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

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