package com.deepblue.SDKServer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
/*
* 用户实体类
* */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SDKUser{
    private Integer id;
    private String userName;
    private String password;
    private String private_key;
    private String public_key;
    private String department;
    private Timestamp createtime;
    private Timestamp updatetime;
    private boolean valid;

    public boolean getValid() {
        return valid;
    }
}
