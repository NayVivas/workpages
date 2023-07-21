package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.RecruiterDTO;
import com.hipergarzon.workpages.dtos.SeekerDTO;
import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.repositories.AdminRepository;
import com.hipergarzon.workpages.repositories.ConfirmationTokenRepository;
import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RecruiterController {

    @Autowired
    RecruiterService userRecruiterService;

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    ConfirmationTokenRepository confirmationTokenRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/users/recruiters")
    public List<RecruiterDTO> getAllRecruiters() {
        return userRecruiterService.getAllRecruiters().stream().map(RecruiterDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/users/recruiter/{id}")
    public RecruiterDTO getRecruiter(@PathVariable Long id) {
        return new RecruiterDTO(userRecruiterService.getRecruiterById(id));
    }

    @GetMapping("/users/recruiter/current")
    public RecruiterDTO getRecruiterCurrent(Authentication authentication) {
        return new RecruiterDTO(userRecruiterService.findRecruiterByEmail(authentication.getName()));
    }
}
