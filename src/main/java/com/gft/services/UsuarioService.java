package com.gft.services;

import com.gft.entities.Usuario;
import com.gft.exceptions.EntradaInvalidaException;
import com.gft.exceptions.UsuarioDuplicadoException;
import com.gft.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    BCryptPasswordEncoder criptografia;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void salvarUsuario(Usuario usuario) throws EntradaInvalidaException, UsuarioDuplicadoException {
        usuario.setSenha(pegarSenhaCriptografada(usuario));
        if(usuarioDuplicado(usuario))
            throw new UsuarioDuplicadoException("E-mail cadastrado para outro usuário");
        usuarioRepository.save(usuario);
    }

    public boolean usuarioDuplicado(Usuario usuario) {
        Optional<Usuario> outroUsuario = usuarioRepository.findByEmail(usuario.getEmail().toUpperCase());
        System.out.println(outroUsuario.get().getEmail());
        if(outroUsuario.isPresent() && usuario.getId() != outroUsuario.get().getId())
            return true;
        return false;
    }

    public String pegarSenhaCriptografada(Usuario usuario) throws EntradaInvalidaException {
        if(usuario.getSenha() == "")
            return new BCryptPasswordEncoder().encode(usuario.getSenha());
        if(usuario.getId() == null)
            throw new EntradaInvalidaException("Senha obrigatórias");
        return usuarioRepository.findById(usuario.getId()).get().getSenha();
    }

    public List<Usuario> listarTodosUsuarios() {

        return usuarioRepository.findAll();

    }

    public Usuario obterUmUsuario(Long id) throws Exception {

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);

        if (usuario.isEmpty()) {
            throw new Exception("Usuario não encontrada");
        }
        return usuario.get();
    }
    public boolean existsUsuario(Long id){
        return  usuarioRepository.existsById(id);
    }

    @Transactional
    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }

}


