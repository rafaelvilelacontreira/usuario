package com.rafaelvilelacontreira.usuario.business;

//TO DO:
//1. Criar metodo de salvar usuario


import com.rafaelvilelacontreira.usuario.business.converter.UsuarioConverter;
import com.rafaelvilelacontreira.usuario.business.dto.UsuarioDTO;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Usuario;
import com.rafaelvilelacontreira.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}
