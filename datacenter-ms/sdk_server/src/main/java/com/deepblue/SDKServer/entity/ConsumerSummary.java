package com.deepblue.SDKServer.entity;

import lombok.Data;

@Data
public class ConsumerSummary {
    String registerTime;
    int register_count;
    int if_first;
    int if_second;
    double  rate_of_1st;
    double  rate_of_2nd;
    double  first_within_0;
    double  rate_of_2nd_within_0;
    double  rate_of_2nd_within_7;
    double  rate_of_2nd_within_14;
    double  rate_of_2nd_within_21;
    int registerCount;
    int ifFirst;
    int ifSecond;

    String startDate;
    String endDate;
    String keyType;
}
