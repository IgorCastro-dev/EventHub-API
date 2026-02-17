package com.igor.eventhubapi.dto;

import java.time.LocalDateTime;

public record IngressoResponseDTO(
        Long ingressoId,
        String nomeEvento,
        LocalDateTime dataEvento,
        String localEvento,
        LocalDateTime dataCompra
) {}
