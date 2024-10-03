package com.european.dynamics.technikonwebapp.repositories;

import com.european.dynamics.technikonwebapp.model.enums.Status;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface Repository<T> {

    // Create
    T save(T entity);

    // Read
    Optional<T> findById(Long id);
    List<T> findAll();

    // Update
    T update(T entity);

    // Delete
    void deleteById(Long id);

    // Custom query methods
    List<T> findByUserId(Long userId);
    List<T> findByDateRange(LocalDateTime startDate, LocalDateTime endDate);
    List<T> findAllByUsername(String username);
    List<T> findPendingRepairs(Status status);
    List<T> findAllByPropertyId(Long propertyId);
    List<T> findPropertiesByUserId(Long userId);
    List<T> findPendingRepairsForId(Status status, Long propertyId);
    List<T> findByOwnerId(Long ownerId);
}