package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.UserGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface UserGeneralRepository extends JpaRepository<UserGeneral, Long> {
    UserGeneral findUserByEmail(String email);
}
