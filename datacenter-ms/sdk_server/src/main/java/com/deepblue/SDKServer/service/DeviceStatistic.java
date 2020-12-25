package com.deepblue.SDKServer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.deepblue.SDKServer.common.BaseResponse;
import com.deepblue.SDKServer.common.PageModel;
import com.deepblue.SDKServer.entity.DashDeviceStatus;
import com.deepblue.SDKServer.entity.User;
import com.deepblue.SDKServer.sys.SysUserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.deepblue.SDKServer.tools.Tools.getLastDate;

@Slf4j
@Service
public class DeviceStatistic implements QueryService<DashDeviceStatus> {

    @Autowired
    DataSource dataSource;

    public ArrayList<DashDeviceStatus> QueryDeviceStatus(DashDeviceStatus dds) throws Exception {
        ArrayList<DashDeviceStatus> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        if(dds.getDeviceCode() == null){
            throw new Exception("缺少设备号");
        }
        String sql = "select * from hive.quixmart_analysis.dash_device_status where device_code= '" + dds.getDeviceCode() + "'";
        sql += " and batch = '" + getLastDate() + "'";

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            DashDeviceStatus dashDeviceStatus = new DashDeviceStatus();

            dashDeviceStatus.setDeviceCode(resultSet.getString(1));
            dashDeviceStatus.setProblemStatus1(resultSet.getString(2));
            dashDeviceStatus.setProblemStatus2(resultSet.getString(3));
            dashDeviceStatus.setProblemStatus3(resultSet.getString(4));
            dashDeviceStatus = JSON.parseObject(dashDeviceStatus.toJson(), DashDeviceStatus.class);
            list.add(dashDeviceStatus);
        }
        return list;
    }

    @Override
    public BaseResponse<PageModel<List<DashDeviceStatus>>> queryList(SysUserDTO dto){
        List<DashDeviceStatus> list = null;
        int status = 0;
        String errorMsg = "success";
        try {
            list = QueryDeviceStatus(JSONObject.toJavaObject(dto.getQueryCondition(), DashDeviceStatus.class));
        } catch (Exception e) {
            errorMsg = "fail, " + e.getMessage();
            e.printStackTrace();
            status = -1;
        }
        PageModel<List<DashDeviceStatus>> pageModel = PageModel.<List<DashDeviceStatus>>builder().data(list).pageIndex(dto.getPageIndex()).pageSize(list.size()).build();
        return new BaseResponse<PageModel<List<DashDeviceStatus>>>(pageModel,status,errorMsg);
    }
}
