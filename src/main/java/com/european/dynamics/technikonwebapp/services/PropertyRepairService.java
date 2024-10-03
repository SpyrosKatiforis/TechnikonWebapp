package com.european.dynamics.technikonwebapp.services;


import com.european.dynamics.technikonwebapp.model.PropertyRepair;
import com.european.dynamics.technikonwebapp.model.enums.Status;
import com.european.dynamics.technikonwebapp.repositories.PropertyRepairRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class PropertyRepairService implements Service<PropertyRepair> {

    @Inject
    private PropertyRepairRepository propertyRepairRepository;

    // Create
    @Override
    public void save(PropertyRepair propertyRepair) {
        propertyRepairRepository.save(propertyRepair);
    }

    // Read
    @Override
    public Optional<PropertyRepair> getById(Long id) {
        return propertyRepairRepository.findById(id);
    }

    @Override
    public List<PropertyRepair> getAll() {
        return propertyRepairRepository.findAll();
    }

    // Update
    @Override
    public PropertyRepair update(PropertyRepair propertyRepair) {
        return propertyRepairRepository.update(propertyRepair);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        propertyRepairRepository.deleteById(id);
    }

    // Additional methods
    public List<PropertyRepair> findByStatus(Status status) {
        return propertyRepairRepository.findByStatus(status);
    }

    public List<PropertyRepair> findAllByPropertyId(Long propertyId) {
        return propertyRepairRepository.findAllByPropertyId(propertyId);
    }

    public List<PropertyRepair> findPendingRepairsForId(Status status, Long propertyId) {
        return propertyRepairRepository.findPendingRepairsForId(status, propertyId);
    }

    public List<PropertyRepair> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        return propertyRepairRepository.findByDateRange(startDate, endDate);
    }

    public List<PropertyRepair> findByUserId(Long userId) {
        return propertyRepairRepository.findByUserId(userId);
    }
    
    public List<PropertyRepair> findByOwnerId(Long ownerId) {
    return propertyRepairRepository.findByOwnerId(ownerId);
    }

}