package deepblueai.quximart.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import deepblueai.quximart.entity.DashDeviceStatus;
import deepblueai.quximart.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import static deepblueai.quximart.tools.Tools.getLastDate;

@Slf4j
@Service
public class DeviceStatistic {


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
}
