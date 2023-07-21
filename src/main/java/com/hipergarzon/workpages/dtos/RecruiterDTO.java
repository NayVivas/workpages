package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.Recruiter;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class RecruiterDTO {
    private Long id;
    private Long branchOfficeId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String jobInCompany;
    private String branchOffice;
    private Set<JobVacancyDTO> jobVacancies;
    @Setter
    private Boolean activeRecruiter;

    public RecruiterDTO() {
    }

    public RecruiterDTO(Recruiter userRecruiter) {
        this.id = userRecruiter.getId();
        this.branchOfficeId = userRecruiter.getBranchOfficeId();
        this.firstName = userRecruiter.getFirstName();
        this.lastName = userRecruiter.getLastName();
        this.email = userRecruiter.getEmail();
        this.jobInCompany = userRecruiter.getJobInCompany();
        this.password = userRecruiter.getPassword();
        this.branchOffice = userRecruiter.getBranchOffice();
        this.jobVacancies = userRecruiter.getJobVacancies().stream().map(JobVacancyDTO::new).collect(Collectors.toSet());
        this.activeRecruiter = userRecruiter.getActiveUser();
    }
}

