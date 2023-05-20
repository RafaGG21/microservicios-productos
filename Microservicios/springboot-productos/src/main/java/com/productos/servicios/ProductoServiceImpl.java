package com.productos.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.commons.dto.ProductoDTO;
import com.commons.entidades.Producto;
import com.commons.mapper.GenericMapper;
import com.productos.repository.IProductoRepository;

@Service
public class ProductoServiceImpl implements IProductoServicio{

	@Autowired
	private IProductoRepository productoRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<ProductoDTO> listarProductos() {
		List<ProductoDTO> listaProductosDTO = new ArrayList<>();
		for (Producto producto : productoRepository.findAll()) {
			listaProductosDTO.add(GenericMapper.map(producto,ProductoDTO.class ));
		}
		return  listaProductosDTO;
	}

	@Override	
	public ProductoDTO encontrarProductoPorId(Long id) {
		Producto producto = productoRepository.findById(id).orElse(null);
		if (producto == null) {
			return null;
		} else {
			return GenericMapper.map(producto, ProductoDTO.class);
		}
	}

	@Override
	public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
		Producto producto = GenericMapper.map(productoDTO, Producto.class);
		Producto productoGuardado = productoRepository.save(producto);
		return GenericMapper.map(productoGuardado, ProductoDTO.class);
	}

	@Override
	public void eliminarProductoPorId(Long id) {
		productoRepository.deleteById(id);
	}
	
	@Override	
	public List<ProductoDTO> encontrarProductoPorTermino(String termino) {
		List<Producto> producto = productoRepository.buscarProductoPorTermino(termino);
		if (producto == null) {
			return null;
		} else {
			return producto.stream().map(p -> GenericMapper.map(p, ProductoDTO.class))
					.collect(Collectors.toList());

		}
	}

	@Override
	public List<ProductoDTO> encontrarProductoPorGenero(String genero) {
		List<Producto> producto = productoRepository.buscarProductoPorGenero(genero);
		if (producto == null) {
			return null;
		} else {
			return producto.stream().map(p -> GenericMapper.map(p, ProductoDTO.class))
					.collect(Collectors.toList());

		}
	}

	@Override
	public List<ProductoDTO> encontrarProductoPorCategoria(String categoria) {
		List<Producto> productos = productoRepository.buscarProductoPorCategoria(categoria);
		if (productos == null) {
			return null;
		} else {
			return productos.stream().map(p -> GenericMapper.map(p, ProductoDTO.class))
					.collect(Collectors.toList());

		}
	}


}
