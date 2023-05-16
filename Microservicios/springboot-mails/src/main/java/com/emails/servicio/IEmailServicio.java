package com.emails.servicio;

import com.commons.dto.UsuarioDTO;

public interface IEmailServicio {

	public boolean enviarEmailRegistro(UsuarioDTO usuarioDTO);

	public boolean enviarEmailRestablecerPassword(UsuarioDTO usuarioDTO);
}
