package com.auth.servicios;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auth.repositorio.IAutentificacionRepositorio;
import com.commons.dto.UsuarioDTO;
import com.commons.entidades.Usuario;


@Service
public class RestablecerPasswordImp {

	@Autowired
	IAutentificacionRepositorio autentificarRepositorio;
	
	public void restablecerContraseña(UsuarioDTO usuarioDTO) throws Exception {
        Usuario usuario = autentificarRepositorio.encontrarUsuarioPorEmail(usuarioDTO.getEmail());
        if (usuario != null) {
        	Random random = new Random();
            int randomNumber = random.nextInt(1000000);
           // usuario.setPasswordRestablecer(Integer.toString(randomNumber));

        } else {
            throw new Exception("Usuario no encontrado");
        }
    }
}
