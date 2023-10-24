package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemJobExperience;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemJobExperienceDTO {

    private Long id;
    private String company;
    private String activityCompany;
    private String job;
    private String levelExperience;
    private String areaJob;
    private String countryExperience;
    private LocalDate initExperience;
    private LocalDate endExperience;
    private String description;

    public ItemJobExperienceDTO() {
    }

    public ItemJobExperienceDTO(ItemJobExperience itemJobExperience) {
        this.id = itemJobExperience.getId();
        this.company = itemJobExperience.getCompany();
        this.activityCompany = itemJobExperience.getActivityCompany();
        this.job = itemJobExperience.getJob();
        this.levelExperience = itemJobExperience.getLevelExperience();
        this.areaJob = itemJobExperience.getAreaJob();
        this.countryExperience = itemJobExperience.getCountryExperience();
        this.initExperience = itemJobExperience.getInitExperience();
        this.endExperience = itemJobExperience.getEndExperience();
        this.description = itemJobExperience.getDescription();
    }
}
