package com.hipergarzon.workpages.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hipergarzon.workpages.models.ItemsCatalogue;
import com.hipergarzon.workpages.models.ItemsCatalogueCategory;
import lombok.Getter;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class ItemsCatalogueCategoryDTO {
    private Long id;
    private String name;
    private Set<ItemsCatalogueDTO> itemsCatalogueList;

    public ItemsCatalogueCategoryDTO() {
    }

    public ItemsCatalogueCategoryDTO(ItemsCatalogueCategory itemsCatalogueCategory) {
        this.id = itemsCatalogueCategory.getId();
        this.name = itemsCatalogueCategory.getName();
        this.itemsCatalogueList = itemsCatalogueCategory.getItemsCatalogues().stream().map(ItemsCatalogueDTO::new).collect(Collectors.toSet());
    }

    public Set<ItemsCatalogueDTO> getItemsCatalogueList() {
        return itemsCatalogueList;
    }
}