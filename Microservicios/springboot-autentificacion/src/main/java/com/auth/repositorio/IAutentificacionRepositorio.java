package com.auth.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.commons.entidades.Usuario;

public interface IAutentificacionRepositorio extends JpaRepository <Usuario, Long> {

	//@Query(value = "SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
	//public Usuario obtenerPorEmail(@Param("email") String email);
	
	@Query(value="select * from productos_db.usuarios where email = ? ", nativeQuery= true) 
	 public Usuario encontrarUsuarioPorEmail(String email);
	
	 @Query(value="select email from productos_db.usuarios where nombre = ? ", nativeQuery= true) 
	 public String encontrarEmailporNombre(String nombre);
	
}
