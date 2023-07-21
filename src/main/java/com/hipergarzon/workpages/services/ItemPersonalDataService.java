package com.hipergarzon.workpages.services;


import com.hipergarzon.workpages.models.ItemEducation;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.UserGeneral;

import java.util.List;
import java.util.Optional;

public interface ItemPersonalDataService {

    public List<ItemPersonalData> getPersonalData();
    public ItemPersonalData getPersonalDataById(Long id);

    void savePersonalData(ItemPersonalData itemPersonalData);
}
