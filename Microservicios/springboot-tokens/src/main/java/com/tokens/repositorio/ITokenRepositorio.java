package com.tokens.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.commons.entidades.TokenAutenticar;

@Repository
public interface ITokenRepositorio extends JpaRepository<TokenAutenticar, Long>{

}
