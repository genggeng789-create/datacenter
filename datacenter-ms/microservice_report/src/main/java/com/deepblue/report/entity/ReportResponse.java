package com.deepblue.report.entity;

import lombok.Data;

import java.util.List;

@Data
public class ReportResponse {
    String depId;
    String depName;
    String userId;
    List<Menu> report;
    String userName;
    String status;
    String roleName;
    int code;
}
