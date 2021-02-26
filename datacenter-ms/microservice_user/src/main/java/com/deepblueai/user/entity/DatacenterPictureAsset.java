package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_picture_asset",indexes = {
        @Index(name = "idx_datacenter_asset_department_project_id", columnList = "departmentId,projectId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterPictureAsset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departmentId;
    private String projectId;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;
}
