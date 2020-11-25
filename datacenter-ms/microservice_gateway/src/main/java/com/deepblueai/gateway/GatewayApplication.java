package com.deepblueai.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
* 网管的微服务
* */
@SpringBootApplication
@EnableZuulProxy
@EnableSwagger2    //开启swagger功能
public class GatewayApplication {

    public static void main(String [] args){
        SpringApplication.run(GatewayApplication.class,args);
    }
}
