package com.deepblue.SDKServer.service;

import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.sys.SysUserDTO;

import java.util.List;

public interface QueryService<T> {
    BaseResponse<PageModel<List<T>>> queryList(SysUserDTO dto);
}
