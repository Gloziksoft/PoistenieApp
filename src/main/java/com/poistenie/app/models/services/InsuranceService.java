package com.poistenie.app.models.services;

import com.poistenie.app.models.dto.EventDTO;
import com.poistenie.app.models.dto.InsuranceDTO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service interface for managing insurance policies.
 */
public interface InsuranceService {

    InsuranceDTO findById(Long id);

    Page<InsuranceDTO> findAll(Pageable pageable);

    Page<InsuranceDTO> search(String keyword, Pageable pageable);

    List<InsuranceDTO> findByInsuredPersonId(Long insuredPersonId);

    InsuranceDTO create(InsuranceDTO dto);

    InsuranceDTO update(Long id, InsuranceDTO dto);

    void delete(Long id);

    void deleteEventsWithInvalidDescriptions();
}
