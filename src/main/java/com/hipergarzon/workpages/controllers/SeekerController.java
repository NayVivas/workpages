package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.SeekerDTO;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.repositories.ConfirmationTokenRepository;
import com.hipergarzon.workpages.repositories.ItemFilesRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.services.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class SeekerController {

    @Autowired
    SeekerService seekerService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/users/seekers")
    public List<SeekerDTO> getAllSeekers() {
        return seekerService.getAllSeeker().stream().map(SeekerDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/users/seekers/{id}")
    public SeekerDTO getSeeker(@PathVariable Long id) {
        return new SeekerDTO(seekerService.getSeekerById(id));
    }

    @GetMapping("/users/seekers/current")
    public SeekerDTO getSeekerDTO(Authentication authentication) {
        return new SeekerDTO(seekerService.findSeekerByEmail(authentication.getName()));
    }
}