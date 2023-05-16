package com.emails.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.UsuarioDTO;
import com.emails.servicio.IEmailServicio;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class EmailsControlador {

	@Autowired
	private IEmailServicio emailServicio;
	
	@PostMapping("/registrado")
	private String emailPorRegistro(@RequestBody UsuarioDTO usuarioDTO) {
		boolean enviadoConExito = emailServicio.enviarEmailRegistro(usuarioDTO);
		return enviadoConExito ?  "Email enviado con exito " :  "Email no enviado";
	}
	
	@PostMapping("/restablecer")
	private String emailPorRestablecerPassword(@RequestBody UsuarioDTO usuarioDTO) {
		boolean enviadoConExito = emailServicio.enviarEmailRestablecerPassword(usuarioDTO);
		return enviadoConExito ?  "Email enviado con exito " :  "Email no enviado";
	}
}
