package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_department",indexes = {
        @Index(name = "idx_datacenter_department_id", columnList = "departmentId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterDepartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String departmentId;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;
}
