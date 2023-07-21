package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemPersonalDataDTO;
import com.hipergarzon.workpages.dtos.UserGeneralDTO;
import com.hipergarzon.workpages.models.ItemSkills;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.services.*;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.models.enums.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemPersonalDataController {

    @Autowired
    UserGeneralService userGeneralService;
    @Autowired
    ItemPersonalDataService itemPersonalDataService;
    @Autowired
    SeekerService seekerService;

    @GetMapping("/personaldata")
    public List<ItemPersonalDataDTO> getPersonalData() {
        return itemPersonalDataService.getPersonalData().stream().map(ItemPersonalDataDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/personaldata/{id}")
    public ItemPersonalDataDTO getPersonalDataById (@PathVariable Long id) {
        return new ItemPersonalDataDTO(itemPersonalDataService.getPersonalDataById(id));
    }

    @GetMapping("/users/current/personaldata")
    public List<ItemPersonalDataDTO> getPersonalDataDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getPersonalData().stream().map(ItemPersonalDataDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/personaldata")
    public ResponseEntity<Object> registerPersonalData(
            Authentication authentication,
            @RequestBody ItemPersonalDataDTO itemPersonalDataDTO) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (itemPersonalDataDTO.getFirstName().isEmpty() || itemPersonalDataDTO.getLastName().isEmpty() || itemPersonalDataDTO.getEmail().isEmpty() || itemPersonalDataDTO.getDocument().isEmpty() ||
                itemPersonalDataDTO.getCivilStatus() == null || itemPersonalDataDTO.getChildren() == null || itemPersonalDataDTO.getPhoneOne().isEmpty() ||
                itemPersonalDataDTO.getPhoneTwo().isEmpty() || itemPersonalDataDTO.getAddress().isEmpty() || itemPersonalDataDTO.getCityResidence().isEmpty() ||
                itemPersonalDataDTO.getStateResidence().isEmpty() || itemPersonalDataDTO.getGender() == null) {
            return new ResponseEntity<>("Parametro vacio", HttpStatus.FORBIDDEN);
        }
        ItemPersonalData itemPersonalData = new ItemPersonalData(seeker1, itemPersonalDataDTO.getFirstName(), itemPersonalDataDTO.getLastName(), itemPersonalDataDTO.getEmail(),
                LocalDate.now(), itemPersonalDataDTO.getDocument(), itemPersonalDataDTO.getCivilStatus(), itemPersonalDataDTO.getChildren(),
                itemPersonalDataDTO.getAmountChildren(), itemPersonalDataDTO.getAgeChildren(), itemPersonalDataDTO.getPhoneOne(), itemPersonalDataDTO.getPhoneTwo(),
                itemPersonalDataDTO.getAddress(), itemPersonalDataDTO.getStateResidence(), itemPersonalDataDTO.getCityResidence(), itemPersonalDataDTO.getGender());
        itemPersonalDataService.savePersonalData(itemPersonalData);
        seeker1.setFormsCompletePersonalData(true);
        seekerService.saveSeeker(seeker1);

        if (seeker1.getFormsCompletePersonalData() && seeker1.getFormsCompleteEducations() && seeker1.getFormsCompleteJobExperince() && seeker1.getFormsCompleteOthersData()) {
            seeker1.setFormsComplete(true);
            seekerService.saveSeeker(seeker1);
        }
        return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);
    }

    @PatchMapping("/users/current/personaldata/{id}")
    public ResponseEntity<Object> updatePersonalData(@PathVariable Long id, Authentication authentication, @RequestBody ItemPersonalDataDTO itemPersonalDataDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemPersonalData itemPersonalData = itemPersonalDataService.getPersonalDataById(id);
        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }
        itemPersonalData.setFirstName(itemPersonalDataDTO.getFirstName());
        itemPersonalData.setLastName(itemPersonalDataDTO.getLastName());
        itemPersonalData.setEmail(itemPersonalDataDTO.getEmail());
        itemPersonalData.setDocument(itemPersonalDataDTO.getDocument());
        itemPersonalData.setCivilStatus(itemPersonalDataDTO.getCivilStatus());
        itemPersonalData.setChildren(itemPersonalDataDTO.getChildren());
        itemPersonalData.setAmountChildren(itemPersonalDataDTO.getAmountChildren());
        itemPersonalData.setAgeChildren(itemPersonalDataDTO.getAgeChildren());
        itemPersonalData.setPhoneOne(itemPersonalDataDTO.getPhoneOne());
        itemPersonalData.setPhoneTwo(itemPersonalDataDTO.getPhoneTwo());
        itemPersonalData.setAddress(itemPersonalDataDTO.getAddress());
        itemPersonalData.setCityResidence(itemPersonalDataDTO.getCityResidence());
        itemPersonalData.setStateResidence(itemPersonalDataDTO.getStateResidence());
        itemPersonalData.setGender(itemPersonalDataDTO.getGender());
        itemPersonalDataService.savePersonalData(itemPersonalData);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }
}
