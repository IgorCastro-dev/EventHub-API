package com.igor.eventhubapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventoNaoEncontradoException.class)
    public ResponseEntity<Erro> handleEventoNaoEncontrado(
            EventoNaoEncontradoException ex) {

        Erro erro = new Erro(HttpStatus.NOT_FOUND,ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(ParticipanteNaoEncontradoException.class)
    public ResponseEntity<Erro> handleParticipanteNaoEncontradoException(
            ParticipanteNaoEncontradoException ex) {

        Erro erro = new Erro(HttpStatus.NOT_FOUND,ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }

    @ExceptionHandler(EventoLotadoException.class)
    public ResponseEntity<Erro> handleEventoLotadoException(
            EventoLotadoException ex) {

        Erro erro = new Erro(HttpStatus.CONFLICT,ex.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Erro> handleValidationException(
            MethodArgumentNotValidException ex) {

        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        Erro erro = new Erro(HttpStatus.BAD_REQUEST, mensagem);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}