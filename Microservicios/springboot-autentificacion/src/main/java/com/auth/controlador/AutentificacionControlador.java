package com.auth.controlador;

import org.apache.http.protocol.HTTP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.servicios.IAutentificationService;
import com.commons.dto.ResponseDTO;
import com.commons.dto.UsuarioDTO;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AutentificacionControlador {

	@Autowired
	IAutentificationService autentificationService;
	
	@GetMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UsuarioDTO usuarioDTO) {
		
		UsuarioDTO usuario = autentificationService.obtenerPorEmailYPassword(usuarioDTO.getEmail(),usuarioDTO.getPassword());
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		} else {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(true);
			//UsuarioDTO usuarioDevolver = new UsuarioDTO(usuario.getEmail(),usuario.getNombre(),null);
			return ResponseEntity.ok().body(respuesta);
		}
	}
	
	@PostMapping("/registrar")
	public ResponseEntity<ResponseDTO> registrar(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioYaExistente = autentificationService.obtenerUsuarioPorEmail(usuarioDTO.getEmail());
		UsuarioDTO usuarioGuardado;
		try {
			if (usuarioYaExistente != null && usuarioYaExistente.getId() != null) {
				ResponseDTO respuesta = new ResponseDTO();
				respuesta.setCorrecto(false);
				return ResponseEntity.badRequest().body(respuesta);
			} else {
				usuarioGuardado = autentificationService.registrarUsuario(usuarioDTO);
				if (usuarioGuardado == null || usuarioGuardado.getId() == null) {
					ResponseDTO respuesta = new ResponseDTO();
					respuesta.setCorrecto(false);
					return ResponseEntity.badRequest().body(respuesta);
				} else {
					ResponseDTO respuesta = new ResponseDTO();
					respuesta.setCorrecto(true);
					//UsuarioDTO usuarioDevolver = new UsuarioDTO(usuarioGuardado.getEmail(), usuarioGuardado.getNombre(),null);
					return ResponseEntity.ok().body(respuesta);
				}
			} 
		} catch (Exception e) {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(false);
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

}
