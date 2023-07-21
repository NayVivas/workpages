package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SeekerRepository extends JpaRepository<Seeker, Long> {

    Seeker findUserByEmail (String email);
}
