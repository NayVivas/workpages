package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
public class JobVacancy {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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
    private LocalDate expirationDate;
    @PreUpdate
    public void preUpdate() {
        if (!vacancyActive && expirationDate == null) {
            expirationDate = LocalDate.now();
        }
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="jobV_recruiter")
    private Recruiter recruiter;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="jobV_seeker")
    private Seeker seeker;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="jobVacancy")
    private Set<JobApplication> jobApplications;

    public JobVacancy() {
    }

    public JobVacancy(Recruiter recruiter, String jobVacancyTitle, String jobVacancyDescription, String jobVacancySkills, String branchOffice, String experienceRequired, String levelExperience, String postedBy, LocalDate postedDate, Boolean vacancyActive) {
        this.recruiter = recruiter;
        this.jobVacancyTitle = jobVacancyTitle;
        this.jobVacancyDescription = jobVacancyDescription;
        this.jobVacancySkills = jobVacancySkills;
        this.branchOffice = branchOffice;
        this.experienceRequired = experienceRequired;
        this.levelExperience = levelExperience;
        this.postedBy = postedBy;
        this.postedDate = postedDate;
        this.vacancyActive = vacancyActive;
    }
}
