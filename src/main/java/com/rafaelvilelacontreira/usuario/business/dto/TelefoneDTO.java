package com.rafaelvilelacontreira.usuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TelefoneDTO {
    private String numero;
    private String ddd;
}
