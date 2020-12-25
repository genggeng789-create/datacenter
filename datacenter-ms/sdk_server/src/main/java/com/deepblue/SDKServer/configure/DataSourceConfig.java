package com.deepblue.SDKServer.configure;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Druid数据源配置
 */
//@Configuration
@SpringBootConfiguration
public class DataSourceConfig {

    private static String dbUrl;
    private static String username;
    private static String password;
    private static String driverClassName;
    private static int initialSize;
    private static int minIdle;
    private static int maxActive;
    private static int maxWait;
    private static String dbType;

    /**
     * 注册DruidServlet
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidServletRegistrationBean() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(new StatViewServlet());
        servletRegistrationBean.addUrlMappings("/druid/*");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        return servletRegistrationBean;
    }

    /**
     * 注册DruidFilter拦截
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidFilterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<String, String>();
        //设置忽略请求
        initParams.put("exclusions", "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }

    /**
     * 配置DataSource
     * @return
     * @throws SQLException
     */
    @Bean(initMethod = "init",destroyMethod = "close")
    @Primary
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDbType(dbType);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setUrl(dbUrl);
        druidDataSource.setInitialSize(initialSize);
        druidDataSource.setMinIdle(minIdle);
        druidDataSource.setMaxActive(maxActive);
        druidDataSource.setMaxWait(maxWait);
        druidDataSource.setUseGlobalDataSourceStat(true);
        druidDataSource.setDriverClassName(driverClassName);
        return druidDataSource;
    }

    @Value("${spring.datasource.url}")
    public void setDbUrl(String dbUrl) {
        DataSourceConfig.dbUrl = dbUrl;
    }

    @Value("${spring.datasource.username}")
    public void setUsername(String username) {
        DataSourceConfig.username = username;
    }

    @Value("${spring.datasource.password}")
    public void setPassword(String password) {
        DataSourceConfig.password = password;
    }

    @Value("${spring.datasource.driver-class-name}")
    public void setDriverClassName(String driverClassName) {
        DataSourceConfig.driverClassName = driverClassName;
    }

    @Value(value = "${spring.datasource.druid.initial-size}")
    public void setInitialSize(int initialSize) {
        DataSourceConfig.initialSize = initialSize;
    }

    @Value(value = "${spring.datasource.druid.min-idle}")
    public void setMinIdle(int minIdle) {
        DataSourceConfig.minIdle = minIdle;
    }

    @Value(value = "${spring.datasource.druid.max-active}")
    public void setMaxActive(int maxActive) {
        DataSourceConfig.maxActive = maxActive;
    }

    @Value(value = "${spring.datasource.druid.max-wait}")
    public void setMaxWait(int maxWait) {
        DataSourceConfig.maxWait = maxWait;
    }

    @Value(value = "${spring.datasource.druid.db-type}")
    public static void setDbType(String dbType) {
        DataSourceConfig.dbType = dbType;
    }
}
