package com.gateway.com.gateway.filtros.factory;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;

import reactor.core.publisher.Mono;

public class GatewayFilterFactory extends AbstractGatewayFilterFactory<GatewayFilterFactory.Configuracion>{

	public GatewayFilterFactory() {
		super(Configuracion.class);
	}

	public static class Configuracion {
		
		private String mensaje;
		private String cookieValor;
		private String cookieNombre;
	}

	@Override
	public GatewayFilter apply(Configuracion config) {
		return (exchange, chain ) ->{
			
			return chain.filter(exchange).then(Mono.fromRunnable( ()-> {
				Optional.ofNullable(config.cookieValor).ifPresent(cookie -> {
					exchange.getResponse().addCookie(ResponseCookie.from(config.cookieNombre,cookie).build());
				});
				}));
		};
	}
}
