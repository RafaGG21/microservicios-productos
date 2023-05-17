package com.tokens;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableDiscoveryClient
@RefreshScope
@EntityScan({"com.commons.entidades","com.commons.dto","com.commons.mapper","com.commons.utils"})
@SpringBootApplication
public class SpringbootTokensApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTokensApplication.class, args);
	}

}
