package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@EntityScan({"com.commons.entidades","com.commons.dto","com.commons.mapper","com.commons.utils"})
@SpringBootApplication
@EnableFeignClients
@ImportAutoConfiguration({FeignAutoConfiguration.class})
public class SpringbootUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootUsuariosApplication.class, args);
	}

}
