package com.hipergarzon.workpages.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hipergarzon.workpages.models.ItemsCatalogue;
import com.hipergarzon.workpages.models.ItemsCatalogueCategory;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemsCatalogueDTO {
    private Long id;
    private String description;
    private Boolean activeItems = true;

    public ItemsCatalogueDTO() {
    }
    public ItemsCatalogueDTO(ItemsCatalogue itemsCatalogue) {
        this.id = itemsCatalogue.getId();
        this.description = itemsCatalogue.getDescription();
        this.activeItems = itemsCatalogue.isActiveItems();
    }
}
