package com.gft.controllers;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.entities.Atividade;
import com.gft.entities.Evento;
import com.gft.services.AtividadeService;
import com.gft.services.EventoService;

@Controller
@RequestMapping("atividade")
public class AtividadeController {
    @Autowired
    private AtividadeService atividadeService;

	@Autowired
	private EventoService eventoService;

    @RequestMapping(path="novo")
    public ModelAndView novaAtividade(@RequestParam Long id) throws Exception{

        ModelAndView mv = new ModelAndView("atividade/cadastrar.html");
        mv.addObject("atividade", new Atividade());
        Evento evento = eventoService.obterEvento(id);
        mv.addObject("evento", evento);
		mv.addObject("listarEvento", eventoService.listarTodosOsEventos());
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.POST, path="novo")
    public ModelAndView salvarAtividade(@RequestParam Long id, @Valid Atividade atividade, BindingResult bindingResult) throws Exception{

        ModelAndView mv = new ModelAndView("atividade/cadastrar.html");

        boolean novo = true;
        Evento evento = eventoService.obterEvento(id);
        mv.addObject("evento", evento);
        if(atividade.getId_atividade() != null){
            novo=false;
        }

        if(bindingResult.hasErrors()){
            mv.addObject("atividade", atividade);
            return mv;
        }
        Atividade linguagemSalva = atividadeService.create(atividade);
        evento.setAtividadeNaLista(atividade);
        eventoService.salvarEvento(evento);
        if(novo){
            mv.addObject("atividade", new Atividade());
        }else{
            mv.addObject("atividade", linguagemSalva);
        }
        mv.addObject("mensagem", "Atividade salvo com sucesso");
        return mv;
    }

    @RequestMapping("/editar")
    public ModelAndView editarAtividade(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("atividade/cadastrar.html");
        Atividade atividade;
        try {
            atividade = atividadeService.readId(id);
        } catch (Exception e) {
            atividade = new Atividade();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("atividade", atividade);
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirAtividade(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/evento");
        try {
            atividadeService.delete(id);
            redirectAttributes.addFlashAttribute("mensagem", "Atividade excluído com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir atividade"+e.getMessage());
        }
        return mv;
    }
}
