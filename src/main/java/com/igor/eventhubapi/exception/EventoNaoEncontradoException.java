package com.igor.eventhubapi.exception;

public class EventoNaoEncontradoException extends RuntimeException {
    public EventoNaoEncontradoException(String message) {
        super(message);
    }
}
