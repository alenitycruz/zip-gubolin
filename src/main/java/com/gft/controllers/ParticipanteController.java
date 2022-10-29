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
import com.gft.entities.Participante;
import com.gft.services.GrupoService;
import com.gft.services.ParticipanteService;

@Controller
@RequestMapping("participante")
public class ParticipanteController {
    @Autowired
    private ParticipanteService participanteService;

    @Autowired
    private GrupoService grupoService;

    @RequestMapping(path="novo")
    public ModelAndView novoParticipante(@RequestParam Long id) throws Exception{

        ModelAndView mv = new ModelAndView("participante/cadastrar.html");
        mv.addObject("participante", new Participante());
        Grupo grupo = grupoService.obterGrupo(id);
        mv.addObject("grupo", grupo);
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.POST, path="novo")
    public ModelAndView salvarParticipante(@RequestParam Long id, @Valid Participante participante, BindingResult bindingResult) throws Exception{

        ModelAndView mv = new ModelAndView("participante/cadastrar.html");

        boolean novo = true;
        Grupo grupo = grupoService.obterGrupo(id);
        mv.addObject("grupo", grupo);
        if(participante.getId_participante() != null){
            novo=false;
        }

        if(bindingResult.hasErrors()){
            mv.addObject("participante", participante);
            return mv;
        }
        Participante participanteSalvo = participanteService.createWithId(participante, id);
        grupo.setParticipantesNaLista(participanteSalvo);
        grupoService.salvarGrupo(grupo);
        if(novo){
            mv.addObject("participante", new Participante());
        }else{
            mv.addObject("participante", participanteSalvo);
        }
        mv.addObject("mensagem", "Participante salvo com sucesso");
        return mv;
    }

    @RequestMapping("/editar")
    public ModelAndView editarParticipante(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("participante/cadastrar.html");
        Participante participante;
        try {
            participante = participanteService.readId(id);
        } catch (Exception e) {
            participante = new Participante();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("participante", participante);
        return mv;
    }

}
