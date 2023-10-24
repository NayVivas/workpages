package com.hipergarzon.workpages.models;


import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    @Column(name = "USER_ID")
    private Long userId;
    @Column(name = "VACANCY_ID")
    private Long vacancyId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="seeker_id")
    private Seeker seeker;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jobVacancy_id")
    private JobVacancy jobVacancy;
    private String stateApplication = "Recibido";
    private Boolean vacancyActive = true;
    private LocalDate expirationDate;

    public JobApplication() {
    }

    public JobApplication(Seeker seeker, JobVacancy jobVacancy) {
        this.seeker = seeker;
        this.jobVacancy = jobVacancy;
    }

    public JobApplication(String stateApplication) {
        this.stateApplication = stateApplication;
    }
}