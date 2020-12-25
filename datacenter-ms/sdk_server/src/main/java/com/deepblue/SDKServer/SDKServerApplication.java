package com.deepblue.SDKServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@EnableEurekaClient
@EnableFeignClients
@EnableSwagger2
public class SDKServerApplication {
    public static void main(String [] args){
        SpringApplication.run(SDKServerApplication.class,args);
    }
}
