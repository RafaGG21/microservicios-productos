package com.emails.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.ResponseDTO;
import com.commons.dto.UsuarioDTO;
import com.emails.servicio.IEmailServicio;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailsControlador {

	@Autowired
	private IEmailServicio emailServicio;
	
	@PostMapping("/registrado")
	public ResponseEntity<ResponseDTO> emailPorRegistro(@RequestBody UsuarioDTO usuarioDTO) {
		boolean enviadoConExito = emailServicio.enviarEmailRegistro(usuarioDTO);
		ResponseDTO response = new ResponseDTO();
		if (enviadoConExito) {
			response.setCorrecto(false);
			return ResponseEntity.badRequest().body(response);
		} else {	
			response.setCorrecto(true);
			return ResponseEntity.ok().body(response);
		}
	}
	
	@PostMapping("/restablecer")
	public ResponseEntity<ResponseDTO> emailPorRestablecerPassword(@RequestBody UsuarioDTO usuarioDTO) {
		boolean enviadoConExito = emailServicio.enviarEmailRestablecerPassword(usuarioDTO);
		ResponseDTO response = new ResponseDTO();
		if (enviadoConExito) {
			response.setCorrecto(true);
			return ResponseEntity.ok().body(response);
		} else {	
			response.setCorrecto(false);
			return ResponseEntity.badRequest().body(response);
		}

	}
}
