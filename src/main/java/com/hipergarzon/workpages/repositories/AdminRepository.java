package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findAdminByEmail (String email);
}
