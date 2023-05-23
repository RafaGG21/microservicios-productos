package com.chat.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.chat.repositorio.IChatRepositorio;
import com.chat.repositorio.IMensajeRepositorio;

import com.commons.entidades.Chat;
import com.commons.entidades.Mensaje;
import com.commons.entidades.Usuario;

public class ChatWebSocketControlador extends TextWebSocketHandler {

	private List<WebSocketSession> sessions = new ArrayList<>();
	private IMensajeRepositorio mensajeRepositorio;
	private IChatRepositorio chatRepositorio;

	
	public ChatWebSocketControlador(IMensajeRepositorio mensajeRepositorio, 
			IChatRepositorio chatRepositorio) {
		
		this.mensajeRepositorio = mensajeRepositorio;
		this.chatRepositorio = chatRepositorio;

	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		String receivedMessage = message.getPayload();
		String username = (String) session.getAttributes().get("username");

        
		Mensaje mensaje = new Mensaje();
		mensaje.setContenido(receivedMessage);
		mensajeRepositorio.save(mensaje);

		for (WebSocketSession clientSession : sessions) {
			clientSession.sendMessage(new TextMessage(receivedMessage));
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		sessions.remove(session);
	}
}
