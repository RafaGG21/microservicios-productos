package com.emails.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.client.RestTemplate;

import com.commons.entidades.Email;
import com.emails.repositorio.IEmailRepositorio;

public class EmailServicioImpl implements IEmailServicio{

	@Autowired
	private IEmailRepositorio emailRepositorio;
	
	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private RestTemplate clienteRest;
	
	private String REGISTRO_ROUTE = "http://localhost:8085/registrado";
	
	@Override
	public void enviarEmailRegistro() {
		String emailUsuario = clienteRest.getForObject(REGISTRO_ROUTE, String.class);
		Email email = emailRepositorio.buscarEmailPorTipo("registro");
		SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(emailUsuario);
	    message.setSubject(email.getAsunto());
	    message.setText(email.getCuerpo());
	    emailSender.send(message);
	}

}
