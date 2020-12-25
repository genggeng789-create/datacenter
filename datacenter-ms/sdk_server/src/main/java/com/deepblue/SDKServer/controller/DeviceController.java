package com.deepblue.SDKServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.api.ApiAbstract;
import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.DashDeviceStatus;
import com.deepblue.SDKServer.entity.User;
import com.deepblue.SDKServer.impl.HopeRequest;
import com.deepblue.SDKServer.impl.HopeResponse;
import com.deepblue.SDKServer.service.DeviceStatistic;
import com.deepblue.SDKServer.service.QueryService;
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
public class DeviceController extends ApiAbstract<BaseResponse<PageModel<List<DashDeviceStatus>>>> {
    @Autowired
    DeviceStatistic deviceStatistic;

    @Override
    public BaseResponse<PageModel<List<DashDeviceStatus>>> work(JSONObject o) {
        return deviceStatistic.queryList(JSONObject.toJavaObject(o, SysUserDTO.class));
    }

    @PostMapping("/queryDeviceList")
    public HopeResponse queryDeviceList(@RequestBody HopeRequest hopeRequest) {
        return process(hopeRequest);
    }
}
