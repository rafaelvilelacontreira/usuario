package com.rafaelvilelacontreira.usuario.infrastructure.repository;

import com.rafaelvilelacontreira.usuario.infrastructure.entity.Telefone;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
