package com.igor.eventhubapi.dto;

import java.time.LocalDateTime;

public record EventoResponseDTO(
        Long id,
        String nome,
        LocalDateTime data,
        String local,
        Integer capacidade,
        Integer vagasDisponiveis
) {
    public EventoResponseDTO(Long id, String nome, LocalDateTime data, String local, Integer capacidade, Integer vagasDisponiveis) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.capacidade = capacidade;
        this.vagasDisponiveis = vagasDisponiveis;
    }

}

