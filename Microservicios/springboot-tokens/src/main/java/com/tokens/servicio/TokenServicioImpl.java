package com.tokens.servicio;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.dto.TokenAutenticarDTO;
import com.commons.entidades.TokenAutenticar;
import com.commons.mapper.GenericMapper;
import com.tokens.repositorio.ITokenRepositorio;

@Service
public class TokenServicioImpl implements ITokenServicio{

	@Autowired
	private ITokenRepositorio tokenRepositorio;
	
	@Override
	public void guardarToken(TokenAutenticarDTO tokenAutenticarDTO) {
		tokenRepositorio.save(GenericMapper.map(tokenAutenticarDTO, TokenAutenticar.class));
		
	}
	
	@Override
	public void eliminarToken(Long id) {
		if (tokenRepositorio.existsById(id)) {
			tokenRepositorio.deleteById(id);
		}

	}

}
