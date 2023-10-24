package com.hipergarzon.workpages.repositories;

import com.hipergarzon.workpages.models.ItemFiles;
import com.hipergarzon.workpages.models.Seeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ItemFilesRepository extends JpaRepository<ItemFiles, Long> {

    ItemFiles findBySeekerAndFileName(Seeker seeker, String fileName);
}
