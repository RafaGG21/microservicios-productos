package com.gateway.com.gateway.filtros;

import java.util.Optional;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;



import reactor.core.publisher.Mono;

@Component
public class FiltroGlobal implements GlobalFilter, Ordered{

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		exchange.getRequest().mutate().headers(header -> header.add("token","21071993"));
		return chain.filter(exchange).then(Mono.fromRunnable( ()-> {
			
			Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("token")).ifPresent(valor -> {
				exchange.getResponse().getHeaders().add("token",valor);
			});
			
			exchange.getResponse().getCookies().add("miCookie", ResponseCookie.from("cookie","miCookie").build());
			exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
		}));
	}

	@Override
	public int getOrder() {
		return -1;
	}

	
}
