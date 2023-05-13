package com.items.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Item;

@Repository
public interface IRepositorioItem extends CrudRepository<Item, Long>{

}
