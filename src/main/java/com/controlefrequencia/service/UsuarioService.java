package com.controlefrequencia.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.controlefrequencia.dto.UsuarioRequestDTO;
import com.controlefrequencia.dto.UsuarioResponseDTO;
import com.controlefrequencia.entity.Usuario;
import com.controlefrequencia.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrar(UsuarioRequestDTO dados) {
        if (usuarioRepository.existsByTelefone(dados.getTelefone())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Já existe um usuário com esse telefone"
            );
        }

        Usuario usuario = new Usuario(
            dados.getNome(),
            dados.getTelefone()
        );

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaDTO(usuarioSalvo);
    }

    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> listar() {
        return usuarioRepository.findAll()
            .stream()
            .map(this::converterParaDTO)
            .toList();
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Usuário não encontrado"
            ));

        return converterParaDTO(usuario);
    }

    private UsuarioResponseDTO converterParaDTO(Usuario usuario) {
        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getTelefone()
        );
    }
}