package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.dtos.ItemsCatalogueCategoryDTO;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.ItemsCatalogue;
import com.hipergarzon.workpages.models.ItemsCatalogueCategory;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemsCatalogueService {

    public List<ItemsCatalogueCategory> getItemsCatalogueCategory();
    public ItemsCatalogueCategory getItemsCatalogueCategoryById(Long id);
    public List<ItemsCatalogue> getItemsCatalogue();
    public ItemsCatalogue getItemsCatalogueById(Long id);
    public Optional<ItemsCatalogue> findById(Long id);
    void saveItemsCatalogue(ItemsCatalogue itemsCatalogue);
    public boolean existsItemsCatalogueByDescriptionIgnoreCase(String description);
    public boolean existsItemsCatalogueByDescriptionIgnoreCaseAndIdNot(String description, Long id);
}
