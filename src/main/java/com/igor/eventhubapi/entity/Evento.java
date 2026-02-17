package com.igor.eventhubapi.entity;

import com.igor.eventhubapi.dto.EventoRequestDTO;
import com.igor.eventhubapi.dto.EventoResponseDTO;
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

    public static EventoResponseDTO toDTO(final Evento entity) {
        return new EventoResponseDTO(
                entity.id,
                entity.nome,
                entity.data,
                entity.local,
                entity.capacidade,
                entity.vagasDisponiveis
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getVagasDisponiveis() {
        return vagasDisponiveis;
    }

    public void setVagasDisponiveis(Integer vagasDisponiveis) {
        this.vagasDisponiveis = vagasDisponiveis;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<Ingresso> ingressos) {
        this.ingressos = ingressos;
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
