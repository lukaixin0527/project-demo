package com.project.parse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @ClassName ParseApplication
 * @Description todo parse定时器启动入口
 * @Author lu
 * @Date 2020/4/10
 * @Version 1.0
 */
@SpringBootApplication
@EnableScheduling
@ComponentScan(value="com.project") // 解决扫描不到Service问题
@EnableJpaRepositories("com.project") // 解决扫描不到Dao问题
@EntityScan(basePackages = "com.project.entity") // 解决扫描不到entity问题
public class ParseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParseApplication.class, args);
    }
}
