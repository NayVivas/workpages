package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.ItemEducation;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.UserGeneral;

import java.util.List;

public interface ItemEducationService {

    public List<ItemEducation> getEducations();

    public ItemEducation getEducationsById(Long id);
    void saveEducations(ItemEducation itemEducation);
    void deleteEducations(ItemEducation itemEducation);
}
