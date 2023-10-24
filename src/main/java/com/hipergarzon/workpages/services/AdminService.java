package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.models.UserGeneral;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    public List<Admin> getAllAdmins();
    Admin getAdminById(Long id);
    public Optional<Admin> getOptionalAdmin(Long id);
    Admin findAdminByEmail(String email);
    void saveAdmin(Admin admin);
}
