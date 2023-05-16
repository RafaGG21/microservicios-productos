package com.auth.controlador;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.commons.dto.UsuarioDTO;

@FeignClient(name="servicio-emails", url="http://localhost:8087")
public interface ClienteEmail {

	
	@PostMapping("/restablecer")
	String emailPorRestablecerPassword(@RequestBody UsuarioDTO usuarioDTO);
}
