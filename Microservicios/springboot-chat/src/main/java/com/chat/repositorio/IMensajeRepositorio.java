package com.chat.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Mensaje;

@Repository
public interface IMensajeRepositorio extends JpaRepository<Mensaje, Long>{

	@Query("SELECT m FROM Mensaje m WHERE m.chat.id = :idChat")
	public List<Mensaje> getMensajesPorChat(@Param("idChat") Long idChat);

}
