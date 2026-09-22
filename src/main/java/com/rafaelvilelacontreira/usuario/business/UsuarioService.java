package com.rafaelvilelacontreira.usuario.business;

//TO DO:
//1. Criar metodo de salvar usuario

import com.rafaelvilelacontreira.usuario.business.converter.UsuarioConverter;
import com.rafaelvilelacontreira.usuario.business.dto.UsuarioDTO;
import com.rafaelvilelacontreira.usuario.infrastructure.entity.Usuario;
import com.rafaelvilelacontreira.usuario.infrastructure.exceptions.ConflictException;
import com.rafaelvilelacontreira.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.rafaelvilelacontreira.usuario.infrastructure.repository.UsuarioRepository;
import com.rafaelvilelacontreira.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final JwtUtil jwtUtil;


    public UsuarioDTO salvarUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(bCryptPasswordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    //esse metodo e responsavel por verificar se o email ja existe e se nao existir ele cria uma exception
    public void emailExiste(String email) {
        try{
            boolean existe = verificaEmailExistente(email);
            if(existe){
                throw new ConflictException("Email já cadastrado." + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email já cadastrado." + e.getCause());
        }
    }

    //metodo que  chama nosso email na repository
    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    @Transactional(readOnly = true)
    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Email não encontrado " + email));
        return usuarioConverter.paraUsuarioDTO(usuario); // conversão acontece DENTRO da transação
    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token,UsuarioDTO dto){
        //Aqui buscamos o email do usuario através do token (tirar a obrigatoriedade do email)
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //Criptografia de senha
        dto.setSenha(dto.getSenha() == null ? null : bCryptPasswordEncoder.encode(dto.getSenha()));

        //Busca os dados do usuário no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(()->
                new ResourceNotFoundException("Email não localizado: "));
        //Mesclou os dados que recebemos na requisição do DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

        //Salvou os daods do usuário convertido e depois pegou o retorno e converteu para UsuarioDTO
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }
}

