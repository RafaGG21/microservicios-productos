package com.chat.servicio;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chat.repositorio.IChatRepositorio;
import com.chat.repositorio.IMensajeRepositorio;
import com.commons.dto.MensajeDTO;
import com.commons.entidades.Chat;
import com.commons.entidades.Mensaje;
import com.commons.mapper.GenericMapper;

@Service
public class MensajesServicioImpl implements IMensajeServicio{

	@Autowired
	private IMensajeRepositorio mensajeRepositorio;
	
	@Autowired
	private IChatRepositorio chatRepositorio;
	
	@Override
	public MensajeDTO crearMensaje(MensajeDTO mensajeDTO) {
		
		Chat chat = chatRepositorio.findById(mensajeDTO.getChat_id()).orElse(null);
		Mensaje mensaje = GenericMapper.map(mensajeDTO, Mensaje.class);
		mensaje.setChat(chat);
		Mensaje mensajeGuardado = mensajeRepositorio.save(mensaje);
		return GenericMapper.map(mensajeGuardado, MensajeDTO.class);
	}

	@Override
	public List<MensajeDTO> getMensajesChat(Long chatId) {
		return mensajeRepositorio.getMensajesPorChat(chatId).stream()
				.map(mensaje -> GenericMapper.map(mensaje, MensajeDTO.class))
				.collect(Collectors.toList());
	}

}
