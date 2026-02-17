package com.igor.eventhubapi.controller;

import com.igor.eventhubapi.dto.CompraIngressoDTO;
import com.igor.eventhubapi.dto.EventoResponseDTO;
import com.igor.eventhubapi.dto.IngressoResponseDTO;
import com.igor.eventhubapi.service.IngressoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ingressos")
public class IngressoController {

    private final IngressoService ingressoService;

    public IngressoController(IngressoService ingressoService) {
        this.ingressoService = ingressoService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<IngressoResponseDTO> comprar(@RequestBody @Valid CompraIngressoDTO dto) {
        IngressoResponseDTO response = ingressoService.comprarIngresso(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/historico/{participanteId}")
    public ResponseEntity<List<IngressoResponseDTO>> listarPorParticipante(
            @PathVariable Long participanteId) {
        return ResponseEntity.ok(ingressoService.listarIngressosPorParticipante(participanteId));
    }
}
