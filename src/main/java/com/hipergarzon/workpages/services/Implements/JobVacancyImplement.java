package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.JobApplication;
import com.hipergarzon.workpages.models.JobVacancy;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.repositories.JobApplicationRepository;
import com.hipergarzon.workpages.repositories.JobVacancyRepository;
import com.hipergarzon.workpages.services.JobVacancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobVacancyImplement implements JobVacancyService {

    @Autowired
    JobVacancyRepository jobVacancyRepository;

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Override
    public List<JobVacancy> getAllJobVacancies() {
        return jobVacancyRepository.findAll();
    }

    @Override
    public JobVacancy getJobVacancyById(Long id) {
        return jobVacancyRepository.findById(id).get();
    }

    @Override
    public List<JobVacancy> getJobVacanciesByBranchOffice(String branchOffice) {
        List<JobVacancy> jobVacancies = jobVacancyRepository.findAll();
        List<JobVacancy> filteredVacancies = new ArrayList<>();
        for (JobVacancy vacancy : jobVacancies) {
            if (vacancy.getBranchOffice().equalsIgnoreCase(branchOffice)) {
                filteredVacancies.add(vacancy);
            }
        }
        return filteredVacancies;
    }

    @Override
    public void saveJobVacancy(JobVacancy jobVacancy) {
        jobVacancyRepository.save(jobVacancy);
    }

    @Override
    public JobVacancy addVacanvy(JobVacancy jobVacancy) {
        return jobVacancyRepository.save(jobVacancy);
    }
}
