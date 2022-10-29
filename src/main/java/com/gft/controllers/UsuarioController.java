package com.gft.controllers;

import com.gft.entities.Usuario;
import com.gft.exceptions.EntradaInvalidaException;
import com.gft.exceptions.UsuarioDuplicadoException;
import com.gft.repositories.UsuarioRepository;
import com.gft.services.UsuarioService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

	@Autowired
	private BCryptPasswordEncoder criptografia;
    
    @GetMapping
    public ModelAndView search() {
        ModelAndView mv = new ModelAndView("usuario/listar");
        mv.addObject("lista", usuarioRepository.findAll());
        return mv;
    }
    
    @GetMapping("/novo")
    public ModelAndView create(Usuario usuario) {
    	return new ModelAndView("usuario/cadastrar");
    }

	@SneakyThrows
	@PostMapping("/novo")
	public ModelAndView salvarUsuario(@Valid Usuario usuario, BindingResult result,
									  RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return create(usuario);
		}

		String senhaCriptografia = criptografia.encode(usuario.getPassword());
		usuario.setSenha(senhaCriptografia);

		usuarioRepository.save(usuario);
		attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");


		return new ModelAndView("redirect:/usuario");
	}
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable("id") Long id, Usuario usuario, boolean validado) {
    	ModelAndView mv = new ModelAndView("usuario/editar");
    	if(!validado)
    		usuario = usuarioRepository.findById(id).get();
    	mv.addObject("usuario", usuario);
    	return mv;
    }
    
    @PostMapping("/editar")
    public ModelAndView editarUsuario(@Valid Usuario usuario, BindingResult result) {
    	if(result.hasErrors()) {
    		return editar(usuario.getId(), usuario, true);
    	}
    	try {
			usuarioService.salvarUsuario(usuario);
		} catch (EntradaInvalidaException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return editar(usuario.getId(), usuario, true);
		} catch (UsuarioDuplicadoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return editar(usuario.getId(), usuario, true);
		}
    	return new ModelAndView("redirect:/usuario");
    }
    
    @GetMapping("/{id}/excluir")
    public ModelAndView delete(@PathVariable("id") Long id) {
    	usuarioService.deletarUsuario(id);
    	return new ModelAndView("redirect:/usuario");
    }
    
    @GetMapping("/senha")
    public ModelAndView senha() {
    	ModelAndView mv = new ModelAndView("usuario/senha");
    	mv.addObject("usuario", new Usuario());
    	return mv;
    }
    
    @PostMapping("/senha")
    public ModelAndView senha(Usuario usuario, BindingResult result) {
    	Usuario usuarioLogado = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	usuarioLogado.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
    	usuarioRepository.save(usuarioLogado);
    	return new ModelAndView("redirect:/");
    }
}
