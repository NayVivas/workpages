package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemOthersDataDTO;
import com.hipergarzon.workpages.dtos.ItemPersonalDataDTO;
import com.hipergarzon.workpages.dtos.ItemSkillsDTO;
import com.hipergarzon.workpages.models.ItemOthersData;
import com.hipergarzon.workpages.models.ItemPersonalData;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.models.enums.*;
import com.hipergarzon.workpages.services.ItemOthersDataService;
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
public class ItemOthersDataController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    ItemOthersDataService itemOthersDataService;

    @Autowired
    SeekerService seekerService;

    @GetMapping("/othersdata")
    public List<ItemOthersDataDTO> getOthersData() {
        return itemOthersDataService.getOtherData().stream().map(ItemOthersDataDTO::new).collect(Collectors.toList());
    }
    @GetMapping("/othersdata/{id}")
    public ItemOthersDataDTO getOtherDataById (@PathVariable Long id) {
        return new ItemOthersDataDTO(itemOthersDataService.getOtherDataById(id));
    }

    @GetMapping("/users/current/othersdata")
    public List<ItemOthersDataDTO> getOtherDataDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getOthersData().stream().map(ItemOthersDataDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/users/current/othersdata")
    public ResponseEntity<Object> registerOtherData(
            Authentication authentication,
            @RequestBody ItemOthersDataDTO itemOthersDataDTO) {

        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());

        if (itemOthersDataDTO.getSalary() == null || itemOthersDataDTO.getDisability() == null
                || itemOthersDataDTO.getTypeDisability() == null || itemOthersDataDTO.getJobThisCompany() == null
                || itemOthersDataDTO.getBranchOffice() == null || itemOthersDataDTO.getReasonForResignation() == null
                || itemOthersDataDTO.getStage() == null || itemOthersDataDTO.getSelectionStage() == null
                || itemOthersDataDTO.getJobAspires() == null || itemOthersDataDTO.getAvailability() == null
                || itemOthersDataDTO.getTypeJob() == null || itemOthersDataDTO.getRotary() == null
                || itemOthersDataDTO.getJobWeekend() == null || itemOthersDataDTO.getMeetPeople() == null
                || itemOthersDataDTO.getRelationship() == null || itemOthersDataDTO.getTransport() == null) {
            return new ResponseEntity<>("Parametro vacio", HttpStatus.FORBIDDEN);
        }

        ItemOthersData itemOthersData = new ItemOthersData(seeker1, itemOthersDataDTO.getSalary(),
                itemOthersDataDTO.getDisability(), itemOthersDataDTO.getTypeDisability(),
                itemOthersDataDTO.getJobThisCompany(), itemOthersDataDTO.getBranchOffice(),
                itemOthersDataDTO.getReasonForResignation(), itemOthersDataDTO.getSelectionStage(),
                itemOthersDataDTO.getStage(), itemOthersDataDTO.getJobAspires(), itemOthersDataDTO.getAvailability(),
                itemOthersDataDTO.getTypeJob(), itemOthersDataDTO.getRotary(), itemOthersDataDTO.getJobWeekend(),
                itemOthersDataDTO.getMeetPeople(), itemOthersDataDTO.getRelationship(),
                itemOthersDataDTO.getTransport());

        itemOthersDataService.saveOtherData(itemOthersData);
        seeker1.setFormsCompleteOthersData(true);
        seekerService.saveSeeker(seeker1);
        if (seeker1.getFormsCompletePersonalData() && seeker1.getFormsCompleteEducations() && seeker1.getFormsCompleteJobExperince() && seeker1.getFormsCompleteOthersData()) {
            seeker1.setFormsComplete(true);
            seekerService.saveSeeker(seeker1);
        }
        return new ResponseEntity<>("Cuenta creada con éxito", HttpStatus.CREATED);
    }


    @PatchMapping("/users/current/othersdata/{id}")
    public ResponseEntity<Object> updateOthersData(@PathVariable Long id, Authentication authentication, @RequestBody ItemOthersDataDTO itemOthersDataDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        ItemOthersData itemOthersData = itemOthersDataService.getOtherDataById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }

        itemOthersData.setSalary(itemOthersDataDTO.getSalary());
        itemOthersData.setDisability(itemOthersDataDTO.getDisability());
        itemOthersData.setTypeDisability(itemOthersDataDTO.getTypeDisability());
        itemOthersData.setJobThisCompany(itemOthersDataDTO.getJobThisCompany());
        itemOthersData.setBranchOffice(itemOthersDataDTO.getBranchOffice());
        itemOthersData.setReasonForResignation(itemOthersDataDTO.getReasonForResignation());
        itemOthersData.setStage(itemOthersDataDTO.getStage());
        itemOthersData.setSelectionStage(itemOthersDataDTO.getSelectionStage());
        itemOthersData.setJobAspires(itemOthersDataDTO.getJobAspires());
        itemOthersData.setAvailability(itemOthersDataDTO.getAvailability());
        itemOthersData.setTypeJob(itemOthersDataDTO.getTypeJob());
        itemOthersData.setRotary(itemOthersDataDTO.getRotary());
        itemOthersData.setJobWeekend(itemOthersDataDTO.getJobWeekend());
        itemOthersData.setMeetPeople(itemOthersDataDTO.getMeetPeople());
        itemOthersData.setRelationship(itemOthersDataDTO.getRelationship());
        itemOthersData.setTransport(itemOthersDataDTO.getTransport());


        itemOthersDataService.saveOtherData(itemOthersData);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }
}
