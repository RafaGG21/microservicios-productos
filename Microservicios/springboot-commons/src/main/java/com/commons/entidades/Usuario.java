package com.commons.entidades;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 492874766793324600L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String nombre;
	
	@Column(unique = true)
	String email;
	String password;
	String imagen;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="usuario_roles", joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})})
	List<Rol> roles;
	
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
	
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
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