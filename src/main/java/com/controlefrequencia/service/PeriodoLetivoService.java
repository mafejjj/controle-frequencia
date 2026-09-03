package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.PeriodoLetivoRequestDTO;
import com.controlefrequencia.dto.PeriodoLetivoResponseDTO;
import com.controlefrequencia.entity.PeriodoLetivo;
import com.controlefrequencia.entity.Usuario;
import com.controlefrequencia.enums.StatusPeriodo;
import com.controlefrequencia.repository.PeriodoLetivoRepository;
import com.controlefrequencia.repository.UsuarioRepository;

@Service
public class PeriodoLetivoService {

    private final PeriodoLetivoRepository periodoRepository;
    private final UsuarioRepository usuarioRepository;

    public PeriodoLetivoService(
            PeriodoLetivoRepository periodoRepository,
            UsuarioRepository usuarioRepository) {

        this.periodoRepository = periodoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public PeriodoLetivoResponseDTO cadastrar(
            PeriodoLetivoRequestDTO dados) {

        Usuario usuario = usuarioRepository.findById(dados.getUsuarioId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
            ));

        if (dados.getDataFim().isBefore(dados.getDataInicio())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A data final não pode ser anterior à data inicial"
            );
        }

        if (periodoRepository.existsByUsuario_IdAndNomeIgnoreCase(
                dados.getUsuarioId(),
                dados.getNome())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Esse período já está cadastrado"
            );
        }

        if (dados.getStatus() == StatusPeriodo.ATIVO
                && periodoRepository.existsByUsuario_IdAndStatus(
                    dados.getUsuarioId(),
                    StatusPeriodo.ATIVO)) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "O usuário já possui um período ativo"
            );
        }

        PeriodoLetivo periodo = new PeriodoLetivo(
            dados.getNome(),
            dados.getNumeroSemestre(),
            dados.getDataInicio(),
            dados.getDataFim(),
            dados.getStatus(),
            usuario
        );

        return converterParaDTO(periodoRepository.save(periodo));
    }

    @Transactional(readOnly = true)
    public List<PeriodoLetivoResponseDTO> listarPorUsuario(
            Long usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
            );
        }

        return periodoRepository
            .findByUsuario_IdOrderByDataInicioDesc(usuarioId)
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    private PeriodoLetivoResponseDTO converterParaDTO(
            PeriodoLetivo periodo) {

        return new PeriodoLetivoResponseDTO(
            periodo.getId(),
            periodo.getUsuario().getId(),
            periodo.getNome(),
            periodo.getNumeroSemestre(),
            periodo.getDataInicio(),
            periodo.getDataFim(),
            periodo.getStatus()
        );
    }
}