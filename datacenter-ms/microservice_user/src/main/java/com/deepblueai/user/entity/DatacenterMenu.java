package com.deepblueai.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "datacenter_menu",indexes = {
        @Index(name = "idx_datacenter_menu_id", columnList = "linkUrl, menuId")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatacenterMenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String superiorId;
    private String menuId;
    private String menuDescription;
    private Integer type;
    private String linkUrl;
    private Boolean validate;
    private Timestamp updatetime;
    private Timestamp createtime;

    @Override
    public String toString() {
        return "DatacenterMenu{" +
                "id=" + id +
                ", superiorId='" + superiorId + '\'' +
                ", menuId='" + menuId + '\'' +
                ", menuDescription='" + menuDescription + '\'' +
                ", type=" + type +
                ", linkUrl='" + linkUrl + '\'' +
                ", validate=" + validate +
                ", updatetime=" + updatetime +
                ", createtime=" + createtime +
                '}';
    }
}
