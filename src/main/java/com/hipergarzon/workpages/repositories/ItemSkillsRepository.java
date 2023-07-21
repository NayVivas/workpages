package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemSkills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemSkillsRepository extends JpaRepository<ItemSkills, Long> {
}
