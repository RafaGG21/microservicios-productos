package com.auth.repositorio;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.commons.entidades.Usuario;

@Repository
public interface IAutentificacionRepositorio extends JpaRepository<Usuario, Long> {

	@Query(value = "SELECT * FROM usuarios WHERE email = :email", nativeQuery = true)
	public Usuario obtenerPorEmail(@Param("email") String email);

	@Query(value = "select u from Usuario u where u.email = :email")
	public Usuario encontrarUsuarioPorEmail(String email);

	@Query(value = "select email, nombre from productos_db.usuarios where nombre = ? ", nativeQuery = true)
	public Usuario encontrarEmailporNombre(String nombre);

	@Query(value = "SELECT u.id, u.nombre, u.email, t.id, t.token, t.fecha_creacion FROM usuarios u INNER JOIN tokens_autenticar t ON u.id = t.usuario_id WHERE t.token = :token ORDER BY t.fecha_creacion DESC", nativeQuery = true)
	public List<Object[]> buscarUsuarioMasRecientePorToken(@Param("token") String token, Pageable pageable);
}
