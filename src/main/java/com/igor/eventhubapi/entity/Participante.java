package com.igor.eventhubapi.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
public class Participante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToMany(mappedBy = "participante")
    private List<Ingresso> ingressos;

    public Participante() {
    }

    public Participante(Long id, String nome, String email, List<Ingresso> ingressos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ingressos = ingressos;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Participante that = (Participante) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(email, that.email) && Objects.equals(ingressos, that.ingressos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, ingressos);
    }
}

