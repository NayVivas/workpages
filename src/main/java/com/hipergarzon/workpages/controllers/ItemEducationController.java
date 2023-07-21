package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemEducationDTO;
import com.hipergarzon.workpages.dtos.ItemJobExperienceDTO;
import com.hipergarzon.workpages.dtos.ItemPersonalDataDTO;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.models.enums.*;
import com.hipergarzon.workpages.services.ItemEducationService;
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
public class ItemEducationController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    ItemEducationService itemEducationService;

    @Autowired
    SeekerService seekerService;

    @GetMapping("/educations")
    public List<ItemEducationDTO> getEducations() {
        return itemEducationService.getEducations().stream().map(ItemEducationDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/educations/{id}")
    public ItemEducationDTO getEducationById (@PathVariable Long id) {
        return new ItemEducationDTO(itemEducationService.getEducationsById(id));
    }

    @GetMapping("/users/current/educations")
    public List<ItemEducationDTO> getJobEducationsDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getEducations().stream().map(ItemEducationDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/educations")
    public ResponseEntity<Object> registerEducations(
            Authentication authentication,
            @RequestBody ItemEducationDTO itemEducationDTO) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (itemEducationDTO.getTitle().isEmpty() || itemEducationDTO.getSchool().isEmpty()
                || itemEducationDTO.getTypeStudy() == null || itemEducationDTO.getAreaStudy() == null
                || itemEducationDTO.getCountryEducation().isEmpty() || itemEducationDTO.getState() == null) {
            return new ResponseEntity<>("Debes completar todos los campos", HttpStatus.FORBIDDEN);
        }

        ItemEducation itemEducation = new ItemEducation(seeker1, itemEducationDTO.getTitle(),
                itemEducationDTO.getSchool(), itemEducationDTO.getTypeStudy(), itemEducationDTO.getAreaStudy(),
                itemEducationDTO.getCountryEducation(), itemEducationDTO.getState(), LocalDate.now(),
                LocalDate.of(1991, 5, 2));

        itemEducationService.saveEducations(itemEducation);
        seeker1.setFormsCompleteEducations(true);
        seekerService.saveSeeker(seeker1);

        if (seeker1.getFormsCompletePersonalData() && seeker1.getFormsCompleteEducations() && seeker1.getFormsCompleteJobExperince() && seeker1.getFormsCompleteOthersData()) {
            seeker1.setFormsComplete(true);
            seekerService.saveSeeker(seeker1);
        }

        return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);
    }

    @PatchMapping("/users/current/educations/{id}")
    public ResponseEntity<Object> updateEducations(@PathVariable Long id, Authentication authentication, @RequestBody ItemEducationDTO itemEducationDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemEducation itemEducation = itemEducationService.getEducationsById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }


        itemEducation.setTitle(itemEducationDTO.getTitle());
        itemEducation.setSchool(itemEducationDTO.getSchool());
        itemEducation.setAreaStudy(itemEducationDTO.getAreaStudy());
        itemEducation.setCountryEducation(itemEducationDTO.getCountryEducation());
        itemEducation.setInitEducation(itemEducationDTO.getInitEducation());
        itemEducation.setEndEducation(itemEducationDTO.getEndEducation());
        itemEducation.setState(itemEducationDTO.getState());
        itemEducation.setTypeStudy(itemEducationDTO.getTypeStudy());

        itemEducationService.saveEducations(itemEducation);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/current/educations/delete/{id}")
    public ResponseEntity<String> deleteEducations(@PathVariable Long id, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemEducation itemEducation = itemEducationService.getEducationsById(id);

        itemEducationService.deleteEducations(itemEducation);

        return new ResponseEntity<>("Item eliminado exitosamente", HttpStatus.CREATED);
    }
}
