package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_user_role_relation",indexes = {
        @Index(name = "idx_datacenter_user_role_relation_id", columnList = "projectId,roleId,userId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterUserRoleRelation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String projectId;
    private String roleId;
    private String userId;
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
                ", userId='" + userId + '\'' +
                ", createId='" + creatorId + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
