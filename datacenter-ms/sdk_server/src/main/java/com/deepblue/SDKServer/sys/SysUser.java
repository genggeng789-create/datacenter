package com.deepblue.SDKServer.sys;

import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.User;
import com.deepblue.SDKServer.service.SysUserService;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@Data
@Builder
public class SysUser implements SysUserService {

    @Override
    public BaseResponse<PageModel<List<User>>> queryUserList(SysUserDTO dto) {
        User user = User.builder().userEmail("tuomingyao@163.com").userId("00001").userName("peter").userPhone("13564136028").build();
        List<User> list = new ArrayList<User>();
        list.add(user);
        user = User.builder().userEmail("tuomingyao@163.com").userId("00001").userName("peter").userPhone("13564136028").build();
        list.add(user);
        PageModel<List<User>> pageModel = PageModel.<List<User>>builder().data(list).pageIndex(dto.getPageIndex()).pageSize(list.size()).build();
        return new BaseResponse<PageModel<List<User>>>(pageModel,0,"OK");
    }
}
