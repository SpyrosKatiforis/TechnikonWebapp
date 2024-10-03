
package com.european.dynamics.technikonwebapp.repositories;

import com.european.dynamics.technikonwebapp.model.Admin;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.TypedQuery;
import java.util.List;

@ApplicationScoped
public class AdminRepository extends RepositoryImpl<Admin> {

    public AdminRepository() {
        super(Admin.class);
    }

    @Override
    public List<Admin> findAllByUsername(String username) {
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.username = :username", Admin.class);
        query.setParameter("username", username);
        return query.getResultList();
    }

    // Other custom methods are not applicable and will throw UnsupportedOperationException
}