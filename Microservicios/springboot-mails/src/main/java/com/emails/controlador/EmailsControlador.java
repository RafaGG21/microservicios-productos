package com.emails.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.UsuarioDTO;
import com.emails.servicio.IEmailServicio;

@RestController
public class EmailsControlador {

	@Autowired
	private IEmailServicio emailServicio;
	
	@GetMapping("registrado")
	private String emailPorRegistro(@RequestBody UsuarioDTO usuarioDTO) {
		boolean enviadoConExito = emailServicio.enviarEmailRegistro(usuarioDTO);
		return enviadoConExito ?  "Email enviado con exito " :  "Email no enviado";
	}
}