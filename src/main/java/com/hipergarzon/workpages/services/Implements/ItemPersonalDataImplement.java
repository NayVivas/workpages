package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.repositories.ItemPersonalDataRepository;
import com.hipergarzon.workpages.services.ItemPersonalDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPersonalDataImplement implements ItemPersonalDataService {

    @Autowired
    ItemPersonalDataRepository itemPersonalDataRepository;
    @Override
    public List<ItemPersonalData> getPersonalData() {
        return itemPersonalDataRepository.findAll();
    }

    @Override
    public ItemPersonalData getPersonalDataById(Long id) {
        return itemPersonalDataRepository.findById(id).get();
    }

    @Override
    public void savePersonalData(ItemPersonalData itemPersonalData) {
        itemPersonalDataRepository.save(itemPersonalData);
    }
}
