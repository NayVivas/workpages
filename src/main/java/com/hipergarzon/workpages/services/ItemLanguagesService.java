package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.ItemLanguages;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ItemLanguagesService {

    public List<ItemLanguages> getLanguages();

    public ItemLanguages getLanguagesById(long id);
    void saveLanguages(ItemLanguages itemLanguages);
    void deleteLanguages(ItemLanguages itemLanguages);
}
