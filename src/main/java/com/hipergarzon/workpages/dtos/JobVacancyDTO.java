package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.JobVacancy;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class JobVacancyDTO {

    private Long id;
    private String jobVacancyTitle;
    private String jobVacancyDescription;
    private String jobVacancySkills;
    private String branchOffice;
    private String experienceRequired;
    private String levelExperience;
    private String postedBy;
    private LocalDate postedDate;
    private Boolean vacancyActive = true;

    public JobVacancyDTO() {
    }

    public JobVacancyDTO(JobVacancy jobVacancy) {
        this.id = jobVacancy.getId();
        this.jobVacancyTitle = jobVacancy.getJobVacancyTitle();
        this.jobVacancyDescription = jobVacancy.getJobVacancyDescription();
        this.jobVacancySkills = jobVacancy.getJobVacancySkills();
        this.branchOffice = jobVacancy.getBranchOffice();
        this.experienceRequired = jobVacancy.getExperienceRequired();
        this.levelExperience = jobVacancy.getLevelExperience();
        this.postedBy = jobVacancy.getPostedBy();
        this.postedDate = jobVacancy.getPostedDate();
        this.vacancyActive = jobVacancy.getVacancyActive();
    }
}
