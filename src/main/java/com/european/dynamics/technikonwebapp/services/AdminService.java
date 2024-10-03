package com.european.dynamics.technikonwebapp.services;


import com.european.dynamics.technikonwebapp.model.Admin;
import com.european.dynamics.technikonwebapp.model.PropertyRepair;
import com.european.dynamics.technikonwebapp.model.enums.Status;
import com.european.dynamics.technikonwebapp.repositories.AdminRepository;
import com.european.dynamics.technikonwebapp.repositories.PropertyRepairRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Transactional
public class AdminService implements Service<Admin> {

    @Inject
    private AdminRepository adminRepository;

    @Inject
    private PropertyRepairRepository propertyRepairRepository;

    // Create
    @Override
    public void save(Admin admin) {
        adminRepository.save(admin);
    }

    // Read
    @Override
    public Optional<Admin> getById(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public List<Admin> getAll() {
        return adminRepository.findAll();
    }

    // Update
    @Override
    public Admin update(Admin admin) {
        return adminRepository.update(admin);
    }

    // Delete
    @Override
    public void deleteById(Long id) {
        adminRepository.deleteById(id);
    }

    // Additional methods
    public Optional<Admin> findAdminByUsername(String username) {
        List<Admin> admins = adminRepository.findAllByUsername(username);
        return admins.isEmpty() ? Optional.empty() : Optional.of(admins.get(0));
    }

    public boolean validateAdminPassword(String password, Admin admin) {
        if (password == null || admin == null || admin.getPassword() == null) {
            return false;
        }
        return password.equals(admin.getPassword());
    }

    public List<PropertyRepair> findPendingRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.PENDING);
    }

    public List<PropertyRepair> findScheduledRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.IN_PROGRESS);
    }

    public List<PropertyRepair> findCompletedRepairs() {
        return propertyRepairRepository.findPendingRepairs(Status.COMPLETED);
    }
}