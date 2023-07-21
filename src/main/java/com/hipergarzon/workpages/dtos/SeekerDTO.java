package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.models.enums.YesOrNo;
import lombok.Getter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class SeekerDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<ItemPersonalDataDTO> personalData;
    private Set<ItemEducationDTO> educations;
    private Set<ItemJobExperienceDTO> jobExperiences;
    private Set<ItemLanguagesDTO> languages;
    private Set<ItemSkillsDTO> skills;
    private Set<ItemOthersDataDTO> otherDatas;
    private Set<JobApplicationDTO> jobApplications;
    private String images;
    private String files;
    private String gender;
    private YesOrNo children;
    private Boolean formsComplete = false;

    public SeekerDTO() {
    }

    public SeekerDTO(Seeker seeker) {
        this.id = seeker.getId();
        this.firstName = seeker.getFirstName();
        this.lastName = seeker.getLastName();
        this.email = seeker.getEmail();
        this.personalData = seeker.getPersonalData().stream().map(ItemPersonalDataDTO::new).collect(Collectors.toSet());
        this.otherDatas = seeker.getOthersData().stream().map(ItemOthersDataDTO::new).collect(Collectors.toSet());
        this.educations = seeker.getEducations().stream().map(ItemEducationDTO::new).collect(Collectors.toSet());
        this.jobExperiences = seeker.getExperiences().stream().map(ItemJobExperienceDTO::new).collect(Collectors.toSet());
        this.languages = seeker.getLanguages().stream().map(ItemLanguagesDTO::new).collect(Collectors.toSet());
        this.skills = seeker.getSkills().stream().map(ItemSkillsDTO::new).collect(Collectors.toSet());
        ItemFiles file = seeker.getItemFiles();
        this.files = file != null ? file.getUrl() : null;
        ItemImage image = seeker.getItemImages();
        this.images = image != null ? image.getUrl() : null;
        this.jobApplications = seeker.getJobApplications().stream().map(JobApplicationDTO::new).collect(Collectors.toSet());
        this.formsComplete = seeker.getFormsComplete();
    }

    public SeekerDTO(Long id, String firstName, String lastName, String email, String gender, YesOrNo children) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.children = children;
    }

    public SeekerDTO(Long id, String firstName, String lastName, String email, ItemPersonalDataDTO personalData, List<ItemEducationDTO> educations, List<ItemOthersDataDTO> otherData, List<ItemSkillsDTO> skills, List<ItemLanguagesDTO> languages) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        Set<ItemPersonalDataDTO> personalDataSet = new HashSet<>();
        personalDataSet.add(personalData);
        this.personalData = personalDataSet;
        this.educations = new HashSet<>(educations);
        this.otherDatas = new HashSet<>(otherData);
        this.skills = new HashSet<>(skills);
        this.languages = new HashSet<>(languages);
    }
}
