package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.*;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class JobApplicationController {


    @Autowired
    JobApplicationService jobApplicationService;

    @Autowired
    JobVacancyService jobVacancyService;

    @Autowired
    SeekerService seekerService;

    @Autowired
    EmailService emailService;

    @GetMapping("/jobapplications")
    public List<JobApplicationDTO> getAllJobApplication() {
        return jobApplicationService.getAllJobApplications().stream().map(JobApplicationDTO::new).collect(Collectors.toList());
    }

    /*@GetMapping("/apply/jobapplication/{id}")
    public JobApplicationDTO getJobApplication(@PathVariable Long id) {
        return new JobApplicationDTO(jobApplicationService.getIdByJobApplicatio(id));
    }*/

    @GetMapping("/users/seeker/current/apply")
    public List<JobApplicationDTO> getJobApplicationDto(Authentication authentication) {
        Seeker seeker1 = seekerService.findSeekerByEmail(authentication.getName());
        return seeker1.getJobApplications().stream().map(JobApplicationDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/jobapplication/{id}")
    public ResponseEntity<List<JobApplicationDTO>> getApplicantsAndApplicationsByVacancyId(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        List<JobApplication> applications = jobApplicationService.getJobVacancyApplications(id);

        List<JobApplicationDTO> applicationDTOs = applications.stream().map(application -> {
            JobApplicationDTO applicationDTO = new JobApplicationDTO();

            applicationDTO.setStateApplication(application.getStateApplication());
            applicationDTO.setId(application.getId());
            applicationDTO.setLevelExperience(application.getJobVacancy().getLevelExperience());
            applicationDTO.setBranchOffice(application.getJobVacancy().getBranchOffice());
            applicationDTO.setJobVacancyTitle(application.getJobVacancy().getJobVacancyTitle());
            applicationDTO.setExperienceRequired(application.getJobVacancy().getExperienceRequired());
            applicationDTO.setJobVacancySkills(application.getJobVacancy().getJobVacancySkills());

            Seeker seeker = application.getSeeker();
            ItemPersonalDataDTO personalData = seeker.getPersonalData().stream().findFirst()
                    .map(data -> new ItemPersonalDataDTO(data.getFirstName(), data.getLastName(), data.getEmail(), data.getDocument(),
                            data.getCivilStatus(), data.getChildren(), data.getCityResidence(), data.getGender())).orElse(null);

            List<ItemEducationDTO> educations = seeker.getEducations().stream()
                    .map(edu -> new ItemEducationDTO(edu.getTitle()))
                    .collect(Collectors.toList());

            List<ItemOthersDataDTO> otherData = seeker.getOthersData().stream()
                    .map(otherD -> new ItemOthersDataDTO(otherD.getSalary(), otherD.getBranchOffice(), otherD.getJobWeekend(),
                            otherD.getRotary(), otherD.getTransport()))
                    .collect(Collectors.toList());

            List<ItemSkillsDTO> skills = seeker.getSkills().stream().map(skill -> new ItemSkillsDTO(skill.getId(), skill.getSoftSkills(), skill.getTechnicalSkills())).collect(Collectors.toList());

            List<ItemLanguagesDTO> languages = seeker.getLanguages().stream().map(language -> new ItemLanguagesDTO(language.getLanguages(), language.getId(), language.getOralLevel(), language.getWriteLevel())).collect(Collectors.toList());

            SeekerDTO seekerDTO = new SeekerDTO(seeker.getId(), seeker.getFirstName(), seeker.getLastName(), seeker.getEmail(), personalData, educations, otherData, skills, languages);
            applicationDTO.setSeeker(seekerDTO);

            return applicationDTO;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(applicationDTOs);
    }

    @PostMapping("/apply/{id}")
    public ResponseEntity<JobApplicationDTO> applyJobVacancy(@PathVariable Long id, Authentication authentication) {

        Seeker seeker = seekerService.findSeekerByEmail(authentication.getName());

        if (seeker == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/login");
            return ResponseEntity.status(HttpStatus.FOUND)
                    .headers(headers)
                    .build();
        }

        JobVacancy jobVacancy = jobVacancyService.getJobVacancyById(id);
        JobApplication jobApplication = new JobApplication();
        jobApplication.setSeeker(seeker);
        jobApplication.setJobVacancy(jobVacancy);
        jobApplicationService.saveApply(jobApplication);

        JobApplicationDTO jobApplicationDTO = new JobApplicationDTO(jobApplication);
        jobApplicationDTO.setId(jobApplication.getId());
        jobApplicationDTO.setJobVacancyTitle(jobVacancy.getJobVacancyTitle());
        jobApplicationDTO.setJobVacancyDescription(jobVacancy.getJobVacancyDescription());
        jobApplicationDTO.setJobVacancySkills(jobVacancy.getJobVacancySkills());
        jobApplicationDTO.setBranchOffice(jobVacancy.getBranchOffice());
        jobApplicationDTO.setExperienceRequired(jobApplicationDTO.getExperienceRequired());
        jobApplicationDTO.setLevelExperience(jobVacancy.getLevelExperience());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(seeker.getEmail());
        mailMessage.setSubject("Confirmación de solicitud de empleo");
        mailMessage.setFrom("tuempresa@example.com");
        mailMessage.setText("Hola " + seeker.getFirstName() + ",\n\n" +
                "Hemos recibido tu solicitud para el puesto de " + jobVacancy.getJobVacancyTitle() + " en " + jobVacancy.getBranchOffice() + ".\n\n" +
                "Te mantendremos informado sobre el progreso de tu solicitud.\n\n" +
                "Gracias,\n" +
                "El equipo de " + jobVacancy.getBranchOffice());

        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok(jobApplicationDTO);
    }

    @PatchMapping("/jobapplication/state/{id}")
    public ResponseEntity<JobApplicationDTO> updateJobApplicationState(@PathVariable Long id, @RequestBody JobApplicationDTO jobApplicationDTO) {
        JobApplication jobApplication = jobApplicationService.getIdByJobApplicatio(id);
        jobApplication.setStateApplication(jobApplicationDTO.getStateApplication());
        jobApplication = jobApplicationService.saveApply(jobApplication);
        UserGeneral userGeneral = jobApplication.getSeeker();
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userGeneral.getEmail());
        mailMessage.setSubject("Actualización de la solicitud de empleo");
        mailMessage.setFrom("casa24nov@gmail.com");
        mailMessage.setText("El estado de tu solicitud de empleo ha sido actualizado a " + jobApplicationDTO.getStateApplication());
        emailService.sendEmail(mailMessage);
        return ResponseEntity.ok(new JobApplicationDTO(jobApplication));
    }
}
