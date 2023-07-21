package com.hipergarzon.workpages.repositories;


import com.hipergarzon.workpages.models.JobVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface JobVacancyRepository extends JpaRepository<JobVacancy, Long> {

    JobVacancy findByjobVacancyTitle(String jobVacancyTitle);
    List<JobVacancy> findByBranchOffice(String branchOffice);
    Optional<JobVacancy> findByIdAndBranchOffice(Long id, String branchOffice);
}
