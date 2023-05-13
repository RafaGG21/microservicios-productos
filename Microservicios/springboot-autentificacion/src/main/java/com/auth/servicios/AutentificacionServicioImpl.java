package com.auth.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth.repositorio.IAutentificacionRepositorio;
import com.commons.dto.UsuarioDTO;
import com.commons.entidades.Usuario;
import com.commons.mapper.GenericMapper;

@Service
public class AutentificacionServicioImpl  implements IAutentificationService, UserDetailsService  {

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
				//List<GrantedAuthority> roles = usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol.getNombre())).peek(authority -> authority.getAuthority()).collect(Collectors.toList());
				usuarioDTO = GenericMapper.map(usuario, UsuarioDTO.class);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarioDTO;
	}

	@Override
	public UsuarioDTO obtenerEmailPorNombre(String nombre) {
		UsuarioDTO usuarioDTO = null;
		try {
			Usuario usuario = autentificacionRepositorio.encontrarEmailporNombre(nombre);
			if(usuario != null) {
				GenericMapper.map(usuario,UsuarioDTO.class);
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
	

}
