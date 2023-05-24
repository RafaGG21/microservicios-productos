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

	@Query(value = "SELECT * FROM productos WHERE genero = ?1", nativeQuery = true)
	public List<Producto> buscarProductoPorGenero(String genero);
	
	@Query(value = "SELECT * FROM productos WHERE categoria = ?1", nativeQuery = true)
	public List<Producto> buscarProductoPorCategoria(String categoria);
	
	@Query(value = "SELECT * FROM productos WHERE nombre = ?1", nativeQuery = true)
	public Producto buscarProductoPorNombre(String nombre);
	
	@Query(value = "SELECT * FROM productos WHERE idUsuario = ?1", nativeQuery = true)
	public Producto buscarProductoPorUsuario(Long idUsuario);
	
	 @Query("SELECT p.id, p.nombre, p.precio, p.genero, p.categoria, p.imagen, p.descripcion, u.nombre, u.email, u.imagen FROM Producto p JOIN p.usuario u  ON p.usuario.id = u.id where p.id = :id")
	 public List<Object[]> getProductoYUsuarioPorId(@Param("id") Long id);
	 
	 @Query("SELECT p.id, p.nombre, p.precio, p.genero, p.categoria, p.imagen, p.descripcion, u.nombre, u.email, u.imagen FROM Producto p JOIN p.usuario u  ON p.usuario.id = u.id where p.nombre = :nombre")
	 public List<Object[]> getProductoYUsuarioPorNombre(@Param("nombre") String nombre);
}
