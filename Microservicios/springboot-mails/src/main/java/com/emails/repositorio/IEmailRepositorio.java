package com.emails.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.commons.entidades.Email;

@Repository
public interface IEmailRepositorio extends JpaRepository<Email, Long>{

	@Query(value="select * from productos_db.emails where tipo = ? ", nativeQuery= true) 
	public Email buscarEmailPorTipo(String tipo);
}
