package com.deepblue.SDKServer.impl;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HopeResponse {

    /**
     * 是否成功
     */
    private boolean success;

    /**
     * 返回信息
     */
    private String message;

    /**
     * 业务相关的返回信息，私钥加密之后的
     */
    private String data;

}
