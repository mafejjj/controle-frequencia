package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.MatriculaRequestDTO;
import com.controlefrequencia.dto.MatriculaResponseDTO;
import com.controlefrequencia.entity.Disciplina;
import com.controlefrequencia.entity.Matricula;
import com.controlefrequencia.entity.PeriodoLetivo;
import com.controlefrequencia.repository.DisciplinaRepository;
import com.controlefrequencia.repository.MatriculaRepository;
import com.controlefrequencia.repository.PeriodoLetivoRepository;

@Service
public class MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final PeriodoLetivoRepository periodoRepository;
    private final DisciplinaRepository disciplinaRepository;

    public MatriculaService(
            MatriculaRepository matriculaRepository,
            PeriodoLetivoRepository periodoRepository,
            DisciplinaRepository disciplinaRepository) {

        this.matriculaRepository = matriculaRepository;
        this.periodoRepository = periodoRepository;
        this.disciplinaRepository = disciplinaRepository;
    }

    @Transactional
    public MatriculaResponseDTO cadastrar(MatriculaRequestDTO dados) {
        PeriodoLetivo periodo = periodoRepository
            .findById(dados.getPeriodoLetivoId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Período letivo não encontrado"
            ));

        Disciplina disciplina = disciplinaRepository
            .findById(dados.getDisciplinaId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Disciplina não encontrada"
            ));

        if (!periodo.getUsuario().getId()
                .equals(disciplina.getUsuario().getId())) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A disciplina e o período pertencem a usuários diferentes"
            );
        }

        if (matriculaRepository
                .existsByPeriodoLetivo_IdAndDisciplina_Id(
                    periodo.getId(),
                    disciplina.getId())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Essa disciplina já está matriculada nesse período"
            );
        }

        Matricula matricula = new Matricula(
            dados.getLimiteFaltas(),
            dados.getStatus(),
            periodo,
            disciplina
        );

        return converter(matriculaRepository.save(matricula));
    }

    @Transactional(readOnly = true)
    public List<MatriculaResponseDTO> listarPorPeriodo(
            Long periodoLetivoId) {

        return matriculaRepository
            .findByPeriodoLetivo_IdOrderByDisciplina_NomeAsc(
                periodoLetivoId
            )
            .stream()
            .map(this::converter)
            .toList();
    }

    private MatriculaResponseDTO converter(Matricula matricula) {
        return new MatriculaResponseDTO(
            matricula.getId(),
            matricula.getPeriodoLetivo().getId(),
            matricula.getPeriodoLetivo().getNome(),
            matricula.getDisciplina().getId(),
            matricula.getDisciplina().getNome(),
            matricula.getLimiteFaltas(),
            matricula.getStatus()
        );
    }
}