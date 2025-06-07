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
		List<ProductoDTO> listaProductosDTO = null;
		 
		try {
			listaProductosDTO = new ArrayList<>();
			for (Producto producto : productoRepository.findAll()) {
				listaProductosDTO.add(GenericMapper.map(producto, ProductoDTO.class));
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  listaProductosDTO;
	}

	@Override	
	public ProductoDTO encontrarProductoPorId(Long id) {
		ProductoDTO productoDTO = new ProductoDTO();
		try {
			List<Object[]> producto = productoRepository.getProductoYUsuarioPorId(id);
			for (Object[] row : producto) {
				productoDTO.setId((Long) row[0]);
				productoDTO.setNombre((String) row[1]);
				productoDTO.setPrecio((Double) row[2]);
				productoDTO.setGenero((String) row[3]);
				productoDTO.setCategoria((String) row[4]);
				productoDTO.setImagen((String) row[5]);
				productoDTO.setDescripcion((String) row[6]);
				productoDTO.setNombreUsuario((String) row[7]);
				productoDTO.setEmailUsuario((String) row[8]);
				productoDTO.setImagenUsuario((String) row[9]);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  productoDTO;
	}

	@Override
	public ProductoDTO guardarProducto(ProductoDTO productoDTO) {
		ProductoDTO productoDTORetornar = null;
		try {
			Producto producto = GenericMapper.map(productoDTO, Producto.class);
			Producto productoGuardado = productoRepository.save(producto);
			productoDTORetornar = GenericMapper.map(productoGuardado, ProductoDTO.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productoDTORetornar;
	}

	@Override
	public void eliminarProductoPorId(Long id) {
		try {
			productoRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override	
	public List<ProductoDTO> encontrarProductoPorTermino(String termino) {
		List<ProductoDTO> productosDTO = null;
		try {
			List<Producto> producto = productoRepository.buscarProductoPorTermino(termino);
			if (producto == null) {
				return null;
			} else {
				productosDTO = producto.stream()
						.map(p -> GenericMapper.map(p, ProductoDTO.class))
						.collect(Collectors.toList());
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productosDTO;
	}

	@Override
	public List<ProductoDTO> encontrarProductoPorGenero(String genero) {
		List<ProductoDTO> productosDTO = null;
		try {
			List<Producto> producto = productoRepository.buscarProductoPorGenero(genero);
			if (producto == null) {
				return null;
			} else {
				productosDTO = producto.stream()
						.map(p -> GenericMapper.map(p, ProductoDTO.class))
						.collect(Collectors.toList());

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productosDTO;
	}

	@Override
	public List<ProductoDTO> encontrarProductoPorCategoria(String categoria) {
		List<ProductoDTO> productosDTO = null;
		try {
			List<Producto> productos = productoRepository.buscarProductoPorCategoria(categoria);
			if (productos == null) {
				return null;
			} else {
				productosDTO = productos.stream().map(p -> GenericMapper.map(p, ProductoDTO.class))
						.collect(Collectors.toList());

			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productosDTO;
	}
	
	@Override
	public ProductoDTO encontrarProductoPorNombre(String nombre) {
		ProductoDTO productoDTO = null;
		try {
			List<Object[]> producto = productoRepository.getProductoYUsuarioPorNombre(nombre);
			productoDTO = new ProductoDTO();
			for (Object[] row : producto) {
				productoDTO.setId((Long) row[0]);
				productoDTO.setNombre((String) row[1]);
				productoDTO.setPrecio((Double) row[2]);
				productoDTO.setGenero((String) row[3]);
				productoDTO.setCategoria((String) row[4]);
				productoDTO.setImagen((String) row[5]);
				productoDTO.setDescripcion((String) row[6]);
				productoDTO.setNombreUsuario((String) row[7]);
				productoDTO.setEmailUsuario((String) row[8]);
				productoDTO.setImagenUsuario((String) row[9]);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  productoDTO;
	}

	@Override	
	public List<ProductoDTO> encontrarProductosUsuario(Long idUsuario) {
		List<ProductoDTO> productosDTO = null;
		try {
			List<Producto> producto = productoRepository.buscarProductosPorUsuario(idUsuario);
			if (producto == null) {
				return null;
			} else {
				productosDTO = producto.stream()
						.map(p -> GenericMapper.map(p, ProductoDTO.class))
						.collect(Collectors.toList());
			} 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return productosDTO;
	}
}
