package com.controlefrequencia.repository;

import com.controlefrequencia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByTelefone(String telefone);

    Optional<Usuario> findByTelefone(String telefone);
}