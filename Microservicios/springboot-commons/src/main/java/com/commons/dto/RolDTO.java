package com.commons.dto;

import java.io.Serializable;

public class RolDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8985501060690734307L;
	
	
	Long id;
	String nombre;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
