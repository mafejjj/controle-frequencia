package com.controlefrequencia.service;

import com.controlefrequencia.dto.RegistroFaltaRequestDTO;
import com.controlefrequencia.dto.RegistroFaltaResponseDTO;
import com.controlefrequencia.entity.HorarioAula;
import com.controlefrequencia.entity.Matricula;
import com.controlefrequencia.entity.RegistroFalta;
import com.controlefrequencia.repository.HorarioAulaRepository;
import com.controlefrequencia.repository.MatriculaRepository;
import com.controlefrequencia.repository.RegistroFaltaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RegistroFaltaService {

    private final RegistroFaltaRepository faltaRepository;
    private final MatriculaRepository matriculaRepository;
    private final HorarioAulaRepository horarioRepository;

    public RegistroFaltaService(
            RegistroFaltaRepository faltaRepository,
            MatriculaRepository matriculaRepository,
            HorarioAulaRepository horarioRepository) {

        this.faltaRepository = faltaRepository;
        this.matriculaRepository = matriculaRepository;
        this.horarioRepository = horarioRepository;
    }

    @Transactional
    public RegistroFaltaResponseDTO cadastrar(
            RegistroFaltaRequestDTO dados) {

        Matricula matricula = matriculaRepository
            .findById(dados.getMatriculaId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Matrícula não encontrada"
            ));

        if (dados.getData().isBefore(
                matricula.getPeriodoLetivo().getDataInicio())
                || dados.getData().isAfter(
                    matricula.getPeriodoLetivo().getDataFim())) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A data está fora do período letivo"
            );
        }

        if (faltaRepository.existsByMatricula_IdAndData(
                dados.getMatriculaId(),
                dados.getData())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um registro nessa data"
            );
        }

        List<HorarioAula> horarios =
            horarioRepository.findByMatricula_IdAndDiaSemana(
                dados.getMatriculaId(),
                dados.getData().getDayOfWeek()
            );

        int quantidade = horarios.stream()
            .mapToInt(HorarioAula::getQuantidadeAulas)
            .sum();

        if (quantidade == 0) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A disciplina não possui aula nessa data"
            );
        }

        RegistroFalta registro = new RegistroFalta(
            dados.getData(),
            quantidade,
            dados.getTipo(),
            dados.getObservacao(),
            matricula
        );

        return converter(faltaRepository.save(registro));
    }

    @Transactional(readOnly = true)
    public List<RegistroFaltaResponseDTO> listarPorMatricula(
            Long matriculaId) {

        return faltaRepository
            .findByMatricula_IdOrderByDataDesc(matriculaId)
            .stream()
            .map(this::converter)
            .toList();
    }

    private RegistroFaltaResponseDTO converter(
            RegistroFalta registro) {

        return new RegistroFaltaResponseDTO(
            registro.getId(),
            registro.getMatricula().getId(),
            registro.getMatricula().getDisciplina().getNome(),
            registro.getData(),
            registro.getQuantidade(),
            registro.getTipo(),
            registro.getObservacao(),
            registro.getRegistradoEm()
        );
    }
}