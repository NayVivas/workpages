package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.dtos.ItemsCatalogueCategoryDTO;
import com.hipergarzon.workpages.models.ItemsCatalogue;
import com.hipergarzon.workpages.models.ItemsCatalogueCategory;
import com.hipergarzon.workpages.repositories.ItemsCatalogueCategoryRepository;
import com.hipergarzon.workpages.repositories.ItemsCatalogueRepository;
import com.hipergarzon.workpages.services.ItemsCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemsCatalogueImplements implements ItemsCatalogueService {

    @Autowired
    private ItemsCatalogueRepository itemsCatalogueRepository;
    @Autowired
    private ItemsCatalogueCategoryRepository itemsCatalogueCategoryRepository;


    @Override
    public List<ItemsCatalogueCategory> getItemsCatalogueCategory() {
        return itemsCatalogueCategoryRepository.findAll();
    }

    @Override
    public ItemsCatalogueCategory getItemsCatalogueCategoryById(Long id) {
        return itemsCatalogueCategoryRepository.findById(id).get();
    }

    @Override
    public List<ItemsCatalogue> getItemsCatalogue() {
        return itemsCatalogueRepository.findAll();
    }

    @Override
    public ItemsCatalogue getItemsCatalogueById(Long id) {
        Optional<ItemsCatalogue> optionalItemsCatalogue = itemsCatalogueRepository.findById(id);
        if (optionalItemsCatalogue.isPresent()) {
            return optionalItemsCatalogue.get();
        } else {
            throw new NoSuchElementException("No se encontró ningún objeto con el ID: " + id);
        }
    }

    @Override
    public Optional<ItemsCatalogue> findById(Long id) {
        return itemsCatalogueRepository.findById(id);
    }

    @Override
    public void saveItemsCatalogue(ItemsCatalogue itemsCatalogue) {
        itemsCatalogueRepository.save(itemsCatalogue);
    }
    @Override
    public boolean existsItemsCatalogueByDescriptionIgnoreCase(String description) {
        return itemsCatalogueRepository.existsByDescriptionIgnoreCase(description);
    }
    @Override
    public boolean existsItemsCatalogueByDescriptionIgnoreCaseAndIdNot(String description, Long id) {
        return itemsCatalogueRepository.existsByDescriptionIgnoreCaseAndIdNot(description, id);
    }
}
