package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemsCatalogueDTO;
import com.hipergarzon.workpages.models.ItemsCatalogue;
import com.hipergarzon.workpages.models.ItemsCatalogueCategory;
import com.hipergarzon.workpages.repositories.ItemsCatalogueCategoryRepository;
import com.hipergarzon.workpages.services.ItemsCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemsCatalogueController {

    @Autowired
    private ItemsCatalogueService itemsCatalogueService;

    @Autowired
    private ItemsCatalogueCategoryRepository itemsCatalogueCategoryRepository;

    @GetMapping("/items")
    public List<ItemsCatalogueDTO> getAllItems() {
        return itemsCatalogueService.getItemsCatalogue().stream().map(ItemsCatalogueDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/items/{id}")
    public ItemsCatalogueDTO getItemsyById (@PathVariable Long id) {
        return new ItemsCatalogueDTO(itemsCatalogueService.getItemsCatalogueById(id));
    }

    @PostMapping("/item/{id}")
    public ResponseEntity<String> createItem(@RequestBody ItemsCatalogueDTO itemDTO, @PathVariable Long id) {
        ItemsCatalogueCategory category = itemsCatalogueService.getItemsCatalogueCategoryById(id);

        if (itemsCatalogueService.existsItemsCatalogueByDescriptionIgnoreCase(itemDTO.getDescription())) {
            return ResponseEntity.badRequest().body("Ya existe un item con esa descripción");
        }

        ItemsCatalogue item = new ItemsCatalogue(itemDTO.getDescription(), category, itemDTO.getActiveItems());
        itemsCatalogueService.saveItemsCatalogue(item);

        return ResponseEntity.ok("Item creado exitosamente");
    }

    @PatchMapping("/item/{id}")
    public ResponseEntity<String> updateItem(@RequestBody ItemsCatalogueDTO itemDTO, @PathVariable Long id) {
        Optional<ItemsCatalogue> optionalItem = itemsCatalogueService.findById(id);

        if (optionalItem.isPresent()) {
            ItemsCatalogue item = optionalItem.get();
            if (itemsCatalogueService.existsItemsCatalogueByDescriptionIgnoreCaseAndIdNot(itemDTO.getDescription(), id)) {
                return ResponseEntity.badRequest().body("Ya existe un item con esa descripción");
            }
            item.setDescription(itemDTO.getDescription());
            itemsCatalogueService.saveItemsCatalogue(item);
            return ResponseEntity.ok("Item creado exitosamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/item/active/{id}")
    public ResponseEntity<String> updateItemActive(@RequestBody ItemsCatalogueDTO itemDTO, @PathVariable Long id) {
        Optional<ItemsCatalogue> optionalItem = itemsCatalogueService.findById(id);

        if (optionalItem.isPresent()) {
            ItemsCatalogue item = optionalItem.get();
            if (itemsCatalogueService.existsItemsCatalogueByDescriptionIgnoreCaseAndIdNot(itemDTO.getDescription(), id)) {
                return ResponseEntity.badRequest().body("Ya existe un item con esa descripción");
            }
            item.setActiveItems(itemDTO.getActiveItems());
            itemsCatalogueService.saveItemsCatalogue(item);
            return ResponseEntity.ok("Item activado");
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PatchMapping("/item/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
        ItemsCatalogue itemsCatalogue = itemsCatalogueService.getItemsCatalogueById(id);
        boolean isActive = itemsCatalogue.isActiveItems();
        itemsCatalogue.setActiveItems(!isActive);
        itemsCatalogueService.saveItemsCatalogue(itemsCatalogue);
        String message = isActive ? "Item deactivated successfully" : "Item activated successfully";
        return ResponseEntity.ok(message);
    }
}