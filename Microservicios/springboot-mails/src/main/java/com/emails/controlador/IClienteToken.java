package com.emails.controlador;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.commons.dto.TokenAutenticarDTO;

@FeignClient(name="servicio-tokens", url="http://localhost:8088")
public interface IClienteToken {

	@PostMapping("/guardar-token")
	public void guardarToken(TokenAutenticarDTO tokenAutenticarDTO);
	
	@DeleteMapping("/eliminar-token/{id}")
	public void eliminarToken(@PathVariable Long id); 
}
