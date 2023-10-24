package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.JobApplication;
import com.hipergarzon.workpages.models.JobVacancy;
import com.hipergarzon.workpages.models.Seeker;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface JobApplicationService {

    List<JobApplication> getAllJobApplications();

    JobApplication getIdByJobApplicatio(Long id);

    public List<JobApplication> findByJobVacancyId(Long jobVacancyId);

    public JobApplication saveApply(JobApplication jobApplication);

    public List<JobApplication> getJobVacancyApplications(Long vacancyId) throws ChangeSetPersister.NotFoundException;
}
