package com.hipergarzon.workpages.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ApplicationSeekerDTO {

    @Setter
    private Long SeekerId;
    private Long jobVacancyId;
    @Setter
    private String stateJobApplication;
    @Setter
    private String seekerName;
    @Setter
    private String seekerLastName;
    @Setter
    private String seekerEmail;

    public ApplicationSeekerDTO() {}

    public ApplicationSeekerDTO(Long seekerId, Long jobVacancyId, String stateJobApplication, String seekerName, String seekerLastName, String seekerEmail) {
        this.SeekerId = seekerId;
        this.jobVacancyId = jobVacancyId;
        this.stateJobApplication = stateJobApplication;
        this.seekerName = seekerName;
        this.seekerLastName = seekerLastName;
        this.seekerEmail = seekerEmail;
    }
}
