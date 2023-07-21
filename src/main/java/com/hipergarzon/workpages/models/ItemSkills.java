package com.hipergarzon.workpages.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class ItemSkills implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String technicalSkills;
    private String softSkills;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="curriculum_id")
    private Seeker seeker;


    public ItemSkills() {
    }

    public ItemSkills(Seeker seeker, String technicalSkills, String softSkills) {
        this.seeker = seeker;
        this.technicalSkills = technicalSkills;
        this.softSkills = softSkills;
    }
}
