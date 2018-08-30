package com.yourboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;

/**
 * Created By shen on 2018-5-18 22:50
 */
@SpringBootApplication
//@ContextConfiguration(locations = {"classpath:application.properties"})
@EnableAspectJAutoProxy(exposeProxy=true, proxyTargetClass = false)
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.yourboot"})
@ImportResource(locations = {"classpath:spring-mybatis.xml"})
public class Application extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
