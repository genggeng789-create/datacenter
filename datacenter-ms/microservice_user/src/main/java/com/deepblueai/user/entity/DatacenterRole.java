package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_role",indexes = {
        @Index(name = "idx_datacenter_role_id", columnList = "projectId, roleId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterRole implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String projectId;
    private String roleId;
    private String roleDescription;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "DatacenterRole{" +
                "id=" + id +
                ", projectId='" + projectId + '\'' +
                ", roleId='" + roleId + '\'' +
                ", roleDescription='" + roleDescription + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
