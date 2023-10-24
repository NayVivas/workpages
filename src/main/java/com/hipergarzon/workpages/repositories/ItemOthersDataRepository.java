package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemOthersData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemOthersDataRepository extends JpaRepository<ItemOthersData, Long> {

}
