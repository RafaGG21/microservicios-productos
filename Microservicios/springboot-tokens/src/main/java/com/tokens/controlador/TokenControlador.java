package com.tokens.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.TokenAutenticarDTO;
import com.tokens.servicio.ITokenServicio;

@RestController
public class TokenControlador {

	@Autowired
	private ITokenServicio tokenServicio;
	
	@PostMapping("/guardar-token")
	public void guardarToken(@RequestBody TokenAutenticarDTO tokenAutenticarDTO) {
		tokenServicio.guardarToken(tokenAutenticarDTO);
	}
	
	@DeleteMapping("/eliminar-token/{id}")
	public void eliminarToken(@PathVariable Long id) {
		tokenServicio.eliminarToken(id);
	}
}
