package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.JobVacancy;
import com.hipergarzon.workpages.models.Seeker;

import java.util.List;

public interface JobVacancyService {

    List<JobVacancy> getAllJobVacancies();
    JobVacancy getJobVacancyById(Long id);
    List<JobVacancy> getJobVacanciesByBranchOffice(String branchOffice);
    void saveJobVacancy(JobVacancy jobVacancy);
    public JobVacancy addVacanvy(JobVacancy jobVacancy);
}
