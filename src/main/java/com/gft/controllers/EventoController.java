package com.gft.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gft.entities.Evento;
import com.gft.entities.Participante;
import com.gft.services.EventoService;

@Controller
@RequestMapping("evento")
public class EventoController {
    @Autowired
    private EventoService eventoService;


    @RequestMapping(path="novo")
    public ModelAndView novoEvento(){

        ModelAndView mv = new ModelAndView("evento/cadastrar.html");
        mv.addObject("evento", new Evento());
        return mv;
    }
    
    @RequestMapping(method=RequestMethod.POST, path="novo")
    public ModelAndView salvarEvento(@Valid Evento evento, BindingResult bindingResult){

        ModelAndView mv = new ModelAndView("evento/cadastrar.html");

        boolean novo = true;

        if(evento.getId_evento() != null){
            novo=false;
        }

        if(bindingResult.hasErrors()){
            mv.addObject("evento", evento);
            return mv;
        }
        Evento linguagemSalva = eventoService.salvarEvento(evento);
        if(novo){
            mv.addObject("evento", new Evento());
        }else{
            mv.addObject("evento", linguagemSalva);
        }
        mv.addObject("mensagem", "Evento salvo com sucesso");
        return mv;
    }

    @RequestMapping("/grupos")
    public ModelAndView grupos(@RequestParam Long id) throws Exception{
        ModelAndView mv = new ModelAndView("evento/grupos.html");
        mv.addObject("listaGrupo", eventoService.listarGrupos(id));
        mv.addObject("evento", eventoService.obterEvento(id));
        return mv;
    }

    @RequestMapping("/presencas")
    public ModelAndView presencas(@RequestParam Long id) throws Exception{
        Evento evento = eventoService.obterEvento(id);
        //var diasEvento = evento.totalDiasDoEvento();
        ModelAndView mv = new ModelAndView("evento/presencas.html");
        mv.addObject("evento", evento);
        List<Participante> listaParticipantes = eventoService.listarTodosOsParticipantesDosGruposDoEvento(id);
        mv.addObject("listaParticipante", listaParticipantes);
        return mv;
    }
    @RequestMapping("/pontuacao")
    public ModelAndView pontuacao(@RequestParam Long id) throws Exception{
        ModelAndView mv = new ModelAndView("evento/pontuacao.html");
        Evento evento = eventoService.obterEvento(id);
        mv.addObject("listaGrupo", evento.getGrupos());
        mv.addObject("evento", evento);
        return mv;
    }
    @RequestMapping("/entregas")
    public ModelAndView entregasAtividades(@RequestParam Long id) throws Exception{
        Evento evento = eventoService.obterEvento(id);
        ModelAndView mv = new ModelAndView("evento/entregas.html");
        mv.addObject("evento", evento);
        List<Participante> listaParticipantes = eventoService.listarTodosOsParticipantesDosGruposDoEvento(id);
        mv.addObject("listaParticipante", listaParticipantes);
        mv.addObject("listaAtividades", evento.getAtividades());
        return mv;
    }

    @RequestMapping(method=RequestMethod.POST, path="entregas")
    public ModelAndView entregas(@RequestParam Long id) throws Exception{
        Evento evento = eventoService.obterEvento(id);
        ModelAndView mv = new ModelAndView("evento/entregas.html");
        mv.addObject("evento", evento);
        List<Participante> listaParticipantes = eventoService.listarTodosOsParticipantesDosGruposDoEvento(id);
        mv.addObject("listaParticipante", listaParticipantes);
        mv.addObject("listaAtividades", evento.getAtividades());
        return mv;
    }

    @RequestMapping
    public ModelAndView listarEvento(String nome){
        ModelAndView mv = new ModelAndView("evento/listar.html");
        mv.addObject("lista", eventoService.listarPorNomeEvento(nome));
        mv.addObject("nome", nome);
        return mv;
    }

    @RequestMapping("/visualizar")
    public ModelAndView visualizarEvento(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("evento/visualizar.html");
        
        Evento ling;
        try {
            ling = eventoService.obterEvento(id);
        } catch (Exception e) {
            ling = new Evento();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("evento", ling);
        mv.addObject("listaAtividade", eventoService.listarTodosAsAtividadesDoEvento(id));
        return mv;
    }


    @RequestMapping("/editar")
    public ModelAndView editarEvento(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("evento/cadastrar.html");
        Evento ling;
        try {
            ling = eventoService.obterEvento(id);
        } catch (Exception e) {
            ling = new Evento();
            mv.addObject("mensagem", e.getMessage());
        }
        mv.addObject("evento", ling);
        return mv;
    }

    @RequestMapping("/excluir")
    public ModelAndView excluirEvento(@RequestParam Long id, RedirectAttributes redirectAttributes){
        ModelAndView mv = new ModelAndView("redirect:/evento");
        try {
            eventoService.excluirEvento(id);
            redirectAttributes.addFlashAttribute("mensagem", "Evento excluído com sucesso");
        } catch (Exception e) {
            mv.addObject("mensagem", "Erro ao excluir evento"+e.getMessage());
        }
        return mv;
    }
}
