package com.european.dynamics.technikonwebapp.repositories;

import com.european.dynamics.technikonwebapp.model.enums.Status;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Transactional
public abstract class RepositoryImpl<T> implements Repository<T> {

    @PersistenceContext
    protected EntityManager entityManager;

    protected Class<T> entityClass;

    public RepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    // Create
    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    // Read
    @Override
    public Optional<T> findById(Long id) {
        T entity = entityManager.find(entityClass, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public List<T> findAll() {
        TypedQuery<T> query = entityManager.createQuery(
            "SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
        return query.getResultList();
    }

    // Update
    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        T entity = entityManager.find(entityClass, id);
        if (entity != null) {
            entityManager.remove(entity);
        }
    }

    // Custom query methods
    @Override
    public List<T> findByUserId(Long userId) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findByDateRange(LocalDateTime startDate, LocalDateTime endDate) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findAllByUsername(String username) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findPendingRepairs(Status status) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findAllByPropertyId(Long propertyId) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findPropertiesByUserId(Long userId) {
        throw new UnsupportedOperationException("Method not implemented");
    }

    @Override
    public List<T> findPendingRepairsForId(Status status, Long propertyId) {
        throw new UnsupportedOperationException("Method not implemented");
    }
    
    @Override
    public List<T> findByOwnerId(Long ownerId) {
        throw new UnsupportedOperationException("Method not implemented");
    }
}