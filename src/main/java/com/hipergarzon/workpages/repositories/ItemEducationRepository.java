package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemEducation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemEducationRepository extends JpaRepository<ItemEducation, Long> {
}
