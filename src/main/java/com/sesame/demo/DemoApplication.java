package com.sesame.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.sesame.demo.controller",
        "com.sesame.demo.data",
        "com.sesame.demo.repository",
        "com.sesame.demo.security",
        "com.sesame.demo.service"
})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
