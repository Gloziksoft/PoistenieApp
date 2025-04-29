package com.poistenie.app.data.repositories;

import com.poistenie.app.data.entities.InsuranceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for managing InsuranceEntity persistence.
 */
@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {

    /**
     * Finds all insurances linked to a specific insured person.
     */
    List<InsuranceEntity> findByInsuredPersonId(Long insuredPersonId);

    /**
     * Searches insurances by insured person's first or last name (case-insensitive).
     */
    Page<InsuranceEntity> findByInsuredPerson_FirstNameContainingIgnoreCaseOrInsuredPerson_LastNameContainingIgnoreCase(
            String firstName,
            String lastName,
            Pageable pageable
    );
}
