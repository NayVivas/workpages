package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.*;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.repositories.JobApplicationRepository;
import com.hipergarzon.workpages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JobVacancyController {

    @Autowired
    JobVacancyService jobVacancyService;

    @Autowired
    RecruiterService recruiterService;

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @GetMapping("/jobvacancy")
    public List<JobVacancyDTO> getAllJobVacancies() {
        return jobVacancyService.getAllJobVacancies().stream().map(JobVacancyDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/jobvacancy/{id}")
    public JobVacancyDTO getJobVacancyById (@PathVariable Long id) {
        return new JobVacancyDTO(jobVacancyService.getJobVacancyById(id));
    }


   /* @GetMapping("/jobvacancy/{id}/applications")
    public List<ApplicationSeekerDTO> showJobVacancyApplications(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        List<JobApplication> applications = jobApplicationService.getJobVacancyApplications(id);
        List<ApplicationSeekerDTO> applicationDTOs = new ArrayList<>();

        for (JobApplication application : applications) {
            ApplicationSeekerDTO applicationDTO = new ApplicationSeekerDTO();
            applicationDTO.setStateJobApplication(application.getStateApplication() != null ? application.getStateApplication() : "Recibido");
            applicationDTO.setSeekerId(application.getSeeker().getId());
            applicationDTO.setSeekerName(application.getSeeker().getFirstName());
            applicationDTO.setSeekerLastName(application.getSeeker().getLastName());
            applicationDTO.setSeekerEmail(application.getSeeker().getEmail());

            applicationDTOs.add(applicationDTO);
        }

        return applicationDTOs;
    }*/

    @GetMapping("/jobvacancies")
    public List<JobVacancyDTO> getJobVacanciesByBranchOffice(@RequestParam String branchOffice) {
        List<JobVacancy> jobVacancies = jobVacancyService.getJobVacanciesByBranchOffice(branchOffice);
        return jobVacancies.stream().map(JobVacancyDTO::new).collect(Collectors.toList());
    }

    @PostMapping("/recruiter/jobVacancy")
    public ResponseEntity<String> createJobVacancy(@RequestBody JobVacancyDTO jobVacancyDTO, Authentication authentication) {
        Recruiter recruiter = recruiterService.findRecruiterByEmail(authentication.getName());
        if (recruiter == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        JobVacancy newJobVacancy = new JobVacancy();
        newJobVacancy.setJobVacancyTitle(jobVacancyDTO.getJobVacancyTitle());
        newJobVacancy.setJobVacancyDescription(jobVacancyDTO.getJobVacancyDescription());
        newJobVacancy.setJobVacancySkills(jobVacancyDTO.getJobVacancySkills());
        newJobVacancy.setLevelExperience(jobVacancyDTO.getLevelExperience());
        newJobVacancy.setExperienceRequired(jobVacancyDTO.getExperienceRequired());
        newJobVacancy.setBranchOffice(recruiter.getBranchOffice());
        newJobVacancy.setRecruiter(recruiter);
        newJobVacancy.setPostedDate(LocalDate.now());
        newJobVacancy.setPostedBy(recruiter.getFirstName() + " " + recruiter.getLastName());

        jobVacancyService.saveJobVacancy(newJobVacancy);
        recruiterService.saveRecruiter(recruiter);
        return new ResponseEntity<>("Vacante registrada con exito", HttpStatus.CREATED);
    }


    /*@PostMapping("/jobvacancy/{id}")
    public ResponseEntity<?> addVacancyAdmin(@PathVariable Long id, @RequestBody JobVacancyDTO jobVacancyDTO) {
        Recruiter recruiter = recruiterService.getRecruiterById(id);

        if(recruiter == null) {
            return ResponseEntity.notFound().build();
        }

        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setJobVacancyTitle(jobVacancyDTO.getJobVacancyTitle());
        jobVacancy.setJobVacancyDescription(jobVacancyDTO.getJobVacancyDescription());
        jobVacancy.setJobVacancySkills(jobVacancyDTO.getJobVacancySkills());
        jobVacancy.setExperienceRequired(jobVacancyDTO.getExperienceRequired());
        jobVacancy.setLevelExperience(jobVacancyDTO.getLevelExperience());
        jobVacancy.setRecruiter(recruiter);

        JobVacancy jobVacancy1 = jobVacancyService.addVacanvy(jobVacancy);

        return ResponseEntity.status(HttpStatus.CREATED).body(jobVacancy1);
    }*/


    @PatchMapping("/jobvacancy/{id}")
    public ResponseEntity<Object> updateJobVacancy(@PathVariable Long id, Authentication authentication, @RequestBody JobVacancyDTO jobVacancyDTO) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        JobVacancy jobVacancy = jobVacancyService.getJobVacancyById(id);

        if(userGeneral == null){
            return new ResponseEntity<>("No estas autorizado para actualizar la información",HttpStatus.FORBIDDEN);
        }

        jobVacancy.setJobVacancyTitle(jobVacancyDTO.getJobVacancyTitle());
        jobVacancy.setJobVacancyDescription(jobVacancyDTO.getJobVacancyDescription());
        jobVacancy.setJobVacancySkills(jobVacancyDTO.getJobVacancySkills());
        jobVacancy.setBranchOffice(jobVacancyDTO.getBranchOffice());
        jobVacancy.setExperienceRequired(jobVacancyDTO.getExperienceRequired());
        jobVacancy.setPostedBy(jobVacancyDTO.getPostedBy());
        jobVacancy.setVacancyActive(jobVacancyDTO.getVacancyActive());

        if (!jobVacancy.getVacancyActive()) {
            List<JobApplication> applications = jobApplicationService.findByJobVacancyId(id);
            for (JobApplication application : applications) {
                application.setVacancyActive(false);
                application.setExpirationDate(jobVacancy.getExpirationDate());
                jobApplicationService.saveApply(application);
            }
        }

        jobVacancyService.saveJobVacancy(jobVacancy);
        return new ResponseEntity<>("Los cambios han sido exitosos", HttpStatus.CREATED);
    }

    @PostMapping("/jobvacancy/{id}/active")
    public ResponseEntity<Object> activateJobVacancy(@PathVariable Long id) {
        JobVacancy jobVacancy = jobVacancyService.getJobVacancyById(id);
        jobVacancy.setVacancyActive(true);
        jobVacancy.setExpirationDate(LocalDate.now());
        jobVacancyService.saveJobVacancy(jobVacancy);
        return ResponseEntity.ok("La vacante ha sido activada exitosamente");
    }

    @PostMapping("/jobvacancy/{id}/inactive")
    public ResponseEntity<Object> deactivateJobVacancy(@PathVariable Long id) {
        JobVacancy jobVacancy = jobVacancyService.getJobVacancyById(id);
        jobVacancy.setVacancyActive(false);
        jobVacancy.setExpirationDate(LocalDate.now());
        jobVacancyService.saveJobVacancy(jobVacancy);
        return ResponseEntity.ok("La vacante ha sido desactivada exitosamente");
    }
}
