package deepblueai.quximart.service;

import com.alibaba.druid.pool.DruidDataSource;
import deepblueai.quximart.entity.ConsumerPurchaseRates;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

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

    @Autowired
    DataSource dataSource;

    public List<ConsumerPurchaseRates> QueryPurchaseRates() throws SQLException {
        ArrayList<ConsumerPurchaseRates> list = new ArrayList<ConsumerPurchaseRates>();
        Connection connection = dataSource.getConnection();
        String sql = "select * from quixmart_analysis.consumer_purchase_rates";
        PreparedStatement prepareStatement = connection.prepareStatement(sql);
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            String cityName = resultSet.getString("name");
            System.out.println(cityName);
        }
        return list;
    }
}
