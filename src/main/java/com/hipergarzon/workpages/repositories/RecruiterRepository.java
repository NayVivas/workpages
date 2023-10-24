package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
    Recruiter findRecruiterByEmail (String email);
}