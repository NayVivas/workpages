package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemsCatalogueCategoryDTO;
import com.hipergarzon.workpages.repositories.ItemsCatalogueCategoryRepository;
import com.hipergarzon.workpages.services.ItemsCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemsCatalogueCategoryController {

    @Autowired
    private ItemsCatalogueService itemsCatalogueService;

    @Autowired
    private ItemsCatalogueCategoryRepository itemsCatalogueCategoryRepository;

    @GetMapping("/items/categories")
    public List<ItemsCatalogueCategoryDTO> getAllCategories() {
        return itemsCatalogueService.getItemsCatalogueCategory().stream().map(ItemsCatalogueCategoryDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/items/categories/{id}")
    public ItemsCatalogueCategoryDTO getItemsCategoryById (@PathVariable Long id) {
        return new ItemsCatalogueCategoryDTO(itemsCatalogueService.getItemsCatalogueCategoryById(id));
    }
}
