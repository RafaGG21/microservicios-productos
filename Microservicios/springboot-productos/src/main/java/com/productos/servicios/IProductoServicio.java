package com.productos.servicios;

import java.util.List;

import com.commons.dto.ProductoDTO;



public interface IProductoServicio {

	public List<ProductoDTO> listarProductos();

	public ProductoDTO encontrarProductoPorId(Long id);

	public ProductoDTO guardarProducto(ProductoDTO producto);

	public void eliminarProductoPorId(Long id);

	public List<ProductoDTO> encontrarProductoPorTermino(String termino);
	
	public List<ProductoDTO> encontrarProductoPorGenero(String genero);
	
	public List<ProductoDTO> encontrarProductoPorCategoria(String categoria);

	public ProductoDTO encontrarProductoPorNombre(String nombre);
}
