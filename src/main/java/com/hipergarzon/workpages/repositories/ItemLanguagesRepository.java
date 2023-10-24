package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemLanguages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemLanguagesRepository extends JpaRepository<ItemLanguages, Long> {
}
