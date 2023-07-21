package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.UserGeneralDTO;
import com.hipergarzon.workpages.models.Audits;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.repositories.AuditsRepository;
import com.hipergarzon.workpages.repositories.ConfirmationTokenRepository;
import com.hipergarzon.workpages.repositories.UserGeneralRepository;
import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.services.RecruiterService;
import com.hipergarzon.workpages.services.SeekerService;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class UserGeneralController {

    @Autowired
    private UserGeneralService userGeneralService;

    @Autowired
    EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserGeneralRepository userGeneralRepository;

    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private SeekerService seekerService;

    @Autowired
    private AuditsRepository auditsRepository;

    @GetMapping("/users")
    public List<UserGeneralDTO> getAllUsers() {
        return userGeneralService.getAllUsers().stream().map(UserGeneralDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserGeneralDTO getUser(@PathVariable Long id) {
        return new UserGeneralDTO(userGeneralService.getUserById(id));
    }

    @GetMapping("/users/current")
    public UserGeneralDTO getUserDTO(Authentication authentication) {
        return new UserGeneralDTO(userGeneralService.findUserByEmail(authentication.getName()));
    }

   @PostMapping("/save/users")
    public ResponseEntity<String> saveUsers(UserGeneral userGeneral) throws IOException {

        UserGeneral existingUser = userGeneralService.findUserByEmail(userGeneral.getEmail());

        if (userGeneral.getFirstName().isEmpty() || userGeneral.getLastName().isEmpty() || userGeneral.getEmail().isEmpty() || userGeneral.getPassword().isEmpty()) {
            return new ResponseEntity<>("Missing Data", HttpStatus.FORBIDDEN);
        }

        if (!userGeneral.getEmail().contains("@") && !userGeneral.getEmail().contains(".com")) {
            return new ResponseEntity<>("El email es incorrecto", HttpStatus.BAD_REQUEST);
        }

        if (existingUser != null) {
            return new ResponseEntity<>("El email ya esta registrado", HttpStatus.FORBIDDEN);
        }

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(userGeneral.getEmail());
            mailMessage.setSubject("Registro Completo");
            mailMessage.setFrom("casa24nov@gmail.com");
            mailMessage.setText("Te has registrado con exito");

            emailService.sendEmail(mailMessage);
        Seeker seeker = new Seeker(userGeneral.getFirstName(), userGeneral.getLastName(), userGeneral.getEmail(), passwordEncoder.encode(userGeneral.getPassword()));

       Audits audit = new Audits();
       audit.setFirstName(userGeneral.getFirstName());
       audit.setLastName(userGeneral.getLastName());
       audit.setEmail(userGeneral.getEmail());
       audit.setJobCompany(null);
       audit.setDate(LocalDateTime.now());
       audit.setDescription("Registro de usuario");
       auditsRepository.save(audit);
       seekerService.saveSeeker(seeker);
       return new ResponseEntity<>("Successful registration", HttpStatus.CREATED);
    }

    @PatchMapping("/save/users")
    public ResponseEntity<Object> editUser(Authentication authentication, UserGeneral userGeneral) {

        UserGeneral userGeneral1 = userGeneralService.findUserByEmail(authentication.getName());

        if (userGeneral1 == null) {
            return new ResponseEntity<>("Usuario no autenticado", HttpStatus.FORBIDDEN);
        }

        if (userGeneral1.getFirstName() != null) {
            userGeneral1.setFirstName(userGeneral.getFirstName());
        }

        if (userGeneral1.getLastName() != null) {
            userGeneral1.setLastName(userGeneral.getLastName());
        }

        if (userGeneral1.getEmail() != null) {
            userGeneral1.setEmail(userGeneral.getEmail());
        }

        if (userGeneral1.getPassword() != null) {
            userGeneral1.setPassword(userGeneral.getPassword());
        }

        this.userGeneralService.saveUser(userGeneral1);
        return new ResponseEntity<>("Usuario modificado exitosamente", HttpStatus.CREATED);
    }

    /*@PatchMapping("/user/delete/{id}")
    public ResponseEntity<Object> deleteUser(
            Authentication authentication,
            @PathVariable Long id) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        userGeneral.setActiveUser(false);
        userGeneralService.saveUser(userGeneral);
        return new ResponseEntity<>("Cuenta eliminada exitosamente", HttpStatus.CREATED);
    }*/
}

