package com.senac.projeto2.repository;

import com.senac.projeto2.entity.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Integer> {

    @Query("SELECT i FROM Inscricao i WHERE i.status >=0")
    List<Inscricao> listarInscricoes();

    @Query("SELECT i FROM Inscricao i WHERE i.status >=0 AND i.id=:id")
    Inscricao obterInscricaoPorId(@Param("id") Integer id);

    @Modifying
    @Transactional
    @Query("UPDATE Inscricao i SET i.status=-1 WHERE i.id=:id")
    int apagadoLogico(@Param("id") Integer id);
}
