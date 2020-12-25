package com.deepblue.SDKServer.controller;

import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.api.ApiAbstract;
import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.User;
import com.deepblue.SDKServer.impl.HopeRequest;
import com.deepblue.SDKServer.impl.HopeResponse;
import com.deepblue.SDKServer.service.SysUserService;
import com.deepblue.SDKServer.sys.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class APIController extends ApiAbstract<BaseResponse<PageModel<List<User>>>> {
    @Resource
    private SysUserService sysUserService;

    @Override
    public BaseResponse<PageModel<List<User>>> work(JSONObject o) {
        return sysUserService.queryUserList(JSONObject.toJavaObject(o, SysUserDTO.class));
    }

    @PostMapping("/queryUserList")
    public HopeResponse queryUserList(@RequestBody HopeRequest hopeRequest) {
        return process(hopeRequest);
    }
}
