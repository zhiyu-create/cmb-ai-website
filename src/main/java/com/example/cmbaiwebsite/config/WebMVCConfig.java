package com.example.cmbaiwebsite.config;

import com.example.cmbaiwebsite.handle.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //跨域配置
        registry.addMapping("/**").allowedOrigins("http://localhost:8080");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //假设拦截test接口后续实际遇到拦截的接口是时，再配置真正的拦截接口
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/test");
    }
}


