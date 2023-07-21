package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemsCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource
public interface ItemsCatalogueRepository extends JpaRepository<ItemsCatalogue, Long> {

    boolean existsByDescriptionIgnoreCase(String description);

    boolean existsByDescriptionIgnoreCaseAndIdNot(String description, Long id);
}
