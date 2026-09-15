package com.rafaelvilelacontreira.usuario.infrastructure.repository;

import com.rafaelvilelacontreira.usuario.infrastructure.entity.Usuario;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Verificando se o usuario ja existe
    boolean existsByEmail(String email);


    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);
}
