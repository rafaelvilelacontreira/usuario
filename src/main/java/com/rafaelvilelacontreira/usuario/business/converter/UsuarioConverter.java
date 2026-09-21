package com.rafaelvilelacontreira.usuario.business.converter;


import com.rafaelvilelacontreira.usuario.business.dto.EnderecoDTO;
import com.rafaelvilelacontreira.usuario.business.dto.TelefoneDTO;
import com.rafaelvilelacontreira.usuario.business.dto.UsuarioDTO;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Endereco;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Telefone;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioConverter {

    // =========================
    // DTO → ENTITY
    // =========================

    public Usuario paraUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .endereco(paraEndereco(usuarioDTO.getEndereco()))
                .telefone(paraTelefone(usuarioDTO.getTelefone()))
                .build();
    }

    public List<Endereco> paraEndereco(List<EnderecoDTO> enderecoDTO) {
        return enderecoDTO.stream()
                .map(this::paraEndereco)
                .toList();
    }

    public Endereco paraEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();
    }

    public List<Telefone> paraTelefone(List<TelefoneDTO> telefoneDTO) {
        return telefoneDTO.stream()
                .map(this::paraTelefone)
                .toList();
    }

    public Telefone paraTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }


    // =========================
    // ENTITY → DTO
    // =========================

    public UsuarioDTO paraUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .endereco(paraEnderecoDTO(usuario.getEndereco()))
                .telefone(paraTelefoneDTO(usuario.getTelefone()))
                .build();
    }

    public List<EnderecoDTO> paraEnderecoDTO(List<Endereco> endereco) {
        return endereco.stream()
                .map(this::paraEnderecoDTO)
                .toList();
    }

    public EnderecoDTO paraEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .cidade(endereco.getCidade())
                .estado(endereco.getEstado())
                .cep(endereco.getCep())
                .build();
    }

    public List<TelefoneDTO> paraTelefoneDTO(List<Telefone> telefone) {
        return telefone.stream()
                .map(this::paraTelefoneDTO)
                .toList();
    }

    public TelefoneDTO paraTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }
}
