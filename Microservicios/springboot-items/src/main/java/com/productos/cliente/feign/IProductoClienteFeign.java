package com.productos.cliente.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.commons.dto.ProductoDTO;

@FeignClient(name = "servicio-productos", url="localhost:8080")
@RequestMapping("/productos")
@Component
public interface IProductoClienteFeign {

	@GetMapping("/listar")
	public List<ProductoDTO> listarProductos();

	@GetMapping("ver/{id}")
	public ProductoDTO encontrarProductoPorId(@PathVariable Long id);
	
	@PostMapping("/crear")
	public ProductoDTO guardarProducto(@RequestBody ProductoDTO producto);

	@DeleteMapping("/{id}")
	public void eliminarProductoPorId(@PathVariable Long id);
	
}
