package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemsCatalogueCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemsCatalogueCategoryRepository extends JpaRepository<ItemsCatalogueCategory, Long> {

}
