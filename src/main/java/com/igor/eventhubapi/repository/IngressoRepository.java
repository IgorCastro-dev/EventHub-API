package com.igor.eventhubapi.repository;

import com.igor.eventhubapi.entity.Ingresso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IngressoRepository extends JpaRepository<Ingresso, Long> {

    List<Ingresso> findByParticipanteId(Long participanteId);
}
