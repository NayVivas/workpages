package com.hipergarzon.workpages.controllers;


import com.hipergarzon.workpages.dtos.ItemLanguagesDTO;
import com.hipergarzon.workpages.dtos.ItemOthersDataDTO;
import com.hipergarzon.workpages.dtos.ItemSkillsDTO;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.models.enums.*;
import com.hipergarzon.workpages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemLanguagesController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    ItemLanguagesService itemLanguagesService;

    @Autowired
    SeekerService seekerService;

    @GetMapping("/languages")
    public List<ItemLanguagesDTO> getLanguages() {
        return itemLanguagesService.getLanguages().stream().map(ItemLanguagesDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/languages/{id}")
    public ItemLanguagesDTO getLanguageById (@PathVariable Long id) {
        return new ItemLanguagesDTO(itemLanguagesService.getLanguagesById(id));
    }

    @GetMapping("/users/current/languages")
    public List<ItemLanguagesDTO> getLanguagesDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getLanguages().stream().map(ItemLanguagesDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/languages")
    public ResponseEntity<Object> registerLanguages(
            Authentication authentication,
            @RequestBody ItemLanguagesDTO itemLanguagesDTO) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (itemLanguagesDTO.getLanguages() == null || itemLanguagesDTO.getWriteLevel() == null || itemLanguagesDTO.getOralLevel() == null) {
            return new ResponseEntity<>("Parametro vacio", HttpStatus.FORBIDDEN);
        }
        ItemLanguages itemLanguages = new ItemLanguages(seeker1, itemLanguagesDTO.getLanguages(), itemLanguagesDTO.getWriteLevel(), itemLanguagesDTO.getOralLevel());

        itemLanguagesService.saveLanguages(itemLanguages);
        seekerService.saveSeeker(seeker1);

        return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);
    }

    @PatchMapping("/users/current/languages/{id}")
    public ResponseEntity<Object> updateLanguages(@PathVariable Long id, Authentication authentication, @RequestBody ItemLanguagesDTO itemLanguagesDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemLanguages itemLanguages = itemLanguagesService.getLanguagesById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }

        itemLanguages.setLanguages(itemLanguagesDTO.getLanguages());
        itemLanguages.setWriteLevel(itemLanguagesDTO.getWriteLevel());
        itemLanguages.setOralLevel(itemLanguagesDTO.getOralLevel());


        itemLanguagesService.saveLanguages(itemLanguages);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/current/languages/delete/{id}")
    public ResponseEntity<String> deleteLanguages(@PathVariable Long id, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemLanguages itemLanguages = itemLanguagesService.getLanguagesById(id);

        itemLanguagesService.deleteLanguages(itemLanguages);

        return new ResponseEntity<>("Item eliminado exitosamente", HttpStatus.CREATED);
    }
}
