package com.european.dynamics.technikonwebapp.services;


import com.european.dynamics.technikonwebapp.model.PropertyOwner;
import com.european.dynamics.technikonwebapp.repositories.PropertyOwnerRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class PropertyOwnerService implements Service<PropertyOwner> {

    @Inject
    private PropertyOwnerRepository propertyOwnerRepository;

    // Create
    @Override
    public void save(PropertyOwner owner) {
        propertyOwnerRepository.save(owner);
    }

    // Read
    @Override
    public Optional<PropertyOwner> getById(Long id) {
        return propertyOwnerRepository.findById(id);
    }

    @Override
    public List<PropertyOwner> getAll() {
        return propertyOwnerRepository.findAll();
    }

    // Update
    @Override
    public PropertyOwner update(PropertyOwner owner) {
        return propertyOwnerRepository.update(owner);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        propertyOwnerRepository.deleteById(id);
    }

    // Additional methods
    public Optional<PropertyOwner> findOwnerByUsername(String username) {
        List<PropertyOwner> owners = propertyOwnerRepository.findAllByUsername(username);
        return owners.isEmpty() ? Optional.empty() : Optional.of(owners.get(0));
    }

    public boolean validatePropertyOwnerPassword(String password, PropertyOwner owner) {
        if (password == null || owner == null || owner.getPassword() == null) {
            return false;
        }
        return password.equals(owner.getPassword());
    }
}