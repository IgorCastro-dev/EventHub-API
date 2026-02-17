package com.igor.eventhubapi.entity;

import com.igor.eventhubapi.dto.EventoRequestDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private LocalDateTime data;

    private String local;

    private Integer capacidade;

    private Integer vagasDisponiveis;

    @OneToMany(mappedBy = "evento")
    private List<Ingresso> ingressos = new ArrayList<>();

    public Evento() {
    }

    public Evento(String nome, LocalDateTime data, String local, Integer capacidade) {
        this.nome = nome;
        this.data = data;
        this.local = local;
        this.capacidade = capacidade;
        this.vagasDisponiveis = capacidade;
    }

    public static Evento fromDTO(final EventoRequestDTO event) {
        return new Evento(
                event.nome(),
                event.data(),
                event.local(),
                event.capacidade()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(id, evento.id) && Objects.equals(nome, evento.nome) && Objects.equals(data, evento.data) && Objects.equals(local, evento.local) && Objects.equals(capacidade, evento.capacidade) && Objects.equals(vagasDisponiveis, evento.vagasDisponiveis) && Objects.equals(ingressos, evento.ingressos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, data, local, capacidade, vagasDisponiveis, ingressos);
    }
}
