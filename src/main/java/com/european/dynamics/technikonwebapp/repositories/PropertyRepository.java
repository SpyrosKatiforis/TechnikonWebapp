package com.european.dynamics.technikonwebapp.repositories;

import com.european.dynamics.technikonwebapp.model.Property;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PropertyRepository extends RepositoryImpl<Property> {

    public PropertyRepository() {
        super(Property.class);
    }

    @Override
    public List<Property> findPropertiesByUserId(Long userId) {
        TypedQuery<Property> query = entityManager.createQuery(
            "SELECT p FROM Property p WHERE p.owner.ownerId = :ownerId", Property.class);
        query.setParameter("ownerId", userId);
        return query.getResultList();
    }

    @Override
    public List<Property> findAllByPropertyId(Long propertyId) {
        TypedQuery<Property> query = entityManager.createQuery(
            "SELECT p FROM Property p WHERE p.propertyId = :propertyId", Property.class);
        query.setParameter("propertyId", propertyId);
        return query.getResultList();
    }

    // Other custom methods are not applicable and will throw UnsupportedOperationException
}