package com.auth.servicios;

import com.commons.dto.UsuarioDTO;
import com.commons.dto.UsuarioTokenDTO;

public interface IAutentificationService {

	public UsuarioDTO obtenerPorEmailYPassword(String email, String password);
	
	public UsuarioDTO registrarUsuario(UsuarioDTO usuario);
	
	public UsuarioDTO obtenerUsuarioPorEmail(String email);
	
	public UsuarioDTO editarPasswordUsuario(UsuarioDTO usuarioDT);
	
	public UsuarioTokenDTO obtenerUsuarioPorToken(String token);

	public UsuarioDTO editarUsuario(Long id, UsuarioDTO usuarioDTO);

	
}
