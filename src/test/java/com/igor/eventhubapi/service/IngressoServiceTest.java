package com.igor.eventhubapi.service;

import com.igor.eventhubapi.dto.CompraIngressoDTO;
import com.igor.eventhubapi.entity.Evento;
import com.igor.eventhubapi.entity.Ingresso;
import com.igor.eventhubapi.entity.Participante;
import com.igor.eventhubapi.exception.EventoLotadoException;
import com.igor.eventhubapi.exception.EventoNaoEncontradoException;
import com.igor.eventhubapi.exception.ParticipanteNaoEncontradoException;
import com.igor.eventhubapi.repository.EventoRepository;
import com.igor.eventhubapi.repository.IngressoRepository;
import com.igor.eventhubapi.repository.ParticipanteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class IngressoServiceTest {

    @Mock
    private EventoRepository eventoRepository;

    @Mock
    private ParticipanteRepository participanteRepository;

    @Mock
    private IngressoRepository ingressoRepository;

    @InjectMocks
    private IngressoService ingressoService;

    private Evento evento;
    private Participante participante;

    @BeforeEach
    void setup() {
        evento = new Evento();
        evento.setId(1L);
        evento.setNome("Evento Teste");
        evento.setVagasDisponiveis(5);

        participante = new Participante(1L,"Igor","igor.castro@gmail.com");
    }

    @Test
    void deveComprarIngressoComSucesso() {

        CompraIngressoDTO dto = new CompraIngressoDTO(1L, 1L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(participanteRepository.findById(1L)).thenReturn(Optional.of(participante));
        when(ingressoRepository.save(any(Ingresso.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ingressoService.comprarIngresso(dto);

        assertEquals(4, evento.getVagasDisponiveis());
        verify(ingressoRepository, times(1)).save(any(Ingresso.class));
    }

    @Test
    void deveLancarExcecaoQuandoEventoLotado() {

        evento.setVagasDisponiveis(0);
        CompraIngressoDTO dto = new CompraIngressoDTO(1L, 1L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));

        assertThrows(EventoLotadoException.class,
                () -> ingressoService.comprarIngresso(dto));

        verify(ingressoRepository, never()).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoEventoNaoExiste() {

        CompraIngressoDTO dto = new CompraIngressoDTO(1L, 1L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EventoNaoEncontradoException.class,
                () -> ingressoService.comprarIngresso(dto));
    }

    @Test
    void deveLancarExcecaoQuandoParticipanteNaoExiste() {

        CompraIngressoDTO dto = new CompraIngressoDTO(1L, 1L);

        when(eventoRepository.findById(1L)).thenReturn(Optional.of(evento));
        when(participanteRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ParticipanteNaoEncontradoException.class,
                () -> ingressoService.comprarIngresso(dto));
    }
}
