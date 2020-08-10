package com.sie.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@MapperScan("com.sie.demo.dao")
public class DemoApplication {

    @PostConstruct
    void start(){
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
