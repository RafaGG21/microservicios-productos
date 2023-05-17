package com.tokens;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.commons.utils.TokenUtils;

@Configuration
public class AppConfig {

	@Bean
    public TokenUtils getTokenUtils() {
    	return new TokenUtils();
    }
}
