package com.dqr.poloniex.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Order Book Remove DTO.
 *
 * Ex. Data:
 * {"type":"orderBookRemove","data":{"type":"ask","rate":"5749.84349317"}}
 *
 * Created by dqromney on 10/27/17.
 */
public final class OrderBookRemove {
    public final String type;
    public final Data data;

    @JsonCreator
    public OrderBookRemove(@JsonProperty("type") String type, @JsonProperty("data") Data data){
        this.type = type;
        this.data = data;
    }

    public static final class Data {
        public final String type;
        public final String rate;

        @JsonCreator
        public Data(@JsonProperty("type") String type, @JsonProperty("rate") String rate){
            this.type = type;
            this.rate = rate;
        }
    }
}