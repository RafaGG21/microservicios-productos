package com.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@EntityScan({"com.commons.entidades","com.commons.dto","com.commons.mapper"})
@SpringBootApplication
public class SpringbootUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootUsuariosApplication.class, args);
	}

}
