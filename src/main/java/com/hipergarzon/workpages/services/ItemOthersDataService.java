package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.ItemOthersData;
import com.hipergarzon.workpages.models.ItemPersonalData;

import java.util.List;

public interface ItemOthersDataService {

    public List<ItemOthersData> getOtherData();
    public ItemOthersData getOtherDataById(Long id);
    void saveOtherData(ItemOthersData itemOthersData);
}
