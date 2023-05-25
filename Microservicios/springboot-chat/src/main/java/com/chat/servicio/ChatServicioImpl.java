package com.chat.servicio;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.repositorio.IChatRepositorio;
import com.chat.repositorio.IMensajeRepositorio;
import com.commons.dto.ChatDTO;
import com.commons.dto.MensajeDTO;
import com.commons.entidades.Chat;
import com.commons.entidades.Mensaje;
import com.commons.mapper.GenericMapper;

@Service
public class ChatServicioImpl implements IChatServicio{

	@Autowired
	private IChatRepositorio chatRepositorio;

	@Autowired
	private IMensajeRepositorio mensajeRepositorio;
	
	@Override
	public  ChatDTO  crearChar(ChatDTO chatDTO) {
		 Chat chat = GenericMapper.map(chatDTO, Chat.class);
		 chat.setListaMensajes(new ArrayList<>());
		 chat.setFechaCreacion(new Date());
		 Chat chatGuardado = chatRepositorio.save(chat);
		 return  GenericMapper.map(chatGuardado, ChatDTO.class);
	}

	@Override
	public ChatDTO obtenerChatMensajesYUsuarios(String nombre_usuario_comprador, String nombre_usuario_vendedor) {
		final Chat chat = chatRepositorio.chatByCompradorAndVendedor(nombre_usuario_comprador, nombre_usuario_vendedor);
		if (chat != null) {
			ChatDTO chatDTO = GenericMapper.map(chat,ChatDTO.class);
			List<Mensaje> mensajes = mensajeRepositorio.getMensajesPorChat(chatDTO.getId());
			if(mensajes != null && !mensajes.isEmpty() ) {
				List<MensajeDTO> listaMensajesDTO = mensajes.stream()
						.map(mensaje -> GenericMapper.map(mensaje, MensajeDTO.class))
						.collect(Collectors.toList());
				chatDTO.setMensajes(listaMensajesDTO);
			}
			return chatDTO;
		} else {
			return null;
		}
	}
	
	@Override
	public List<ChatDTO> obtenerChatVendedorMensajesYUsuarios(String nombre_usuario_vendedor) {
		final List<Chat> listaChats = chatRepositorio.chatByVendedor(nombre_usuario_vendedor);
		if (listaChats != null) {
			List<ChatDTO> listaDTO = listaChats.stream().map(chat -> GenericMapper.map(chat,ChatDTO.class)).collect(Collectors.toList());
			for (ChatDTO chat : listaDTO) {
				List<Mensaje> mensajes = mensajeRepositorio.getMensajesPorChat(chat.getId());
				if(mensajes != null && !mensajes.isEmpty() ) {
					List<MensajeDTO> listaMensajesDTO = mensajes.stream()
							.map(mensaje -> GenericMapper.map(mensaje, MensajeDTO.class))
							.collect(Collectors.toList());
					chat.setMensajes(listaMensajesDTO);
				}
				
			}
			return listaDTO;
		} else {
			return null;
		}
	}

}
