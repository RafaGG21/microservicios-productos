package com.commons.dto;

import java.io.Serializable;

public class ItemDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6256058777793078587L;
	
	
	private Long id;
	private ProductoDTO producto;
	private Integer cantidad;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public ProductoDTO getProducto() {
		return producto;
	}

	public void setProducto(ProductoDTO producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public ItemDTO(ProductoDTO producto, Integer cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}

	public ItemDTO() {

	}

	public Double getPrecioTotal() {
		return producto.getPrecio() * cantidad.doubleValue();
	}

}
