package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_role_privilege_relation",indexes = {
        @Index(name = "idx_datacenter_role_privilege_relation_id", columnList = "projectId,roleId,privilegeId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterRolePrivilegeRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String projectId;
    private String roleId;
    private String privilegeId;
    private String creatorId;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "DatacenterUserRoleRelation{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", userId='" + privilegeId + '\'' +
                ", createId='" + creatorId + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
