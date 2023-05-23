package com.chat.servicio;

import com.commons.dto.ChatDTO;
import com.commons.dto.MensajeDTO;

public interface IChatServicio {

	public ChatDTO crearChar(ChatDTO chatDTO);

	public ChatDTO obtenerChatMensajesYUsuarios(String nombreUsuarioComprador, String nombreUsuarioVendedor);
}
