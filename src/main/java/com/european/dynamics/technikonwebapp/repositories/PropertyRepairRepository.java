
package com.european.dynamics.technikonwebapp.repositories;


import com.european.dynamics.technikonwebapp.model.PropertyRepair;
import com.european.dynamics.technikonwebapp.model.enums.Status;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class PropertyRepairRepository extends RepositoryImpl<PropertyRepair> {

    public PropertyRepairRepository() {
        super(PropertyRepair.class);
    }

    @Override
    public List<PropertyRepair> findByOwnerId(Long ownerId) {
    TypedQuery<PropertyRepair> query = entityManager.createQuery(
        "SELECT pr FROM PropertyRepair pr JOIN pr.property p WHERE p.owner.ownerId = :ownerId", PropertyRepair.class);
    query.setParameter("ownerId", ownerId);
    return query.getResultList();
    }


    @Override
    public List<PropertyRepair> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        TypedQuery<PropertyRepair> query = entityManager.createQuery(
            "SELECT pr FROM PropertyRepair pr WHERE pr.dateSubmitted BETWEEN :startDate AND :endDate",
            PropertyRepair.class);
        query.setParameter("startDate", startDate);
        query.setParameter("endDate", endDate);
        return query.getResultList();
    }

    
    public List<PropertyRepair> findByStatus(Status status) {
    TypedQuery<PropertyRepair> query = entityManager.createQuery(
        "SELECT pr FROM PropertyRepair pr WHERE pr.status = :status", PropertyRepair.class);
    query.setParameter("status", status);
    return query.getResultList();
}

    @Override
    public List<PropertyRepair> findPendingRepairsForId(Status status, Long propertyId) {
        TypedQuery<PropertyRepair> query = entityManager.createQuery(
            "SELECT pr FROM PropertyRepair pr WHERE pr.status = :status AND pr.property.propertyId = :propertyId",
            PropertyRepair.class);
        query.setParameter("status", status);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    @Override
    public List<PropertyRepair> findAllByPropertyId(Long propertyId) {
        TypedQuery<PropertyRepair> query = entityManager.createQuery(
            "SELECT pr FROM PropertyRepair pr WHERE pr.property.propertyId = :propertyId", PropertyRepair.class);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    // Other custom methods can be implemented as needed
}