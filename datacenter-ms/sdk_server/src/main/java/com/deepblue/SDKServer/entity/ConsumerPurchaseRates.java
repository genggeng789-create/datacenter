package com.deepblue.SDKServer.entity;

import lombok.Data;

@Data
public class ConsumerPurchaseRates {
    String registerTime;
    String merchantId;
    double rateOf1stWithin0;
    double rateOf1st;
    double rateOf2ndWithin0;
    double rateOf2ndWithin7;
    double rateOf2ndWithin14;
    double rateOf2ndwithin21;
    double rateOf2nd;
    int registerCount;
    int ifFirst;
    int ifSecond;

    String startDate;
    String endDate;
    String keyType;

    public String toJson() {
        return "{" +
                "register_time:\"" + registerTime +"\"" +
                ", merchant_id:\"" + merchantId + "\"" +
                ", rate_of_1st_within0:" + rateOf1stWithin0 +
                ", rate_of_1st:" + rateOf1st +
                ", rate_of_2nd_within_0:" + rateOf2ndWithin0 +
                ", rate_of_2nd_within_7:" + rateOf2ndWithin7 +
                ", rate_of_2nd_within_14:" + rateOf2ndWithin14 +
                ", rate_of_2nd_within_21:" + rateOf2ndwithin21 +
                ", rate_of_2nd:" + rateOf2nd +
                ", register_count:" + registerCount +
                ", if_first:" + ifFirst +
                ", if_second:" + ifSecond +
                '}';
    }
}
