package com.tokens.servicio;

import com.commons.dto.TokenAutenticarDTO;

public interface ITokenServicio {

	public void guardarToken(TokenAutenticarDTO tokenAutenticarDTO);

	public void eliminarToken(Long id);
}
