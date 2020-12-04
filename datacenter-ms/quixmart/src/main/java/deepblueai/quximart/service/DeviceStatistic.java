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

@Slf4j
@Service
public class DeviceStatistic {
    private String url = "jdbc:presto://10.16.32.113:8089/";
    public void testpresto2() throws SQLException {
        //数据源配置
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(url);
        dataSource.setDriverClassName("com.facebook.presto.jdbc.PrestoDriver"); //这个可以缺省的，会根据url自动识别
        dataSource.setUsername("deepblue");
        dataSource.setPassword("");

        //下面都是可选的配置
        dataSource.setInitialSize(10);  //初始连接数，默认0
        dataSource.setMaxActive(30);  //最大连接数，默认8
        dataSource.setMinIdle(10);  //最小闲置数
        dataSource.setMaxWait(2000);  //获取连接的最大等待时间，单位毫秒
        dataSource.setPoolPreparedStatements(true); //缓存PreparedStatement，默认false
        dataSource.setMaxOpenPreparedStatements(20); //缓存PreparedStatement的最大数量，默认-1（不缓存）。大于0时会自动开启缓存PreparedStatement，所以可以省略上一句代码

        //获取连接
        Connection connection = dataSource.getConnection();

        //Statement接口
        Statement statement = connection.createStatement();
        String sql1 = "select * from quixmart_analysis.consumer_purchase_rates";
        ResultSet rs = statement.executeQuery(sql1);
        while (rs.next()) {
            System.out.println(rs.getString("merchant_id"));
            System.out.println(rs.getDouble("rate_of_1st_within_0"));
        }

        //关闭连接
        rs.close();
        statement.close();
        connection.close();
    }

    private String join_condition(String sql,DashDeviceStatus dds)
    {
        if(dds.getMerchantId() != null)
        {
            sql = sql + "and merchant_id = '" + dds.getMerchantId() + "'";
        }

        if(dds.getRegisterTime() != null)
        {
            sql = sql + "and register_time = '" + dds.getRegisterTime() + "'";
        }
        return sql;
    }

    @Autowired
    DataSource dataSource;

    public ArrayList<DashDeviceStatus> QueryDeviceStatus(DashDeviceStatus dds) throws SQLException {
        ArrayList<DashDeviceStatus> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        String sql = "select * from hive.quixmart_analysis.consumer_purchase_rates where 1=1 ";
        sql = join_condition(sql,dds);
        log.info(sql);
        sql += " limit 5";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            DashDeviceStatus dashDeviceStatus = new DashDeviceStatus();

            dashDeviceStatus.setRegisterTime(Tools.TimestampToString(resultSet.getTimestamp(1),"yyyy/MM/dd"));
            dashDeviceStatus.setMerchantId(resultSet.getString(2));
            dashDeviceStatus.setRateOf1stWithin0(String.valueOf(resultSet.getDouble(3)));
            dashDeviceStatus.setRateOf1st(String.valueOf(resultSet.getDouble(4)));
            dashDeviceStatus.setRateOf2ndWithin0(String.valueOf(resultSet.getDouble(5)));
            dashDeviceStatus.setRateOf2ndWithin7(String.valueOf(resultSet.getDouble(6)));
            dashDeviceStatus.setRateOf2ndWithin14(String.valueOf(resultSet.getDouble(7)));
            dashDeviceStatus.setRateOf2ndwithin21(String.valueOf(resultSet.getDouble(8)));
            dashDeviceStatus.setRegisterCount(resultSet.getInt(9));
            dashDeviceStatus.setIfFirst(resultSet.getInt(10));
            dashDeviceStatus.setIfSecond(resultSet.getInt(11));
            dashDeviceStatus = JSON.parseObject(dashDeviceStatus.toJson(), DashDeviceStatus.class);
            list.add(dashDeviceStatus);
        }
        return list;
    }
}
