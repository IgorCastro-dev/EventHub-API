package com.igor.eventhubapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventoNaoEncontradoException.class)
    public ResponseEntity<Erro> handleEventoNaoEncontrado(
            EventoNaoEncontradoException ex) {

        Erro erro = new Erro(HttpStatus.NOT_FOUND,ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
}