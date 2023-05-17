package com.auth.servicios;

import com.commons.dto.UsuarioDTO;
import com.commons.dto.UsuarioTokenDTO;

public interface IAutentificationService {

	public UsuarioDTO obtenerPorEmailYPassword(String email, String password);
	
	public UsuarioDTO registrarUsuario(UsuarioDTO usuario);
	
	public UsuarioDTO obtenerUsuarioPorEmail(String email);
	
	public UsuarioDTO editarPasswordUsuario(String email, String password);

	public UsuarioDTO editarUsuario(UsuarioDTO usuarioDTO);
	
	public UsuarioTokenDTO obtenerUsuarioPorToken(String token);
}
