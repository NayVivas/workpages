package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemJobExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemJobExperienceRepository extends JpaRepository<ItemJobExperience, Long> {
}
