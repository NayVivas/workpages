package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.ItemLanguages;
import com.hipergarzon.workpages.models.ItemSkills;
import org.springframework.stereotype.Service;

import javax.mail.FetchProfile;
import java.util.List;

@Service
public interface ItemSkillsService {

    public List<ItemSkills> getSkills();

    public ItemSkills getSkillsById(long id);
    void saveSkills(ItemSkills itemSkills);
    void deleteSkills(ItemSkills itemSkills);
}
