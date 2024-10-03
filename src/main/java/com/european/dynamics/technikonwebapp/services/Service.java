package com.european.dynamics.technikonwebapp.services;

import java.util.List;
import java.util.Optional;

public interface Service<T> {

    // Create
    void save(T entity);

    // Read
    Optional<T> getById(Long id);
    List<T> getAll();

    // Update
    T update(T entity);

    // Delete
    void deleteById(Long id);
}