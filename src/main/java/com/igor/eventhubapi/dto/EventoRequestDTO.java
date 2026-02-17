package com.igor.eventhubapi.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record EventoRequestDTO(

        @NotBlank(message = "Nome não pode ser vazio")
        String nome,

        @Future(message = "Data deve estar no futuro")
        LocalDateTime data,

        String local,

        @Min(1)
        Integer capacidade
) {}
