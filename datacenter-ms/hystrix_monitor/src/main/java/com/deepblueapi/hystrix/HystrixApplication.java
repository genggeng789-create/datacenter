package com.deepblueapi.hystrix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/*
* Hystrix Dashboard启动类
* */
@SpringBootApplication
@EnableHystrixDashboard
public class HystrixApplication {
    public static void main(String [] args){
        SpringApplication.run(HystrixApplication.class,args);
    }
}
