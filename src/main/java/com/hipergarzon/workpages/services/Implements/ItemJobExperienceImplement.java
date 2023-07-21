package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemJobExperience;
import com.hipergarzon.workpages.repositories.ItemJobExperienceRepository;
import com.hipergarzon.workpages.services.ItemJobExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemJobExperienceImplement implements ItemJobExperienceService {

    @Autowired
    ItemJobExperienceRepository itemJobExperienceRepository;
    @Override
    public List<ItemJobExperience> getJobExperience() {
        return itemJobExperienceRepository.findAll();
    }

    @Override
    public ItemJobExperience getJobExperienceById(Long id) {
        return itemJobExperienceRepository.findById(id).get();
    }

    @Override
    public void saveJobExperience(ItemJobExperience itemJobExperience) {
        itemJobExperienceRepository.save(itemJobExperience);
    }

    @Override
    public void deleteJobExperience(ItemJobExperience itemJobExperience) {
        itemJobExperienceRepository.delete(itemJobExperience);
    }
}
