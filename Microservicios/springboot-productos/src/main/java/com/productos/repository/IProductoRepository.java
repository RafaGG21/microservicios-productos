package com.productos.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Producto;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long>{

}
