package com.hipergarzon.workpages.controllers;


import com.hipergarzon.workpages.dtos.ItemOthersDataDTO;
import com.hipergarzon.workpages.dtos.ItemPersonalDataDTO;
import com.hipergarzon.workpages.dtos.ItemSkillsDTO;
import com.hipergarzon.workpages.models.ItemOthersData;
import com.hipergarzon.workpages.models.ItemSkills;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.services.ItemSkillsService;
import com.hipergarzon.workpages.services.SeekerService;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ItemSkillsController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    ItemSkillsService itemSkillsService;

    @Autowired
    SeekerService seekerService;

    @GetMapping("/skills")
    public List<ItemSkillsDTO> getSkills() {
        return itemSkillsService.getSkills().stream().map(ItemSkillsDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/skills/{id}")
    public ItemSkillsDTO getSkillById (@PathVariable Long id) {
        return new ItemSkillsDTO(itemSkillsService.getSkillsById(id));
    }

    @GetMapping("/users/current/skills")
    public List<ItemSkillsDTO> getSkillsDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getSkills().stream().map(ItemSkillsDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/skills")
    public ResponseEntity<Object> registerSkills(
            Authentication authentication,
            @RequestParam String technicalSkills, @RequestParam String softSkills) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (technicalSkills.isEmpty() || softSkills.isEmpty()) {
            return new ResponseEntity<>("Parametro vacio", HttpStatus.FORBIDDEN);
        }

        ItemSkills itemSkills = new ItemSkills(seeker1, technicalSkills, softSkills);

        itemSkillsService.saveSkills(itemSkills);
        seekerService.saveSeeker(seeker1);


        return new ResponseEntity<>("Cuenta creada con exito", HttpStatus.CREATED);
    }

    @PatchMapping("/users/current/skills/{id}")
    public ResponseEntity<Object> updateSkills(@PathVariable Long id, Authentication authentication, @RequestBody ItemSkillsDTO itemSkillsDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemSkills itemSkills = itemSkillsService.getSkillsById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }

        itemSkills.setSoftSkills(itemSkillsDTO.getSoftSkills());
        itemSkills.setTechnicalSkills(itemSkillsDTO.getTechnicalSkills());


        itemSkillsService.saveSkills(itemSkills);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @DeleteMapping("/users/current/skills/delete/{id}")
    public ResponseEntity<String> deleteSkills(@PathVariable Long id, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemSkills itemSkills = itemSkillsService.getSkillsById(id);

        itemSkillsService.deleteSkills(itemSkills);

        return new ResponseEntity<>("Item eliminado exitosamente", HttpStatus.CREATED);
    }
}
