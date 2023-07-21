package com.hipergarzon.workpages.controllers;


import com.hipergarzon.workpages.dtos.ItemEducationDTO;
import com.hipergarzon.workpages.dtos.ItemJobExperienceDTO;
import com.hipergarzon.workpages.dtos.ItemLanguagesDTO;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.models.enums.*;
import com.hipergarzon.workpages.services.ItemJobExperienceService;
import com.hipergarzon.workpages.services.SeekerService;
import com.hipergarzon.workpages.services.UserGeneralService;
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
public class ItemJobExperienceController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    ItemJobExperienceService itemJobExperienceService;

    @Autowired
    SeekerService seekerService;

    @GetMapping("/jobexperience")
    public List<ItemJobExperienceDTO> getJobExperience() {
        return itemJobExperienceService.getJobExperience().stream().map(ItemJobExperienceDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/jobexperience/{id}")
    public ItemJobExperienceDTO getJobExperienceById (@PathVariable Long id) {
        return new ItemJobExperienceDTO(itemJobExperienceService.getJobExperienceById(id));
    }

    @GetMapping("/users/current/jobexperience")
    public List<ItemJobExperienceDTO> getJobExperienceDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getExperiences().stream().map(ItemJobExperienceDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/jobexperience")
    public ResponseEntity<Object> registerJobExperience(
            Authentication authentication,
            @RequestBody ItemJobExperienceDTO itemJobExperienceDTO) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (itemJobExperienceDTO.getCompany().isEmpty()
                || itemJobExperienceDTO.getActivityCompany() == null || itemJobExperienceDTO.getJob().isEmpty()
                || itemJobExperienceDTO.getLevelExperience() == null || itemJobExperienceDTO.getAreaJob() == null
                || itemJobExperienceDTO.getCountryExperience().isEmpty() || itemJobExperienceDTO.getDescription().isEmpty()) {
            return new ResponseEntity<>("Parametro vacio", HttpStatus.FORBIDDEN);
        }

        ItemJobExperience itemJobExperience = new ItemJobExperience(seeker1, itemJobExperienceDTO.getCompany(),
                itemJobExperienceDTO.getActivityCompany(), itemJobExperienceDTO.getJob(),
                itemJobExperienceDTO.getLevelExperience(), itemJobExperienceDTO.getAreaJob(),
                itemJobExperienceDTO.getCountryExperience(), LocalDate.now(), LocalDate.now(),
                itemJobExperienceDTO.getDescription());

        itemJobExperienceService.saveJobExperience(itemJobExperience);
        seeker1.setFormsCompleteJobExperince(true);
        seekerService.saveSeeker(seeker1);

        if (seeker1.getFormsCompletePersonalData() && seeker1.getFormsCompleteEducations() && seeker1.getFormsCompleteJobExperince() && seeker1.getFormsCompleteOthersData()) {
            seeker1.setFormsComplete(true);
            seekerService.saveSeeker(seeker1);
        }
        return new ResponseEntity<>("Cuenta creada con éxito", HttpStatus.CREATED);
    }


    @PatchMapping("/users/current/jobexperience/{id}")
    public ResponseEntity<Object> updateJobexperience(@PathVariable Long id, Authentication authentication, @RequestBody ItemJobExperienceDTO itemJobExperienceDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemJobExperience itemJobExperience = itemJobExperienceService.getJobExperienceById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }

        itemJobExperience.setCompany(itemJobExperienceDTO.getCompany());
        itemJobExperience.setActivityCompany(itemJobExperienceDTO.getActivityCompany());
        itemJobExperience.setJob(itemJobExperienceDTO.getJob());
        itemJobExperience.setLevelExperience(itemJobExperienceDTO.getLevelExperience());
        itemJobExperience.setAreaJob(itemJobExperienceDTO.getAreaJob());
        itemJobExperience.setCountryExperience(itemJobExperienceDTO.getCountryExperience());
        itemJobExperience.setInitExperience(itemJobExperienceDTO.getInitExperience());
        itemJobExperience.setEndExperience(itemJobExperienceDTO.getEndExperience());
        itemJobExperience.setDescription(itemJobExperienceDTO.getDescription());

        itemJobExperienceService.saveJobExperience(itemJobExperience);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/current/jobexperience/delete/{id}")
    public ResponseEntity<String> deleteJobExperience(@PathVariable Long id, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemJobExperience itemJobExperience = itemJobExperienceService.getJobExperienceById(id);

        itemJobExperienceService.deleteJobExperience(itemJobExperience);

        return new ResponseEntity<>("Item eliminado exitosamente", HttpStatus.CREATED);
    }
}
