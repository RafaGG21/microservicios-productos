package com.chat.servicio;

import java.util.List;

import com.commons.dto.MensajeDTO;

public interface IMensajeServicio {

	public MensajeDTO crearMensaje(MensajeDTO mensajeDTO);
	
	public List<MensajeDTO> getMensajesChat(Long chatId);
}
