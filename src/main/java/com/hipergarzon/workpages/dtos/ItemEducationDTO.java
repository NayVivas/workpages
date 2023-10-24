package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemEducation;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemEducationDTO {

    private Long id;
    private String title;
    private String school;
    private String typeStudy;
    private String areaStudy;
    private String countryEducation;
    private String state;
    private LocalDate initEducation;
    private LocalDate endEducation;

    public ItemEducationDTO() {
    }

    public ItemEducationDTO(ItemEducation itemEducation) {
        this.id = itemEducation.getId();
        this.title = itemEducation.getTitle();
        this.school = itemEducation.getSchool();
        this.typeStudy = itemEducation.getTypeStudy();
        this.areaStudy = itemEducation.getAreaStudy();
        this.countryEducation = itemEducation.getCountryEducation();
        this.state = itemEducation.getState();
        this.initEducation = itemEducation.getInitEducation();
        this.endEducation = itemEducation.getEndEducation();
    }

    public ItemEducationDTO(String title) {
        this.title = title;
    }
}
