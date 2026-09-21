package com.rafaelvilelacontreira.usuario.infrastructure.repository;

import com.rafaelvilelacontreira.usuario.infrastructure.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
}
