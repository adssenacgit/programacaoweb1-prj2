package com.senac.projeto2.repository;

import com.senac.projeto2.entity.Categoria;
import com.senac.projeto2.entity.Participante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Integer> {
    @Query("SELECT p FROM Participante p WHERE p.status>=0")
    List<Participante> buscarRegistros();

    @Query("SELECT p FROM Participante p WHERE p.status>=0 AND p.id=:id")
    Participante buscarRegistroPorId(@Param("id") Integer id);
}
