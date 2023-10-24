package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Audits {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String email;
    private String jobCompany;
    private LocalDateTime date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_general_id")
    private UserGeneral userGeneral;

    public Long getUserId() {
        return userGeneral.getId();
    }

    @ManyToOne
    @JoinColumn(name = "jobvacancy_id")
    private JobVacancy jobVacancy;

    @ManyToOne
    @JoinColumn(name = "jobapplication_id")
    private JobApplication jobApplication;

}
