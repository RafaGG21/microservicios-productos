package com.commons.dto;


public class MensajeDTO {


	private Long id;
	
	private String contenido;
	
	private String usuarioAutor;

	private Long chat_id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getUsuarioAutor() {
		return usuarioAutor;
	}

	public void setUsuarioAutor(String usuarioAutor) {
		this.usuarioAutor = usuarioAutor;
	}

	public Long getChat_id() {
		return chat_id;
	}

	public void setChat_id(Long chat_id) {
		this.chat_id = chat_id;
	}

}
