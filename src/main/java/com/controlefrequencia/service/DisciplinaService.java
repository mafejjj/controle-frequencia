package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.DisciplinaRequestDTO;
import com.controlefrequencia.dto.DisciplinaResponseDTO;
import com.controlefrequencia.entity.Disciplina;
import com.controlefrequencia.entity.Usuario;
import com.controlefrequencia.repository.DisciplinaRepository;
import com.controlefrequencia.repository.UsuarioRepository;

@Service
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final UsuarioRepository usuarioRepository;

    public DisciplinaService(
            DisciplinaRepository disciplinaRepository,
            UsuarioRepository usuarioRepository) {

        this.disciplinaRepository = disciplinaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public DisciplinaResponseDTO cadastrar(
            DisciplinaRequestDTO dados) {

        Usuario usuario = usuarioRepository
            .findById(dados.getUsuarioId())
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
            ));

        if (disciplinaRepository.existsByUsuario_IdAndNomeIgnoreCase(
                dados.getUsuarioId(),
                dados.getNome())) {

            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Essa disciplina já está cadastrada"
            );
        }

        Disciplina disciplina = new Disciplina(
            dados.getNome(),
            dados.getCodigo(),
            usuario
        );

        return converter(
            disciplinaRepository.save(disciplina)
        );
    }

    @Transactional(readOnly = true)
    public List<DisciplinaResponseDTO> listarPorUsuario(
            Long usuarioId) {

        return disciplinaRepository
            .findByUsuario_IdOrderByNomeAsc(usuarioId)
            .stream()
            .map(this::converter)
            .toList();
    }

    private DisciplinaResponseDTO converter(Disciplina disciplina) {
        return new DisciplinaResponseDTO(
            disciplina.getId(),
            disciplina.getUsuario().getId(),
            disciplina.getNome(),
            disciplina.getCodigo()
        );
    }
}