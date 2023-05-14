package com.productos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Producto;

@Repository
public interface IProductoRepository extends CrudRepository<Producto, Long>{

	@Query(value = "SELECT * FROM productos_db.productos WHERE nombre LIKE concat('%', :termino, '%')", nativeQuery = true)
	public List<Producto> buscarProductoPorTermino(@Param("termino") String termino);

}
