package com.hipergarzon.workpages.dtos;

import com.hipergarzon.workpages.models.ItemLanguages;
import lombok.Getter;

@Getter
public class ItemLanguagesDTO {

    private Long id;
    private String languages;
    private String writeLevel;
    private String oralLevel;

    public ItemLanguagesDTO() {
    }

    public ItemLanguagesDTO(ItemLanguages itemLanguages) {
        this.id = itemLanguages.getId();
        this.languages = itemLanguages.getLanguages();
        this.writeLevel = itemLanguages.getWriteLevel();
        this.oralLevel = itemLanguages.getOralLevel();
    }

    public ItemLanguagesDTO(String languages, long id, String oralLevel, String writeLevel) {
        this.id = id;
        this.languages = languages;
        this.oralLevel = oralLevel;
        this.writeLevel = writeLevel;
    }
}
