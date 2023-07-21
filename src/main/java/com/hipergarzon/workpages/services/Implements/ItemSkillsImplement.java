package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemSkills;
import com.hipergarzon.workpages.repositories.ItemSkillsRepository;
import com.hipergarzon.workpages.services.ItemSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemSkillsImplement implements ItemSkillsService {

    @Autowired
    ItemSkillsRepository itemSkillsRepository;
    @Override
    public List<ItemSkills> getSkills() {
        return itemSkillsRepository.findAll();
    }

    @Override
    public ItemSkills getSkillsById(long id) {
        return itemSkillsRepository.findById(id).get();
    }

    @Override
    public void saveSkills(ItemSkills itemSkills) {
        itemSkillsRepository.save(itemSkills);
    }

    @Override
    public void deleteSkills(ItemSkills itemSkills) {
        itemSkillsRepository.delete(itemSkills);
    }
}
