package com.commons.dto;

import java.util.Date;
import java.util.List;


public class ChatDTO {


	private Long id;
	private Date fechaCreacion;
	private String comprador;	
	private String vendedor;
	private List<MensajeDTO> mensajes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	
	
	public String getComprador() {
		return comprador;
	}
	public void setComprador(String comprador) {
		this.comprador = comprador;
	}
	public String getVendedor() {
		return vendedor;
	}
	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}
	public List<MensajeDTO> getMensajes() {
		return mensajes;
	}
	public void setMensajes(List<MensajeDTO> mensajes) {
		this.mensajes = mensajes;
	}
	public ChatDTO() {

	}
	
	
}
