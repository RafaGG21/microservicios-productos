package com.chat.repositorio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Chat;

@Repository
public interface IChatRepositorio extends JpaRepository<Chat, Long>{

	@Query("SELECT c FROM Chat c WHERE c.comprador = :comprador AND c.vendedor = :vendedor")
    public Chat chatByCompradorAndVendedor(@Param("comprador") String comprador, @Param("vendedor") String vendedor);

	@Query("SELECT c FROM Chat c WHERE c.vendedor = :vendedor")
    public List<Chat> chatByVendedor(@Param("vendedor") String vendedor);
}
