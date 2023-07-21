package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.Audits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface AuditsRepository extends JpaRepository<Audits, Long> {
    List<Audits> findByUserGeneralId(Long userGeneralId);

}
