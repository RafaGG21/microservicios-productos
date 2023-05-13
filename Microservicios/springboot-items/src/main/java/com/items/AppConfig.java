package com.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.productos.cliente.feign.IProductoClienteFeign;
import com.productos.cliente.feign.ProductoClienteFeignImpl;


@Configuration
public class AppConfig {
	
	@Bean("clienteRest")
	public RestTemplate registrarRestTemplate() {
		return new RestTemplate();
	}
	
	@Bean
    public IProductoClienteFeign productoClienteFeign() {
        return new ProductoClienteFeignImpl();
    }
	
}
