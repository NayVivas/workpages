package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.ItemLanguages;
import com.hipergarzon.workpages.repositories.ItemLanguagesRepository;
import com.hipergarzon.workpages.services.ItemLanguagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ItemLanguagesImplement  implements ItemLanguagesService {

    @Autowired
    ItemLanguagesRepository itemLanguagesRepository;

    @Override
    public List<ItemLanguages> getLanguages() {
        return itemLanguagesRepository.findAll();
    }

    @Override
    public ItemLanguages getLanguagesById(long id) {
        return itemLanguagesRepository.findById(id).get();
    }

    @Override
    public void saveLanguages(ItemLanguages itemLanguages) {
        itemLanguagesRepository.save(itemLanguages);
    }

    @Override
    public void deleteLanguages(ItemLanguages itemLanguages) {
        itemLanguagesRepository.delete(itemLanguages);
    }
}
