package com.productos.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.ProductoDTO;
import com.commons.dto.ResponseDTO;
import com.productos.servicios.IProductoServicio;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductoControlador {

	@Autowired
	private IProductoServicio productoServicio;
	
	@GetMapping("/listar")
	public List<ProductoDTO> listarProductos() {
		return productoServicio.listarProductos();
	}

	@GetMapping("ver/{id}")
	public ProductoDTO encontrarProductoPorId(@PathVariable Long id) {
		return productoServicio.encontrarProductoPorId(id);
	}
	
	@GetMapping("ver/nombre/{nombre}")
	public ProductoDTO encontrarProductoPorNombre(@PathVariable String nombre) {
		return productoServicio.encontrarProductoPorNombre(nombre);
	}

	@PostMapping("/crear")
	public ProductoDTO guardarProducto(@RequestBody ProductoDTO producto) {
		return productoServicio.guardarProducto(producto);
	}

	@DeleteMapping("/{id}")
	public void eliminarProductoPorId(@PathVariable Long id) {
		productoServicio.eliminarProductoPorId(id);
	}
	
	@GetMapping("/buscar")
	public ResponseEntity<List<ProductoDTO>> encontrarProductoPorTermno(@RequestParam String termino) {
		List <ProductoDTO> producto = productoServicio.encontrarProductoPorTermino(termino);
		if(producto.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(producto);
		}
	}
	
	@GetMapping("/por-genero/{genero}")
    public ResponseEntity<List<ProductoDTO>> getProductosPorGenero(@PathVariable String genero) {
		List <ProductoDTO> productos =  productoServicio.encontrarProductoPorGenero(genero);
        
        if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(productos);
		}
    }

	@GetMapping("/por-categoria/{categoria}")
    public ResponseEntity<List<ProductoDTO>> getProductosPorCategoria(@PathVariable String categoria) {
		List <ProductoDTO> productos =  productoServicio.encontrarProductoPorCategoria(categoria);
        
        if(productos.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok().body(productos);
		}
    }
	
	@GetMapping("/por-usuario/{idUsuario}")
	public ResponseEntity<List<ProductoDTO>> getProductosPorUsuario(@PathVariable Long idUsuario) {
	    List<ProductoDTO> productos = productoServicio.encontrarProductosUsuario(idUsuario);
	    if(productos.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok(productos);
	    }
	}

}
