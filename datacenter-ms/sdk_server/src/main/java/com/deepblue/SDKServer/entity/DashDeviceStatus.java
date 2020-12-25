package com.deepblue.SDKServer.entity;

import lombok.Data;

@Data
public class DashDeviceStatus {
    String deviceCode;
    String problemStatus1;
    String problemStatus2;
    String problemStatus3;

    public String toJson() {
        return "{" +
                "deviceCode:\"" + deviceCode + '\"' +
                ", problemStatus1:\"" + problemStatus1 + '\"' +
                ", problemStatus2:\"" + problemStatus2 + '\"' +
                ", problemStatus3:\"" + problemStatus3 + '\"' +
                '}';
    }

}
