package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.repositories.AdminRepository;
import com.hipergarzon.workpages.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminImplements implements AdminService {

    @Autowired
    AdminRepository adminRepository;

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).get();
    }

    @Override
    public Optional<Admin> getOptionalAdmin(Long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminRepository.findAdminByEmail(email);
    }

    @Override
    public void saveAdmin(Admin admin) {
        adminRepository.save(admin);
    }
}
