package com.poistenie.app.models.services;

import com.poistenie.app.data.entities.InsuredPersonEntity;
import com.poistenie.app.models.dto.InsuredPersonDTO;
import com.poistenie.app.data.repositories.InsuredPersonRepository;
import com.poistenie.app.models.dto.mappers.InsuredPersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * Service implementation for managing insured persons.
 */
@Service
public class InsuredPersonServiceImpl implements InsuredPersonService {

    @Autowired
    private InsuredPersonRepository repository;

    @Autowired
    private InsuredPersonMapper mapper;

    /**
     * Searches insured persons by name (first name or last name).
     */
    @Override
    public Page<InsuredPersonDTO> searchByName(String name, Pageable pageable) {
        Page<InsuredPersonEntity> result = repository
                .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name, pageable);
        return result.map(mapper::toDTO);
    }

    @Override
    public Page<InsuredPersonDTO> findByEmail(String email, Pageable pageable) {
        return repository.findByEmail(email, pageable)
                .map(mapper::toDTO);
    }


    /**
     * Retrieves all insured persons with pagination support.
     */
    @Override
    public Page<InsuredPersonDTO> getAllPaginated(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDTO);
    }

    /**
     * Finds an insured person by ID.
     * Throws NoSuchElementException if not found.
     */
    @Override
    public InsuredPersonDTO findById(Long id) {
        InsuredPersonEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Poistená osoba s ID " + id + " neexistuje."));
        return mapper.toDTO(entity);
    }

    /**
     * Retrieves all insured persons without pagination.
     */
    @Override
    public List<InsuredPersonDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Creates a new insured person.
     */
    @Override
    public InsuredPersonDTO create(InsuredPersonDTO dto) {
        InsuredPersonEntity entity = mapper.toEntity(dto);
        return mapper.toDTO(repository.save(entity));
    }

    /**
     * Updates an existing insured person by ID.
     * Throws NoSuchElementException if not found.
     */
    @Override
    public InsuredPersonDTO update(Long id, InsuredPersonDTO dto) {
        InsuredPersonEntity existingEntity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Poistená osoba s ID " + id + " neexistuje."));

        InsuredPersonEntity updatedEntity = mapper.toEntity(dto);
        updatedEntity.setId(id); // preserve ID

        return mapper.toDTO(repository.save(updatedEntity));
    }

    /**
     * Deletes an insured person by ID.
     * Throws NoSuchElementException if not found.
     */
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Poistená osoba s ID " + id + " neexistuje.");
        }
        repository.deleteById(id);
    }
}
