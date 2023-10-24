package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemEducation;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.repositories.ItemEducationRepository;
import com.hipergarzon.workpages.services.ItemEducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemEducationImplement implements ItemEducationService {

    @Autowired
    ItemEducationRepository itemEducationRepository;

    @Override
    public List<ItemEducation> getEducations() {
        return itemEducationRepository.findAll();
    }

    @Override
    public ItemEducation getEducationsById(Long id) {
        return itemEducationRepository.findById(id).get();
    }

    @Override
    public void saveEducations(ItemEducation itemEducation) {
        itemEducationRepository.save(itemEducation);
    }

    @Override
    public void deleteEducations(ItemEducation itemEducation) {
        itemEducationRepository.delete(itemEducation);
    }
}
