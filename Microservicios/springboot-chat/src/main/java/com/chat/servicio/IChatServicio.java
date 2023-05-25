package com.chat.servicio;

import java.util.List;

import com.commons.dto.ChatDTO;
import com.commons.dto.MensajeDTO;

public interface IChatServicio {

	public ChatDTO crearChar(ChatDTO chatDTO);

	public ChatDTO obtenerChatMensajesYUsuarios(String nombreUsuarioComprador, String nombreUsuarioVendedor);

	public List<ChatDTO> obtenerChatVendedorMensajesYUsuarios(String nombre_usuario_vendedor);
}
