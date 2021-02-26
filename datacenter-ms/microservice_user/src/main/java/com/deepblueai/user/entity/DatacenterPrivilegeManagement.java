package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_privilege_manage",indexes = {
        @Index(name = "idx_datacenter_privilege_id", columnList = "privilegeId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterPrivilegeManagement implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String privilegeName;
    private String privilegeId;
    private String privilegeDescription;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "DatacenterPrivilegeManagement{" +
                "id=" + id +
                ", privilegeName='" + privilegeName + '\'' +
                ", privilegeId='" + privilegeId + '\'' +
                ", privilegeDescription='" + privilegeDescription + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
