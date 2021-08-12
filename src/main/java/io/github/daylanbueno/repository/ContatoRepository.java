package io.github.daylanbueno.repository;


import io.github.daylanbueno.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContatoRepository extends JpaRepository<Contato, Integer> {

    @Query(value = "SELECT c FROM Contato c WHERE c.nome like %:nome%")
    List<Contato> obterPorNome(@Param("nome") String nome);
}
