package com.igor.eventhubapi.service;

import com.igor.eventhubapi.dto.CompraIngressoDTO;
import com.igor.eventhubapi.dto.IngressoResponseDTO;
import com.igor.eventhubapi.entity.Evento;
import com.igor.eventhubapi.entity.Ingresso;
import com.igor.eventhubapi.entity.Participante;
import com.igor.eventhubapi.exception.EventoLotadoException;
import com.igor.eventhubapi.exception.EventoNaoEncontradoException;
import com.igor.eventhubapi.exception.ParticipanteNaoEncontradoException;
import com.igor.eventhubapi.repository.EventoRepository;
import com.igor.eventhubapi.repository.IngressoRepository;
import com.igor.eventhubapi.repository.ParticipanteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.igor.eventhubapi.service.EventoService.EVENTO_NAO_ENCONTRADO_MENSAGEM;

@Service
public class IngressoService {

    private final EventoRepository eventoRepository;
    private final ParticipanteRepository participanteRepository;
    private final IngressoRepository ingressoRepository;

    public IngressoService(
            EventoRepository eventoRepository,
            ParticipanteRepository participanteRepository,
            IngressoRepository ingressoRepository
    ) {
        this.eventoRepository = eventoRepository;
        this.participanteRepository = participanteRepository;
        this.ingressoRepository = ingressoRepository;
    }

    @Transactional
    public IngressoResponseDTO comprarIngresso(CompraIngressoDTO dto) {

        Evento evento = eventoRepository.findById(dto.eventoId())
                .orElseThrow(() -> new EventoNaoEncontradoException(EVENTO_NAO_ENCONTRADO_MENSAGEM));

        if (evento.getVagasDisponiveis() <= 0) {
            throw new EventoLotadoException("O Evento está lotado");
        }

        Participante participante = participanteRepository.findById(dto.participanteId())
                .orElseThrow(() -> new ParticipanteNaoEncontradoException("Participante não encontrado"));

        evento.setVagasDisponiveis(evento.getVagasDisponiveis() - 1);

        Ingresso ingresso = new Ingresso(
                evento,
                participante,
                LocalDateTime.now()
        );
        Ingresso ingressoCriado = ingressoRepository.save(ingresso);
        return Ingresso.toDTO(ingressoCriado);
    }

    public List<IngressoResponseDTO> listarIngressosPorParticipante(Long participanteId) {

        Participante participante = participanteRepository.findById(participanteId)
                .orElseThrow(() ->
                        new ParticipanteNaoEncontradoException("Participante não encontrado"));

        return ingressoRepository.findByParticipante(participante)
                .stream()
                .map(Ingresso::toDTO)
                .toList();
    }

}

