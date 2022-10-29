package com.gft.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gft.entities.Grupo;
import com.gft.services.GrupoService;
import com.gft.services.EventoService;

@Controller
@RequestMapping("grupo")
public class GrupoController {
    @Autowired
    private GrupoService grupoService;
    @Autowired
    private EventoService eventoService;

    @RequestMapping(path="novo")
    public ModelAndView novoGrupo(){

        ModelAndView mv = new ModelAndView("grupo/newgrupo.html");
        mv.addObject("grupo", new Grupo());
        mv.addObject("listarEvento", eventoService.listarTodosOsEventos());
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.POST, path="novo")
    public ModelAndView salvarGrupo(@Valid Grupo grupo, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("grupo/newgrupo.html");

        boolean novo = true;

        if(grupo.getId_grupo() != null){
            novo=false;
        }

        if(bindingResult.hasErrors()){
            mv.addObject("grupo", grupo);
            return mv;
        }
        Grupo linguagemSalva = grupoService.salvarGrupo(grupo);
        if(novo){
            mv.addObject("grupo", new Grupo());
        }else{
            mv.addObject("grupo", linguagemSalva);
        }
        mv.addObject("mensagem", "Grupo salvo com sucesso");
        mv.addObject("listarEvento", eventoService.listarTodosOsEventos());
        return mv;
    }

    @RequestMapping("/visualizar")
    public ModelAndView visualizarGrupo(@RequestParam Long id) throws Exception{
        ModelAndView mv = new ModelAndView("grupo/visualizar.html");
        
        Grupo ling;
        try {
            ling = grupoService.obterGrupo(id);
        } catch (Exception e) {
            ling = new Grupo();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("grupo", ling);
        mv.addObject("listaParticipantes", grupoService.listarTodosOsParticipantesDoGrupo(id));
        return mv;
    }


    @RequestMapping("/editar")
    public ModelAndView editarGrupo(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("grupo/newgrupo.html");
        Grupo ling;
        try {
            ling = grupoService.obterGrupo(id);
        } catch (Exception e) {
            ling = new Grupo();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("grupo", ling);
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirGrupo(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("/evento/grupos.html");
        try {
            grupoService.excluir(id);
            mv.addObject("mensagem", "Grupo excluído com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir grupo"+e.getMessage());
        }
        return mv;
    }
}
