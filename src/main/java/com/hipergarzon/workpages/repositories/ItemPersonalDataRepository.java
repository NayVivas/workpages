package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemPersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemPersonalDataRepository extends JpaRepository<ItemPersonalData, Long> {

}
