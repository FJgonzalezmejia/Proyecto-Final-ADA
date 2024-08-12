package com.sjprogramming.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sjprogramming.restapi.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
