package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemOthersData;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.repositories.ItemOthersDataRepository;
import com.hipergarzon.workpages.services.ItemOthersDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemOthersDataImplement implements ItemOthersDataService {

    @Autowired
    ItemOthersDataRepository itemOthersDataRepository;

    @Override
    public List<ItemOthersData> getOtherData() {
        return itemOthersDataRepository.findAll();
    }

    @Override
    public ItemOthersData getOtherDataById(Long id) {
        return itemOthersDataRepository.findById(id).get();
    }

    @Override
    public void saveOtherData(ItemOthersData itemOthersData) {
        itemOthersDataRepository.save(itemOthersData);
    }
}
