package com.igor.eventhubapi.exception;

import org.springframework.http.HttpStatus;

public record Erro(
        HttpStatus status,
        String message
) {
}
