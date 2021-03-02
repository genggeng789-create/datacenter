package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_user",indexes = {
        @Index(name = "idx_datacenter_user_id", columnList = "user_id")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String user_id;
    private String password;
    private String username;
    private String email;
    private String telephone;
    private String department;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "DatacenterUser{" +
                "id=" + id +
                ", user_id='" + user_id + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", department='" + department + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
