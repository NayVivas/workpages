package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemImageRepository extends JpaRepository<ItemImage, Long> {
}
