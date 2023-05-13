package com.auth.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.repositorio.IAutentificacionRepositorio;
import com.commons.dto.UsuarioDTO;
import com.commons.entidades.Usuario;
import com.commons.mapper.GenericMapper;

@Service
public class AutentificacionServicioImpl implements IAutentificationService {

	@Autowired
	IAutentificacionRepositorio autentificacionRepositorio;

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public UsuarioDTO obtenerPorEmailYPassword(String email, String password) {
		UsuarioDTO usuarioDTO = null;
		try {
			Usuario usuario = autentificacionRepositorio.encontrarUsuarioPorEmail(email);
			if (usuario != null && encoder.matches(password, usuario.getPassword())) {

				usuarioDTO = GenericMapper.map(usuario, UsuarioDTO.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO registrarUsuario(UsuarioDTO usuarioDTO) {
		UsuarioDTO usuarioRegistradoDTO = null;
		String passwordCodificado = encoder.encode(usuarioDTO.getPassword());
		usuarioDTO.setPassword(passwordCodificado);
		Usuario usuario = GenericMapper.map(usuarioDTO, Usuario.class);
		try {
			Usuario usuarioRegistrado = autentificacionRepositorio.save(usuario);
			if (usuarioRegistrado != null) {
				usuarioRegistradoDTO = GenericMapper.map(usuarioRegistrado, UsuarioDTO.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioRegistradoDTO;
	}
	

}
