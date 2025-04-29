package com.poistenie.app.data.repositories;

import com.poistenie.app.data.entities.EventEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repository for managing EventEntity persistence.
 */
public interface EventRepository extends JpaRepository<EventEntity, Long> {

    /**
     * Finds all events linked to a specific insurance policy.
     */
    List<EventEntity> findByInsuranceId(Long insuranceId);
}
