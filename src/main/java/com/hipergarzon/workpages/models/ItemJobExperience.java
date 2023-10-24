package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ItemJobExperience implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
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
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curriculum_id")
    private Seeker seeker;

    public ItemJobExperience() {
    }

    public ItemJobExperience(Seeker seeker, String company, String activityCompany, String job, String levelExperience, String areaJob, String countryExperience, LocalDate initExperience, LocalDate endExperience, String description) {
        this.seeker = seeker;
        this.company = company;
        this.activityCompany = activityCompany;
        this.job = job;
        this.levelExperience = levelExperience;
        this.areaJob = areaJob;
        this.countryExperience = countryExperience;
        this.initExperience = initExperience;
        this.endExperience = endExperience;
        this.description = description;
    }
}
