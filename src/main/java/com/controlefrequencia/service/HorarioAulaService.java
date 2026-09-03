package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.HorarioAulaRequestDTO;
import com.controlefrequencia.dto.HorarioAulaResponseDTO;
import com.controlefrequencia.entity.HorarioAula;
import com.controlefrequencia.entity.Matricula;
import com.controlefrequencia.repository.HorarioAulaRepository;
import com.controlefrequencia.repository.MatriculaRepository;

@Service
public class HorarioAulaService {

    private final HorarioAulaRepository horarioRepository;
    private final MatriculaRepository matriculaRepository;

    public HorarioAulaService(
            HorarioAulaRepository horarioRepository,
            MatriculaRepository matriculaRepository) {

        this.horarioRepository = horarioRepository;
        this.matriculaRepository = matriculaRepository;
    }

    @Transactional
    public HorarioAulaResponseDTO cadastrar(
            HorarioAulaRequestDTO dados) {

        Matricula matricula = matriculaRepository
            .findById(dados.getMatriculaId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Matrícula não encontrada"
            ));

        if (!dados.getHoraFim().isAfter(dados.getHoraInicio())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A hora final deve ser posterior à inicial"
            );
        }

        if (horarioRepository
                .existsByMatricula_IdAndDiaSemanaAndHoraInicio(
                    dados.getMatriculaId(),
                    dados.getDiaSemana(),
                    dados.getHoraInicio())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Esse horário já está cadastrado"
            );
        }

        HorarioAula horario = new HorarioAula(
            dados.getDiaSemana(),
            dados.getHoraInicio(),
            dados.getHoraFim(),
            dados.getQuantidadeAulas(),
            matricula
        );

        return converter(horarioRepository.save(horario));
    }

    @Transactional(readOnly = true)
    public List<HorarioAulaResponseDTO> listarPorPeriodo(
            Long periodoLetivoId) {

        return horarioRepository
            .findByMatricula_PeriodoLetivo_IdOrderByDiaSemanaAscHoraInicioAsc(
                periodoLetivoId
            )
            .stream()
            .map(this::converter)
            .toList();
    }

    private HorarioAulaResponseDTO converter(HorarioAula horario) {
        return new HorarioAulaResponseDTO(
            horario.getId(),
            horario.getMatricula().getId(),
            horario.getMatricula().getDisciplina().getNome(),
            horario.getDiaSemana(),
            horario.getHoraInicio(),
            horario.getHoraFim(),
            horario.getQuantidadeAulas()
        );
    }
}