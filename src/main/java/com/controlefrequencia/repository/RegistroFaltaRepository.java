package com.controlefrequencia.repository;

import com.controlefrequencia.entity.RegistroFalta;
import com.controlefrequencia.enums.TipoRegistroFalta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RegistroFaltaRepository
        extends JpaRepository<RegistroFalta, Long> {

    List<RegistroFalta> findByMatricula_IdOrderByDataDesc(
        Long matriculaId
    );

    boolean existsByMatricula_IdAndData(
        Long matriculaId,
        LocalDate data
    );

    @Query("""
        SELECT COALESCE(SUM(r.quantidade), 0)
        FROM RegistroFalta r
        WHERE r.matricula.id = :matriculaId
          AND r.tipo = :tipo
    """)
    Long somarPorMatriculaETipo(
        @Param("matriculaId") Long matriculaId,
        @Param("tipo") TipoRegistroFalta tipo
    );
}