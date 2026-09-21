package com.rafaelvilelacontreira.usuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsuarioDTO {
    private String nome;
    private String email;
    private String senha;
    private Long id;
    private List<EnderecoDTO> endereco;
    private List<TelefoneDTO> telefone;

}
