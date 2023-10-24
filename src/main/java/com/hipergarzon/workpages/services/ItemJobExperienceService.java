package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.ItemJobExperience;
import com.hipergarzon.workpages.models.ItemPersonalData;

import java.util.List;

public interface ItemJobExperienceService {

    public List<ItemJobExperience> getJobExperience();

    public ItemJobExperience getJobExperienceById(Long id);
    void saveJobExperience(ItemJobExperience itemJobExperience);
    void deleteJobExperience(ItemJobExperience itemJobExperience);
}
