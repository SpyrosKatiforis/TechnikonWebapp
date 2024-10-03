package com.european.dynamics.technikonwebapp.repositories;


import com.european.dynamics.technikonwebapp.model.PropertyOwner;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class PropertyOwnerRepository extends RepositoryImpl<PropertyOwner> {

    public PropertyOwnerRepository() {
        super(PropertyOwner.class);
    }

    @Override
    public List<PropertyOwner> findByUserId(Long userId) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery(
            "SELECT po FROM PropertyOwner po WHERE po.ownerId = :ownerId", PropertyOwner.class);
        query.setParameter("ownerId", userId);
        return query.getResultList();
    }

    @Override
    public List<PropertyOwner> findAllByUsername(String username) {
        TypedQuery<PropertyOwner> query = entityManager.createQuery(
            "SELECT po FROM PropertyOwner po WHERE po.username = :username", PropertyOwner.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    // Other custom methods are not applicable and will throw UnsupportedOperationException
}