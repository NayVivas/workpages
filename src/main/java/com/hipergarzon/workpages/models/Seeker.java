package com.hipergarzon.workpages.models;

import com.hipergarzon.workpages.models.enums.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Seeker extends UserGeneral {
    private boolean isEnabled;
    private String resetPasswordToken;

    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemPersonalData> personalData;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemEducation> educations;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemJobExperience> experiences;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemSkills> skills;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemLanguages> languages;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<ItemOthersData> othersData;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="seeker")
    private Set<JobApplication> jobApplications;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id", referencedColumnName = "id")
    private ItemImage itemImages;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id", referencedColumnName = "id")
    private ItemFiles itemFiles;


    public Seeker() {
    }

    public Seeker(String firstName, String lastName, String email, String password) {
        super(firstName, lastName, email, password);
    }

    public Seeker(String firstName, String lastName, String email, String password, boolean isEnabled, String resetPasswordToken) {
        super(firstName, lastName, email, password);
        this.isEnabled = isEnabled;
        this.resetPasswordToken = resetPasswordToken;
    }
}
