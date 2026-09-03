package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.BimestreRequestDTO;
import com.controlefrequencia.dto.BimestreResponseDTO;
import com.controlefrequencia.entity.Bimestre;
import com.controlefrequencia.entity.PeriodoLetivo;
import com.controlefrequencia.repository.BimestreRepository;
import com.controlefrequencia.repository.PeriodoLetivoRepository;

@Service
public class BimestreService {

    private final BimestreRepository bimestreRepository;
    private final PeriodoLetivoRepository periodoRepository;

    public BimestreService(
            BimestreRepository bimestreRepository,
            PeriodoLetivoRepository periodoRepository) {

        this.bimestreRepository = bimestreRepository;
        this.periodoRepository = periodoRepository;
    }

    @Transactional
    public BimestreResponseDTO cadastrar(BimestreRequestDTO dados) {
        PeriodoLetivo periodo = periodoRepository
            .findById(dados.getPeriodoLetivoId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Período letivo não encontrado"
            ));

        if (dados.getDataFim().isBefore(dados.getDataInicio())) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "A data final não pode ser anterior à inicial"
            );
        }

        if (dados.getDataInicio().isBefore(periodo.getDataInicio())
                || dados.getDataFim().isAfter(periodo.getDataFim())) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "As datas do bimestre devem estar dentro do período letivo"
            );
        }

        if (bimestreRepository.existsByPeriodoLetivo_IdAndNumero(
                dados.getPeriodoLetivoId(),
                dados.getNumero())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Esse bimestre já está cadastrado"
            );
        }

        Bimestre bimestre = new Bimestre(
            dados.getNumero(),
            dados.getDataInicio(),
            dados.getDataFim(),
            periodo
        );

        return converterParaDTO(
            bimestreRepository.save(bimestre)
        );
    }

    @Transactional(readOnly = true)
    public List<BimestreResponseDTO> listarPorPeriodo(
            Long periodoLetivoId) {

        if (!periodoRepository.existsById(periodoLetivoId)) {
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Período letivo não encontrado"
            );
        }

        return bimestreRepository
            .findByPeriodoLetivo_IdOrderByNumeroAsc(periodoLetivoId)
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    private BimestreResponseDTO converterParaDTO(
            Bimestre bimestre) {

        return new BimestreResponseDTO(
            bimestre.getId(),
            bimestre.getPeriodoLetivo().getId(),
            bimestre.getNumero(),
            bimestre.getDataInicio(),
            bimestre.getDataFim()
        );
    }
}