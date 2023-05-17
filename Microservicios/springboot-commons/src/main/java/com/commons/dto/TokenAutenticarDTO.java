package com.commons.dto;

import java.util.Date;

public class TokenAutenticarDTO {


	private Long id;

	private String token;

	private Date fechaCreacion;

	private Long idUsuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date localDateTime) {
		this.fechaCreacion = localDateTime;
	}

	

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public TokenAutenticarDTO(String token, Date fechaCreacion, UsuarioDTO usuarioDTO) {
		this.token = token;
		this.fechaCreacion = fechaCreacion;
	}
	
	public TokenAutenticarDTO() {
	}
	
}
