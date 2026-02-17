package com.igor.eventhubapi.repository;

import com.igor.eventhubapi.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepository extends JpaRepository<Evento, Long> {}
