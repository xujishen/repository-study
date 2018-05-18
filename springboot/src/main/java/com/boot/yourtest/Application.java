package com.boot.yourtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created By shen on 2018-5-18 22:50
 */
@SpringBootApplication
@ContextConfiguration(locations = {"classpath:application.properties"})
@EnableAspectJAutoProxy(exposeProxy=true)
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
