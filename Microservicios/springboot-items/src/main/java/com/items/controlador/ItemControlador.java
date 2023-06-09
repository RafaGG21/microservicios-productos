package com.items.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.commons.dto.ItemDTO;
import com.items.servicio.IServicioItem;

@RestController
public class ItemControlador {

	@Autowired
	private IServicioItem itemService;
	
	@GetMapping("/listar")
	public List<ItemDTO> listarItems(){
		return itemService.listarItems();
	}

	@GetMapping("/ver/{id}/{cantidad}")
	public ItemDTO verItem(@PathVariable Long id, @PathVariable Integer cantidad){
		return itemService.verItem(id, cantidad);
	}

}
