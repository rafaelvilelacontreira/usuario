package com.rafaelvilelacontreira.usuario.business.dto;

import com.rafaelvilelacontreira.usuario.infrastructure.entity.Endereco;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Telefone;
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
    private long id;
    private List<EnderecoDTO> endereco;
    private List<TelefoneDTO> telefone;

}
