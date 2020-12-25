package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
/*
* 用户实体类
* */

@Entity
@Table(name = "tb_sdk_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SDKUser implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String password;
    private String private_key;
    private String public_key;
    private String department;
    private Timestamp createtime;
    private Timestamp updatetime;
    private boolean valid;

    @Override
    public String toString() {
        return "SDKUser{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", private_key='" + private_key + '\'' +
                ", public_key='" + public_key + '\'' +
                ", department='" + department + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", valid=" + valid +
                '}';
    }
}
