package com.controlefrequencia.repository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.controlefrequencia.entity.HorarioAula;

public interface HorarioAulaRepository
        extends JpaRepository<HorarioAula, Long> {

    List<HorarioAula>
        findByMatricula_PeriodoLetivo_IdOrderByDiaSemanaAscHoraInicioAsc(
            Long periodoLetivoId
        );

    List<HorarioAula> findByMatricula_Id(Long matriculaId);

    List<HorarioAula>
        findByMatricula_PeriodoLetivo_IdAndDiaSemana(
            Long periodoLetivoId,
            DayOfWeek diaSemana
        );

    List<HorarioAula> findByMatricula_IdAndDiaSemana(
        Long matriculaId,
        DayOfWeek diaSemana
    );

    boolean existsByMatricula_IdAndDiaSemanaAndHoraInicio(
        Long matriculaId,
        DayOfWeek diaSemana,
        LocalTime horaInicio
    );
}