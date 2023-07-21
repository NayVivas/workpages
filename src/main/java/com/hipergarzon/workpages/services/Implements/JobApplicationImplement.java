package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.JobApplication;
import com.hipergarzon.workpages.models.JobVacancy;
import com.hipergarzon.workpages.repositories.JobApplicationRepository;
import com.hipergarzon.workpages.repositories.JobVacancyRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.services.JobApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class JobApplicationImplement implements JobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Autowired
    JobVacancyRepository jobVacancyRepository;

    @Autowired
    SeekerRepository seekerRepository;


    @Override
    public List<JobApplication> getAllJobApplications() {
        return jobApplicationRepository.findAll();
    }

    @Override
    public JobApplication getIdByJobApplicatio(Long id) {
        return jobApplicationRepository.findById(id).get();
    }

    @Override
    public List<JobApplication> findByJobVacancyId(Long jobVacancyId) {
        return jobApplicationRepository.findByJobVacancyId(jobVacancyId);
    }


    @Override
    public JobApplication saveApply(JobApplication jobApplication) {
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public List<JobApplication> getJobVacancyApplications(Long vacancyId) throws ChangeSetPersister.NotFoundException {
        JobVacancy jobVacancy = jobVacancyRepository.findById(vacancyId).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        Set<JobApplication> jobApplications = jobVacancy.getJobApplications();
        List<JobApplication> jobApplicationsList = new ArrayList<>(jobApplications);

        return jobApplicationsList;
    }
}
