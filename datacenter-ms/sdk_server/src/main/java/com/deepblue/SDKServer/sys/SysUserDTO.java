package com.deepblue.SDKServer.sys;

import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.common.PageBase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SysUserDTO extends PageBase {

    /**
     * 昵称
     */
    private JSONObject queryCondition;
}
