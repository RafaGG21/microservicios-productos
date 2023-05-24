package com.commons.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 492874766793324600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;

	@Column(unique = true)
	private String email;
	private String password;
	private String imagen;

	@OneToMany(mappedBy="usuario")
	private List<TokenAutenticar> listaTokens = new ArrayList<>();
	
	@OneToMany(mappedBy="usuario")
	private List<Producto> listaProductos = new ArrayList<>();

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	
	
	public List<TokenAutenticar> getListaTokens() {
		return listaTokens;
	}

	public void setListaTokens(List<TokenAutenticar> listaTokens) {
		this.listaTokens = listaTokens;
	}

	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}

	public Usuario(Long id, String nombre, String email, String password, String imagen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.imagen = imagen;
	}

	public Usuario() {
	}

	public Usuario(String nombre, String email, String password) {
		this.nombre = nombre;
		this.email = email;
		this.password = password;
	}

}
