package com.gft.entities;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
public class Usuario implements Serializable, UserDetails {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80, nullable = false)
    @NotBlank(message = "Email obrigatório")
    @Length(min = 5, max = 80, message = "Email deve conter entre 5 e 80 caracteres")
    private String email;

    @Column(length = 80)
    private String senha;

    @Column(length = 80, nullable = false)
    @Length(min = 5, max = 80, message = "Nome deve conter entre 5 e 80 caracteres")
    private String nome;

    @Column(columnDefinition = "char(4)", nullable = false)
    @NotBlank(message = "Quatro letras obrigatório")
    private String quatroLetras;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "acessos", joinColumns = @JoinColumn(
            name = "usuario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "perfil_id", referencedColumnName = "id"))
    private List<Perfil> perfis;

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getQuatroLetras() {
        return quatroLetras;
    }

    public void setQuatroLetras(String quatroLetras) {
        this.quatroLetras = quatroLetras;
    }

    public List<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(List<Perfil> perfis) {
        this.perfis = perfis;
    }
}

