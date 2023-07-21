package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemSkills;
import lombok.Getter;

@Getter
public class ItemSkillsDTO {

    private Long id;
    private String technicalSkills;
    private String softSkills;

    public ItemSkillsDTO() {
    }

    public ItemSkillsDTO(ItemSkills itemSkills) {
        this.id = itemSkills.getId();
        this.technicalSkills = itemSkills.getTechnicalSkills();
        this.softSkills = itemSkills.getSoftSkills();
    }

    public ItemSkillsDTO(long id, String softSkills, String technicalSkills) {
        this.id = id;
        this.softSkills = softSkills;
        this.technicalSkills = technicalSkills;
    }
}
