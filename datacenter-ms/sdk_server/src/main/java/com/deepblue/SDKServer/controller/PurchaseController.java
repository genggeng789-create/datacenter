package com.deepblue.SDKServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.api.ApiAbstract;
import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.ConsumerPurchaseRates;
import com.deepblue.SDKServer.entity.DashDeviceStatus;
import com.deepblue.SDKServer.impl.HopeRequest;
import com.deepblue.SDKServer.impl.HopeResponse;
import com.deepblue.SDKServer.service.DeviceStatistic;
import com.deepblue.SDKServer.service.MerchantStatistic;
import com.deepblue.SDKServer.sys.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api")
public class PurchaseController extends ApiAbstract<BaseResponse<PageModel<List<ConsumerPurchaseRates>>>> {
    @Autowired
    MerchantStatistic merchantStatistic;

    @Override
    public BaseResponse<PageModel<List<ConsumerPurchaseRates>>> work(JSONObject o) {
        return merchantStatistic.queryList(JSONObject.toJavaObject(o, SysUserDTO.class));
    }

    @PostMapping("/queryPurchaseList")
    public HopeResponse queryDeviceList(@RequestBody HopeRequest hopeRequest) {
        return process(hopeRequest);
    }
}
