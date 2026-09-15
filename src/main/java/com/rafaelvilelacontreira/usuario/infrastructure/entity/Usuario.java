package com.rafaelvilelacontreira.usuario.infrastructure.entity;
import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.Collection;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity //para indicar que é uma tabela do banco de dados
@Table(name = "usuario")
@Builder
public class Usuario implements UserDetails {

    @Id //identificador único
    @GeneratedValue(strategy = GenerationType.IDENTITY) //vai gerar nosso id automaticamente
    private Long id;
    @Column(name = "nome", length = 100)
    private String nome;
    @Column(name = "email", length = 100)
    private String email;
    @Column(name = "senha")
    private String senha;
    @OneToMany(cascade = CascadeType.ALL) //no momento que a gente excluir o usuário, vai excluir automaticamente o email
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Endereco> endereco;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private List<Telefone> telefone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    //VAMOS LOGAR COM EMAIL E SENHA

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
