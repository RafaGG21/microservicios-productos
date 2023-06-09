package com.items.servicio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.commons.dto.ItemDTO;
import com.commons.dto.ProductoDTO;
import com.commons.entidades.Item;
import com.commons.mapper.GenericMapper;
import com.items.repositorio.IRepositorioItem;


@Service
public class ServicioItemsImpl implements IServicioItem{

	@Autowired
	private RestTemplate clienteRest;
	
	@Autowired
	private IRepositorioItem repositorioItem;
	
	private String LISTAR_PRODUCTOS_ROUTE = "http://localhost:8080/listar";
	
	private String PRODUCTO_ID_ROUTE = "http://localhost:8080/ver/{id}";
	
	@Override
	public List<ItemDTO> listarItems() {
		List<ProductoDTO> listaProductos = Arrays.asList(clienteRest.getForObject(LISTAR_PRODUCTOS_ROUTE, ProductoDTO[].class));
		return listaProductos.stream()
				.map(producto -> new ItemDTO(producto,1) ).collect(Collectors.toList());
	}

	@Override
	public ItemDTO verItem(Long id, Integer cantidad) {
		ProductoDTO producto = clienteRest.getForObject(PRODUCTO_ID_ROUTE, ProductoDTO.class);
		return new ItemDTO(producto,cantidad);
	}

	@Override
	public ItemDTO crearItem(ProductoDTO producto, Integer cantidad) {
		ItemDTO nuevoItemDTO = new ItemDTO(producto,cantidad);
		Item nuevoItem = GenericMapper.map(nuevoItemDTO, Item.class);
		Item itemGuardado = repositorioItem.save(nuevoItem);
		return GenericMapper.map(itemGuardado, ItemDTO.class);
		
	}


}
