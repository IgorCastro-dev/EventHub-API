package com.igor.eventhubapi.service;

import com.igor.eventhubapi.dto.EventoRequestDTO;
import com.igor.eventhubapi.dto.EventoResponseDTO;
import com.igor.eventhubapi.entity.Evento;
import com.igor.eventhubapi.exception.EventoNaoEncontradoException;
import com.igor.eventhubapi.repository.EventoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    protected static final String EVENTO_NAO_ENCONTRADO_MENSAGEM = "Evento não encontrado";

    public EventoService(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    public EventoResponseDTO criar(EventoRequestDTO dto) {
        Evento evento = Evento.fromDTO(dto);
        Evento salvo = eventoRepository.save(evento);
        return Evento.toDTO(salvo);
    }

    public List<EventoResponseDTO> listar() {
        return eventoRepository.findAll()
                .stream()
                .map(Evento::toDTO)
                .toList();
    }


    public EventoResponseDTO buscarPorId(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNaoEncontradoException(EVENTO_NAO_ENCONTRADO_MENSAGEM));


        return Evento.toDTO(evento);
    }

    @Transactional
    public EventoResponseDTO atualizar(Long id, EventoRequestDTO dto) {

        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new EventoNaoEncontradoException(EVENTO_NAO_ENCONTRADO_MENSAGEM));

        int ingressosVendidos =
                evento.getCapacidade() - evento.getVagasDisponiveis();

        if (dto.capacidade() < ingressosVendidos) {
            throw new IllegalArgumentException(
                    "Nova capacidade não pode ser menor que ingressos já vendidos"
            );
        }

        evento.setNome(dto.nome());
        evento.setData(dto.data());
        evento.setLocal(dto.local());
        evento.setCapacidade(dto.capacidade());
        evento.setVagasDisponiveis(dto.capacidade() - ingressosVendidos);

        return Evento.toDTO(evento);
    }

    public void deletar(Long id) {

        if (!eventoRepository.existsById(id)) {
            throw new EventoNaoEncontradoException(EVENTO_NAO_ENCONTRADO_MENSAGEM);
        }

        eventoRepository.deleteById(id);
    }

}

