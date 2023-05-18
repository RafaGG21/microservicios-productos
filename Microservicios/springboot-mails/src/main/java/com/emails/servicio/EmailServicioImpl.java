package com.emails.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


import com.commons.dto.TokenAutenticarDTO;
import com.commons.dto.UsuarioDTO;
import com.commons.entidades.Email;
import com.commons.utils.TokenUtils;
import com.emails.controlador.IClienteToken;
import com.emails.repositorio.IEmailRepositorio;

import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;

@Service
public class EmailServicioImpl implements IEmailServicio{

	@Autowired
	private IEmailRepositorio emailRepositorio;
	
	@Autowired
	private JavaMailSender emailSender;
		
	@Autowired
	private IClienteToken clienteToken;
	
	@Override
	public boolean enviarEmailRegistro(UsuarioDTO usuarioDTO) {
		if (isValidEmailAddress(usuarioDTO.getEmail())) {
			//UsuarioDTO usuario = clienteRest.getForObject(REGISTRO_ROUTE, UsuarioDTO.class);
			Email email = emailRepositorio.buscarEmailPorTipo("registro");
			String cuerpoEmail = email.getCuerpo().replace("<EMAIL>", usuarioDTO.getEmail());
			cuerpoEmail = cuerpoEmail.replace("<NOMBRE>", usuarioDTO.getNombre());

			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(usuarioDTO.getEmail());
			message.setSubject(email.getAsunto());
			message.setText(cuerpoEmail);
			emailSender.send(message);
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public boolean enviarEmailRestablecerPassword(UsuarioDTO usuarioDTO) {
		if (isValidEmailAddress(usuarioDTO.getEmail())) {
			Email email = emailRepositorio.buscarEmailPorTipo("restablecer");
			String cuerpoEmail = email.getCuerpo();
			cuerpoEmail = cuerpoEmail.replace("<nombre>", usuarioDTO.getNombre());
			TokenAutenticarDTO token = TokenUtils.generateToken(usuarioDTO.getId());
			clienteToken.guardarToken(token);
			String url = "http://localhost:4200/cambiar-password?token=";
			url += token.getToken();
			cuerpoEmail = cuerpoEmail.replace("<url>", url);
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(usuarioDTO.getEmail());
			message.setSubject(email.getAsunto());
			message.setText(cuerpoEmail);
			emailSender.send(message);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isValidEmailAddress(String email) {
	    try {
	        InternetAddress emailAddr = new InternetAddress(email);
	        emailAddr.validate();
	        return true;
	    } catch (AddressException ex) {
	        return false;
	    }
	}


}
