package com.igor.eventhubapi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ingresso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Evento evento;

    @ManyToOne
    private Participante participante;

    private LocalDateTime dataCompra;

    public Ingresso() {
    }

    public Ingresso(Evento evento, Participante participante, LocalDateTime dataCompra) {
        this.evento = evento;
        this.participante = participante;
        this.dataCompra = dataCompra;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Objects.equals(id, ingresso.id) && Objects.equals(evento, ingresso.evento) && Objects.equals(participante, ingresso.participante) && Objects.equals(dataCompra, ingresso.dataCompra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, evento, participante, dataCompra);
    }
}

