package com.commons.utils;


import java.util.Date;
import java.util.UUID;

import com.commons.dto.TokenAutenticarDTO;



public class TokenUtils {


    public static TokenAutenticarDTO generateToken(Long id) {
    	TokenAutenticarDTO token = new TokenAutenticarDTO();
    	token.setIdUsuario(id);
    	Date fecha = new Date();
    	token.setFechaCreacion(fecha);
    	token.setToken(UUID.randomUUID().toString());
    	return token;
    }

    public static boolean isTokenValid(Date fechaToken) {
    	long diferenciaMilisegundos = fechaToken.getTime() - new Date().getTime();
    	long diferenciaDias = diferenciaMilisegundos / 86400000;

    	if (diferenciaDias >= 1) {
    		return false;
    	} else {
    	   return true;
    	}
  
    }

}
