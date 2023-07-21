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
public class ItemEducation implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String title;
    private String school;
    private String typeStudy;
    private String areaStudy;
    private String countryEducation;
    private String state;
    private LocalDate initEducation;
    private LocalDate endEducation;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curriculum_id")
    private Seeker seeker;

    public ItemEducation() {
    }

    public ItemEducation(Seeker seeker, String title, String school, String typeStudy, String areaStudy, String countryEducation, String state, LocalDate initEducation, LocalDate endEducation) {
        this.title = title;
        this.school = school;
        this.typeStudy = typeStudy;
        this.areaStudy = areaStudy;
        this.countryEducation = countryEducation;
        this.state = state;
        this.initEducation = initEducation;
        this.endEducation = endEducation;
        this.seeker = seeker;
    }
}
