package com.deepblue.SDKServer.service;

import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.User;
import com.deepblue.SDKServer.sys.SysUserDTO;

import java.util.List;

public interface SysUserService {
    BaseResponse<PageModel<List<User>>> queryUserList(SysUserDTO dto);
}
