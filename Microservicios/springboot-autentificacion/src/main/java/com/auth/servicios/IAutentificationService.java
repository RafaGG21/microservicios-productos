package com.auth.servicios;

import com.commons.dto.UsuarioDTO;

public interface IAutentificationService {

	public UsuarioDTO obtenerPorEmailYPassword(String email, String password);
	
	public UsuarioDTO registrarUsuario(UsuarioDTO usuario);
	
	public UsuarioDTO obtenerEmailPorNombre(String email);
}
