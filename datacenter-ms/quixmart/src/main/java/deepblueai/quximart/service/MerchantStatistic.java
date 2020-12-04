package deepblueai.quximart.service;

import com.alibaba.fastjson.JSON;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

import static deepblueai.quximart.tools.Tools.getLastDate;

@Slf4j
@Service
public class MerchantStatistic {

    private String join_condition(String sql, ConsumerPurchaseRates consumerPurchaseRates)
    {
        if(consumerPurchaseRates.getMerchantId() != null)
        {
            sql = sql + " and merchant_id = '" + consumerPurchaseRates.getMerchantId() + "'";
        }

        if(consumerPurchaseRates.getStartDate() != null)
        {
            sql = sql + " and register_time >= '" + consumerPurchaseRates.getStartDate() + "'";
        }

        if(consumerPurchaseRates.getEndDate() != null)
        {
            sql = sql + " and register_time <= '" + consumerPurchaseRates.getEndDate() + "'";
        }
        return sql;
    }

    @Autowired
    DataSource dataSource;

    public ArrayList<ConsumerPurchaseRates> QueryMerchantRates(ConsumerPurchaseRates dds) throws Exception {
        ArrayList<ConsumerPurchaseRates> list = new ArrayList<>();
        Connection connection = dataSource.getConnection();
        if(dds.getKeyType() == null || dds.getStartDate() == null || dds.getEndDate() == null)
        {
            throw new Exception("缺少参数,请注意!");
        }
        String sql = "select register_time,if_first,if_second,"+dds.getKeyType()+",register_count from hive.quixmart_analysis.consumer_purchase_rates where  1=1";
        sql = join_condition(sql,dds);
        sql += " and batch = '" + getLastDate() + "'";
        log.info(sql);

        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();

        while (resultSet.next()) {
            ConsumerPurchaseRates consumerPurchaseRates = new ConsumerPurchaseRates();
            consumerPurchaseRates = fillValue(dds,resultSet);
            consumerPurchaseRates.setRegisterCount(resultSet.getInt("register_count"));
            consumerPurchaseRates.setIfFirst(resultSet.getInt("if_first"));
            consumerPurchaseRates.setIfSecond(resultSet.getInt("if_second"));
            consumerPurchaseRates.setRegisterTime(resultSet.getString("register_time"));
            consumerPurchaseRates = JSON.parseObject(consumerPurchaseRates.toJson(), ConsumerPurchaseRates.class);
            list.add(consumerPurchaseRates);
        }
        return list;
    }

    private ConsumerPurchaseRates fillValue(ConsumerPurchaseRates consumerPurchaseRates,ResultSet resultSet) throws SQLException {
        consumerPurchaseRates.setRateOf1stWithin0(resultSet.getDouble(consumerPurchaseRates.getKeyType()));
        return consumerPurchaseRates;
    }
}
