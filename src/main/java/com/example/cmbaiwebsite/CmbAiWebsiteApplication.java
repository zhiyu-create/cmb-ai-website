package com.example.cmbaiwebsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.example.cmbaiwebsite.mapper")
public class CmbAiWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmbAiWebsiteApplication.class, args);
    }

}
