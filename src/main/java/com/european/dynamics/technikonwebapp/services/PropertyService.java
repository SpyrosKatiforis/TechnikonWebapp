package com.european.dynamics.technikonwebapp.services;

import com.european.dynamics.technikonwebapp.model.Property;
import com.european.dynamics.technikonwebapp.repositories.PropertyRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class PropertyService implements Service<Property> {

    @Inject
    private PropertyRepository propertyRepository;

    // Create
    @Override
    public void save(Property property) {
        propertyRepository.save(property);
    }

    // Read
    @Override
    public Optional<Property> getById(Long id) {
        return propertyRepository.findById(id);
    }

    @Override
    public List<Property> getAll() {
        return propertyRepository.findAll();
    }

    // Update
    @Override
    public Property update(Property property) {
        return propertyRepository.update(property);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        propertyRepository.deleteById(id);
    }

    // Additional methods
    public List<Property> findPropertiesByUserId(Long userId) {
        return propertyRepository.findPropertiesByUserId(userId);
    }
}