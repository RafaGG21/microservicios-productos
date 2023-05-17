package com.auth.servicios;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.repositorio.IAutentificacionRepositorio;
import com.commons.dto.UsuarioDTO;
import com.commons.dto.UsuarioTokenDTO;
import com.commons.entidades.Usuario;
import com.commons.mapper.GenericMapper;
import java.time.LocalDateTime;

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
	public UsuarioDTO obtenerUsuarioPorEmail(String email) {
		UsuarioDTO usuarioDTO = null;
		try {
			Usuario usuario = autentificacionRepositorio.encontrarUsuarioPorEmail(email);
			if (usuario != null) {
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
			UsuarioDTO usuarioEnBBDD = obtenerUsuarioPorEmail(usuario.getEmail());
			if (usuarioEnBBDD == null) {
				Usuario usuarioRegistrado = autentificacionRepositorio.save(usuario);
				if (usuarioRegistrado != null) {
					usuarioRegistradoDTO = GenericMapper.map(usuarioRegistrado, UsuarioDTO.class);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioRegistradoDTO;
	}

	@Override
	public UsuarioDTO editarPasswordUsuario(String email, String password) {
		Usuario usuarioBBDD = autentificacionRepositorio.encontrarUsuarioPorEmail(email);
		usuarioBBDD.setPassword(encoder.encode(password));
		Usuario usuario = autentificacionRepositorio.save(usuarioBBDD);
		return GenericMapper.map(usuario, UsuarioDTO.class);
	}
	
	@Override
	public UsuarioDTO editarUsuario(UsuarioDTO usuarioDTO) {
		Usuario usuarioBBDD = autentificacionRepositorio.encontrarUsuarioPorEmail(usuarioDTO.getEmail());
		if(usuarioBBDD != null) {
			Usuario usuarioEditado = GenericMapper.map(usuarioDTO, Usuario.class);
			Usuario usuarioGuardar = autentificacionRepositorio.save(usuarioEditado);
			return GenericMapper.map(usuarioGuardar, UsuarioDTO.class);
			
		}
		return null;
	}

	@Override
	public UsuarioTokenDTO obtenerUsuarioPorToken(String token) {
		UsuarioTokenDTO usuarioToken = new UsuarioTokenDTO();
		Pageable pageable = PageRequest.of(0, 1);
		List<Object[]> result = autentificacionRepositorio.buscarUsuarioMasRecientePorToken(token, pageable);
		for (Object[] tab : result) {
			usuarioToken.setIdUsuario((Long) tab[0]);
			usuarioToken.setNombre((String) tab[1]);
			usuarioToken.setEmail((String) tab[2]);
			usuarioToken.setIdToken((Long) tab[3]);
			usuarioToken.setToken((String) tab[4]);
			usuarioToken.setFechaCreacion((Date) tab[5]);
		}

		return usuarioToken;
	}
	

}
