package com.items.servicio;

import java.util.List;

import com.commons.dto.ProductoDTO;
import com.commons.dto.ItemDTO;

public interface IServicioItem {

	public List<ItemDTO> listarItems();
	
	public ItemDTO verItem(Long id, Integer cantidad);
	
	public ItemDTO crearItem(ProductoDTO producto, Integer cantidad);
	
}
