package com.heima.app.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author AdRainty
 * @version V1.0.0
 * @since 2023/7/16 18:25
 */

@SpringBootApplication
@EnableDiscoveryClient
public class AppGateApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppGateApplication.class);
    }
}
