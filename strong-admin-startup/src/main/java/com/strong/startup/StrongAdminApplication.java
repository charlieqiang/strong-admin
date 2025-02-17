package com.strong.startup;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author charlie
 * @date 2025/2/11 13:13
 **/
@SpringBootApplication(scanBasePackages = "com.strong")
@MapperScan("com.strong.system.mapper")
public class StrongAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(StrongAdminApplication.class, args);
    }
}
