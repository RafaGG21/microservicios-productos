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
		ChatDTO chatDTORetornar = null;
		 try {
			Chat chat = GenericMapper.map(chatDTO, Chat.class);
			chat.setListaMensajes(new ArrayList<>());
			chat.setFechaCreacion(new Date());
			Chat chatGuardado = chatRepositorio.save(chat);
			chatDTORetornar = GenericMapper.map(chatGuardado, ChatDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return chatDTORetornar;
	}

	@Override
	public ChatDTO obtenerChatMensajesYUsuarios(String nombre_usuario_comprador, String nombre_usuario_vendedor) {
		ChatDTO chatDTO = null; 
		try {
			final Chat chat = chatRepositorio.chatByCompradorAndVendedor(nombre_usuario_comprador,
					nombre_usuario_vendedor);
			if (chat != null) {
				chatDTO = GenericMapper.map(chat, ChatDTO.class);
				List<Mensaje> mensajes = mensajeRepositorio.getMensajesPorChat(chatDTO.getId());
				if (mensajes != null && !mensajes.isEmpty()) {
					List<MensajeDTO> listaMensajesDTO = mensajes.stream()
							.map(mensaje -> GenericMapper.map(mensaje, MensajeDTO.class)).collect(Collectors.toList());
					chatDTO.setMensajes(listaMensajesDTO);
				}
			} else {
				return null;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatDTO;
	}
	
	@Override
	public List<ChatDTO> obtenerChatVendedorMensajesYUsuarios(String nombre_usuario_vendedor) {
		List<ChatDTO> listaDTO = null;
		try {
			final List<Chat> listaChats = chatRepositorio.chatByVendedor(nombre_usuario_vendedor);
			if (listaChats != null) {
				listaDTO = listaChats.stream().map(chat -> GenericMapper.map(chat, ChatDTO.class))
						.collect(Collectors.toList());
				for (ChatDTO chat : listaDTO) {
					List<Mensaje> mensajes = mensajeRepositorio.getMensajesPorChat(chat.getId());
					if (mensajes != null && !mensajes.isEmpty()) {
						List<MensajeDTO> listaMensajesDTO = mensajes.stream()
								.map(mensaje -> GenericMapper.map(mensaje, MensajeDTO.class))
								.collect(Collectors.toList());
						chat.setMensajes(listaMensajesDTO);
					}
				}
				
			} else {
				return null;
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaDTO;
	}

	@Override
	public ChatDTO verChatPorId(Long id) {
		ChatDTO chatDTO = null;
		try {
			chatDTO = GenericMapper.map(chatRepositorio.findById(id).orElse(null), ChatDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chatDTO;
	}
}
