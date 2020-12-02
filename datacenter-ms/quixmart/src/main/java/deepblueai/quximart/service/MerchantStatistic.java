package deepblueai.quximart.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import deepblueai.quximart.entity.DashDeviceStatus;
import deepblueai.quximart.tools.Tools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

@Slf4j
@Service
public class MerchantStatistic {

    private String join_condition(String sql, ConsumerPurchaseRates consumerPurchaseRates)
    {
        if(consumerPurchaseRates.getMerchantId() != null)
        {
            sql = sql + "and merchant_id = '" + consumerPurchaseRates.getMerchantId() + "'";
        }

        if(consumerPurchaseRates.getRegisterTime() != null)
        {
            sql = sql + "and register_time = cast('" + consumerPurchaseRates.getRegisterTime() + "' as timestamp)";
        }
        return sql;
    }

    @Autowired
    DataSource dataSource;

    public ArrayList<ConsumerPurchaseRates> QueryMerchantRates(ConsumerPurchaseRates dds) throws SQLException {
        ArrayList<ConsumerPurchaseRates> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        String sql = "select * from hive.quixmart_analysis.consumer_purchase_rates where 1=1 ";
        sql = join_condition(sql,dds);
        log.info(sql);
        //sql += " limit 5";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            ConsumerPurchaseRates consumerPurchaseRates = new ConsumerPurchaseRates();

            consumerPurchaseRates.setRegisterTime(Tools.TimestampToString(resultSet.getTimestamp(1),"yyyy/MM/dd"));
            consumerPurchaseRates.setMerchantId(resultSet.getString(2));
            consumerPurchaseRates.setRateOf1stWithin0(resultSet.getDouble(3));
            consumerPurchaseRates.setRateOf1st(resultSet.getDouble(4));
            consumerPurchaseRates.setRateOf2ndWithin0(resultSet.getDouble(5));
            consumerPurchaseRates.setRateOf2ndWithin7(resultSet.getDouble(6));
            consumerPurchaseRates.setRateOf2ndWithin14(resultSet.getDouble(7));
            consumerPurchaseRates.setRateOf2ndwithin21(resultSet.getDouble(8));
            consumerPurchaseRates.setRegisterCount(resultSet.getInt(9));
            consumerPurchaseRates.setIfFirst(resultSet.getInt(10));
            consumerPurchaseRates.setIfSecond(resultSet.getInt(11));
            consumerPurchaseRates = JSON.parseObject(consumerPurchaseRates.toJson(), ConsumerPurchaseRates.class);
            list.add(consumerPurchaseRates);
        }
        return list;
    }
}
