package com.dqr.poloniex.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * New Trade DTO.
 * 
 * Ex. Data:
 * {"type":"newTrade","data":{"amount":"0.03591039","date":"2017-10-14 11:33:51","rate":"5737.80305380","total":"206.04674540","tradeID":"9368327","type":"buy"}}
 *
 * Created by dqromney on 10/27/17.
 */
public final class NewTrade {
    public final String type;
    public final Data data;

    @JsonCreator
    public NewTrade(@JsonProperty("type") String type, @JsonProperty("data") Data data){
        this.type = type;
        this.data = data;
    }

    public static final class Data {
        public final String amount;
        public final String date;
        public final String rate;
        public final String total;
        public final String tradeID;
        public final String type;

        @JsonCreator
        public Data(@JsonProperty("amount") String amount, @JsonProperty("date") String date, @JsonProperty("rate") String rate, @JsonProperty("total") String total, @JsonProperty("tradeID") String tradeID, @JsonProperty("type") String type){
            this.amount = amount;
            this.date = date;
            this.rate = rate;
            this.total = total;
            this.tradeID = tradeID;
            this.type = type;
        }
    }
}
