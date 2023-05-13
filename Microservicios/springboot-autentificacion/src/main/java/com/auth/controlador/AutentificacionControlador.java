package com.auth.controlador;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.auth.servicios.IAutentificationService;
import com.commons.dto.UsuarioDTO;


@RestController
public class AutentificacionControlador {

	@Autowired
	IAutentificationService autentificationService;
	
	@GetMapping("/login")
	public ResponseEntity<UsuarioDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
		
		UsuarioDTO usuario = autentificationService.obtenerPorEmailYPassword(usuarioDTO.getEmail(),usuarioDTO.getPassword());
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().build();
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<UsuarioDTO> registrar(@RequestBody UsuarioDTO usuarioDTO) {
		
		UsuarioDTO usuarioGuardado = autentificationService.registrarUsuario(usuarioDTO);
		if(usuarioGuardado == null || usuarioGuardado.getId() == null) {
			return  ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().body(usuarioGuardado);
		}
	}
	
}
