package com.auth.controlador;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth.servicios.IAutentificationService;
import com.commons.dto.RequestPasswordDTO;
import com.commons.dto.ResetPasswordDto;
import com.commons.dto.ResponseDTO;
import com.commons.dto.UsuarioDTO;

import com.commons.utils.TokenUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AutentificacionControlador {

	@Autowired
	private IAutentificationService autentificationService;

	@Autowired
	private ClienteEmail clienteEmail;

	@PostMapping("/login")
	public ResponseEntity<ResponseDTO> login(@RequestBody UsuarioDTO usuarioDTO) {

		UsuarioDTO usuario = autentificationService.obtenerPorEmailYPassword(usuarioDTO.getEmail(),
				usuarioDTO.getPassword());
		if (usuario == null) {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(false);
			return ResponseEntity.badRequest().body(respuesta);
		} else {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(true);
			// UsuarioDTO usuarioDevolver = new
			// UsuarioDTO(usuario.getEmail(),usuario.getNombre(),null);
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
					// UsuarioDTO usuarioDevolver = new UsuarioDTO(usuarioGuardado.getEmail(),
					// usuarioGuardado.getNombre(),null);
					return ResponseEntity.ok().body(respuesta);
				}
			}
		} catch (Exception e) {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(false);
			return ResponseEntity.badRequest().body(respuesta);
		}
	}

	@PutMapping("editar/{email}")
	public ResponseEntity<UsuarioDTO> editarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioEditado = autentificationService.editarUsuario(usuarioDTO);
		if (usuarioEditado == null) {
			return ResponseEntity.badRequest().build();
		} else {
			return ResponseEntity.ok().body(usuarioEditado);
		}

	}

	@PostMapping("/request")
	public ResponseEntity<String> requestPasswordReset(@RequestBody RequestPasswordDTO requestPasswordDTO) {

		UsuarioDTO usuario = autentificationService.obtenerUsuarioPorEmail(requestPasswordDTO.getEmail());
		if (usuario == null) {
			ResponseDTO respuesta = new ResponseDTO();
			respuesta.setCorrecto(false);
			return ResponseEntity.badRequest().body(requestPasswordDTO.getEmail());
		} else {
			String resultado = clienteEmail.emailPorRestablecerPassword(usuario);
			return ResponseEntity.ok().body(resultado);
		}

	}

	@PostMapping("/reset-password")
	public ResponseEntity<String> resetPassword(@RequestParam("token") String token,
			@RequestBody ResetPasswordDto resetPasswordDto) {

		if (TokenUtils.isTokenValid(token)) {
			autentificationService.editarPasswordUsuario(resetPasswordDto.getEmail(), resetPasswordDto.getPassword());
			TokenUtils.deleteToken(token);
			return ResponseEntity.ok("Contraseña restablecida exitosamente.");
		}
		return ResponseEntity.badRequest().body("Token de restablecimiento de contraseña inválido.");
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioDTO> getUsuarioPorEmail(@RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuario = autentificationService.obtenerUsuarioPorEmail(usuarioDTO.getEmail());
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok().body(usuarioDTO);
		}

	}
}
