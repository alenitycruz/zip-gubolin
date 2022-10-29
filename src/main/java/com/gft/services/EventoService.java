package com.gft.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gft.entities.Atividade;
import com.gft.entities.Evento;
import com.gft.entities.Grupo;
import com.gft.entities.Participante;
import com.gft.repositories.EventoRepository;

@Service
public class EventoService {
    @Autowired
    private EventoRepository eventoRepository;

    //Salvar
    public Evento salvarEvento(Evento evento){
        return eventoRepository.save(evento);
    }

    //Listar todos
    public List<Evento> listarTodosOsEventos(){
        return eventoRepository.findAll();
    }

    public List<Participante> listarTodosOsParticipantesDosGruposDoEvento(Long id) throws Exception{
        Evento evento = obterEvento(id);
        List<Participante> listaParticipantes = new ArrayList<Participante>();
        List<Grupo> grupos = evento.getGrupos();
        for (Grupo grupo : grupos) {
            var participantes = grupo.getParticipantes();
            for (Participante participante : participantes) {
                listaParticipantes.add(participante);
            }
        }
        return listaParticipantes;
    }

    public List<Atividade> listarTodosAsAtividadesDoEvento(Long id){
        Optional<Evento> eventos = eventoRepository.findById(id);
        var evento = eventos.get();
        return evento.getAtividades();
    }

    //Listar por Nome
    public List<Evento> listarPorNomeEvento(String nome){
        if(nome!= null){
            return eventoRepository.findByNomeContains(nome);
        }
        return listarTodosOsEventos();
        
    }

    //Obter 
    public Evento obterEvento(Long id) throws Exception{
        Optional<Evento> evento = eventoRepository.findById(id);
        if(evento.isEmpty()){
            throw new Exception("Evento não encontrado");
        }
        return evento.get();
    }

    //Excluir
    public void excluirEvento(Long id) throws Exception{
        //eventoRepository.deleteById(id);
        Evento evento = obterEvento(id);
        evento.setStatus(false);
        eventoRepository.save(evento);
    }

    public List<Grupo> listarGrupos(Long id) throws Exception {
        Evento evento = obterEvento(id);
        return evento.getGrupos();
    }
}
