package com.deepblue.downloadPackage.congfigure;

import com.google.common.collect.ImmutableMap;
import org.mitre.dsmiley.httpproxy.ProxyServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Servlet;
import java.util.Map;

/**
 * @ClassName: SolrProxyServletConfiguration
 * @Description: 反向代理
 * @author: KingKong
 * @date: 2020年03月24日 12:11
 */
@Configuration
@ConfigurationProperties(prefix = "proxy")
@EnableConfigurationProperties(ProxyServletConfiguration.class)
public class ProxyServletConfiguration {
    /**
     * 读取配置文件中路由设置
     */
    //@Value("${proxy.servlet_url}")
    private String servletUrl;
    /**
     * 读取配置中代理目标地址
     */
    //@Value("${proxy.target_url}")
    private String targetUrl;

    public void setServletUrl(String servletUrl) {
        this.servletUrl = servletUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    @Bean
    public Servlet createProxyServlet(){
        // 创建新的ProxyServlet
        return new ProxyServlet();
    }

    @Bean
    public ServletRegistrationBean proxyServletRegistration(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(createProxyServlet(), servletUrl);
        //设置网址以及参数
        Map<String, String> params = ImmutableMap.of(
                "targetUri", targetUrl,
                "log", "true");
        registrationBean.setInitParameters(params);
        return registrationBean;
    }
}
