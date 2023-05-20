package com.auth.controlador;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.commons.dto.UsuarioTokenDTO;
import com.commons.utils.TokenUtils;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AutentificacionControlador {

	@Autowired
	private IAutentificationService autentificationService;

	@Autowired
	private ClienteEmail clienteEmail;
	
	@Autowired
	private IClienteToken clienteToken;

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

	@PutMapping("editar-password/{id}")
	public ResponseEntity<ResponseDTO> editarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioEditado = autentificationService.editarPasswordUsuario(usuarioDTO);
		ResponseDTO response = new ResponseDTO();
		if (usuarioEditado == null) {
			response.setCorrecto(false);
			return ResponseEntity.badRequest().body(response);
		} else {	
			response.setCorrecto(true);
			return ResponseEntity.ok().body(response);
		}

	}
	
	@PutMapping("editar-usuario/{id}")
	public ResponseEntity<UsuarioDTO> editarUsuarioImagen(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioEditado = autentificationService.editarUsuario(id, usuarioDTO);
		ResponseDTO response = new ResponseDTO();
		if (usuarioEditado == null) {
			response.setCorrecto(false);
			return ResponseEntity.badRequest().build();
		} else {	
			response.setCorrecto(true);
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

	@GetMapping("/cambiar-password")
	public UsuarioTokenDTO resetPassword(@RequestParam("token") String token) {
		UsuarioTokenDTO usuarioTokenDTO = autentificationService.obtenerUsuarioPorToken(token);	
		if (usuarioTokenDTO != null && TokenUtils.isTokenValid(usuarioTokenDTO.getFechaCreacion())) {
			clienteToken.eliminarToken(usuarioTokenDTO.getIdToken());
			return usuarioTokenDTO;
		}
		return null;
	}
	

	@GetMapping("/email/{email}")
	public ResponseEntity<UsuarioDTO> getUsuarioPorEmail(@PathVariable String email) {
		UsuarioDTO usuario = autentificationService.obtenerUsuarioPorEmail(email);
		if (usuario == null) {
			return ResponseEntity.notFound().build();
		} else {

			return ResponseEntity.ok().body(usuario);
		}

	}
}
