package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.JobApplication;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class JobApplicationDTO {
    private Long id;
    private String jobVacancyTitle;
    private String jobVacancyDescription;
    private String jobVacancySkills;
    private String branchOffice;
    private String experienceRequired;
    private String levelExperience;
    private String stateApplication = "Recibido";
    private SeekerDTO seeker;
    private Boolean vacancyActive = true;
    private LocalDate expirationDate;

    public JobApplicationDTO() {
    }

    public JobApplicationDTO(JobApplication jobApplication) {
        this.id = jobApplication.getId();
        this.jobVacancyTitle = jobApplication.getJobVacancy().getJobVacancyTitle();
        this.jobVacancyDescription = jobApplication.getJobVacancy().getJobVacancyDescription();
        this.jobVacancySkills = jobApplication.getJobVacancy().getJobVacancySkills();
        this.branchOffice = jobApplication.getJobVacancy().getBranchOffice();
        this.experienceRequired = jobApplication.getJobVacancy().getExperienceRequired();
        this.levelExperience = jobApplication.getJobVacancy().getLevelExperience();
        this.stateApplication = jobApplication.getStateApplication();
        this.vacancyActive = jobApplication.getVacancyActive();
        this.expirationDate = jobApplication.getExpirationDate();
    }
}
