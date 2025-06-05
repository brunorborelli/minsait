package com.minsait.teste.usuarioApi.repository;

import com.minsait.teste.usuarioApi.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT u.* from usuario u where u.id = :usuarioId", nativeQuery = true)
    Optional<Usuario> buscarUsuarioAtivoPorId(@Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT u.* from usuario u", nativeQuery = true)
    List<Usuario> buscarUsuariosAtivos();

}
