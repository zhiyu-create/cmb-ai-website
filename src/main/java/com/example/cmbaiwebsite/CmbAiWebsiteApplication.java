package com.example.cmbaiwebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CmbAiWebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmbAiWebsiteApplication.class, args);
    }

}
