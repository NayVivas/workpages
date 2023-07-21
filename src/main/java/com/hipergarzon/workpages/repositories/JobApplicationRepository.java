package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.JobApplication;
import com.hipergarzon.workpages.models.JobVacancy;
import com.hipergarzon.workpages.models.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    List<JobApplication> findByJobVacancy(JobVacancy jobVacancy);
    Optional<JobApplication> findByUserIdAndVacancyId(Long userId, Long vacancyId);

    List<JobApplication> findByJobVacancyId(Long jobVacancyId);
}
