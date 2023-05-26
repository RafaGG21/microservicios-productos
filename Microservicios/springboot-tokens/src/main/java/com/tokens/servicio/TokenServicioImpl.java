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
		try {
			tokenRepositorio.save(GenericMapper.map(tokenAutenticarDTO, TokenAutenticar.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void eliminarToken(Long id) {
		try {
			if (tokenRepositorio.existsById(id)) {
				tokenRepositorio.deleteById(id);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
