package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.AdminDTO;
import com.hipergarzon.workpages.dtos.RecruiterDTO;
import com.hipergarzon.workpages.dtos.UserGeneralDTO;
import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.models.enums.Rol;
import com.hipergarzon.workpages.repositories.AdminRepository;
import com.hipergarzon.workpages.repositories.RecruiterRepository;
import com.hipergarzon.workpages.repositories.UserGeneralRepository;
import com.hipergarzon.workpages.services.AdminService;
import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.services.RecruiterService;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserGeneralService userGeneralService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private RecruiterService recruiterService;

    private String generateRandomPassword(int length) {
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$&*./";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        sb.append(uppercaseLetters.charAt(random.nextInt(uppercaseLetters.length())));
        sb.append(lowercaseLetters.charAt(random.nextInt(lowercaseLetters.length())));
        sb.append(numbers.charAt(random.nextInt(numbers.length())));
        sb.append(specialCharacters.charAt(random.nextInt(specialCharacters.length())));
        for (int i = 1; i < length; i++) {
            String allCharacters = uppercaseLetters + lowercaseLetters + numbers + specialCharacters;
            sb.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }
        for (int i = sb.length() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = sb.charAt(i);
            sb.setCharAt(i, sb.charAt(j));
            sb.setCharAt(j, temp);
        }
        return sb.toString();
    }

    @GetMapping("/users/admin")
    public List<AdminDTO> getAllAdmins() {
        return adminService.getAllAdmins().stream().map(AdminDTO::new).collect(Collectors.toList());
    }

    @GetMapping("/users/admin/{id}")
    public AdminDTO getAdmin(@PathVariable Long id) {
        return new AdminDTO(adminService.getAdminById(id));
    }

    @GetMapping("/users/admin/current")
    public AdminDTO getAdminCurrent(Authentication authentication) {
        return new AdminDTO(adminService.findAdminByEmail(authentication.getName()));
    }

    @PostMapping("/admin/user")
    public ResponseEntity<String> addUser(@RequestBody UserGeneralDTO userDTO, Authentication authentication) {
        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        UserGeneral existingUser = userGeneralService.findUserByEmail(userDTO.getEmail());
        if (existingUser != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario ya está registrado");
        }

        String randomPassword = generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(randomPassword);

        UserGeneral newUser;

        if (userDTO.getRol() == Rol.RECRUITER) {
            newUser = new Recruiter();
        } else if (userDTO.getRol() == Rol.ADMIN) {
            newUser = new Admin();
        } else {
            newUser = new UserGeneral();
        }

        newUser.setEmail(userDTO.getEmail());
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        newUser.setRol(userDTO.getRol());
        newUser.setPassword(encodedPassword);
        newUser.setBranchOffice(userDTO.getBranchOffice());

        userGeneralService.saveUser(newUser);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newUser.getEmail());
        mailMessage.setSubject("Registro Completo");
        mailMessage.setFrom("noreply@example.com");
        mailMessage.setText("Estimado," + newUser.getFirstName() + " " +  newUser.getLastName() + "\n\n" +
                "Se han generado las siguientes credenciales de inicio de sesión:\n\n" +
                "Usuario: " + newUser.getEmail() + "\n" +
                "Contraseña: " + randomPassword + "\n\n" +
                "Saludos,\n" +
                "El equipo de RRHH");

        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok("Usuario registrado exitosamente. Se ha enviado la contraseña al correo del usuario.");
    }

    @PostMapping("/admin/administrator")
    public ResponseEntity<String> addAdministrator(@RequestBody AdminDTO adminDTO, Authentication authentication) {
        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        UserGeneral existingUser = userGeneralService.findUserByEmail(adminDTO.getEmail());
        if (existingUser != null) {
            return new ResponseEntity<>("El email ya se encuentra registrado", HttpStatus.FORBIDDEN);
        }

        String randomPassword = generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(randomPassword);

        UserGeneral newAdministrator = new Admin();
        newAdministrator.setEmail(adminDTO.getEmail());
        newAdministrator.setFirstName(adminDTO.getFirstName());
        newAdministrator.setLastName(adminDTO.getLastName());
        newAdministrator.setRol(Rol.ADMIN);
        newAdministrator.setPassword(encodedPassword);
        newAdministrator.setBranchOffice(adminDTO.getBranchOffice());

        userGeneralService.saveUser(newAdministrator);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newAdministrator.getEmail());
        mailMessage.setSubject("Registro Completo");
        mailMessage.setFrom("noreply@example.com");
        mailMessage.setText("Estimado administrador,\n\n" +
                "Se han generado las siguientes credenciales de inicio de sesión:\n" +
                "Usuario: " + newAdministrator.getEmail() + "\n" +
                "Contraseña: " + randomPassword + "\n\n" +
                "Saludos,\n" +
                "El equipo de administración");

        emailService.sendEmail(mailMessage);

        return ResponseEntity.ok("Administrador registrado exitosamente. Se ha enviado la contraseña al correo del administrador.");
    }

    @PostMapping("/admin/recruiter")
    public ResponseEntity<String> addRecruiter(@RequestBody RecruiterDTO recruiterDTO, Authentication authentication) {
        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        UserGeneral existingUser = userGeneralService.findUserByEmail(recruiterDTO.getEmail());
        if (existingUser != null) {
            return new ResponseEntity<>("El email ya se encuentra registrado", HttpStatus.FORBIDDEN);
        }

        String randomPassword = generateRandomPassword(8);
        String encodedPassword = passwordEncoder.encode(randomPassword);

        UserGeneral newUser = new Recruiter();
        newUser.setEmail(recruiterDTO.getEmail());
        newUser.setFirstName(recruiterDTO.getFirstName());
        newUser.setLastName(recruiterDTO.getLastName());
        newUser.setRol(Rol.RECRUITER);
        newUser.setBranchOffice(recruiterDTO.getBranchOffice());
        newUser.setPassword(encodedPassword);

        userGeneralService.saveUser(newUser);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(newUser.getEmail());
        mailMessage.setSubject("Registro Completo");
        mailMessage.setFrom("noreply@example.com");
        mailMessage.setText("Estimado reclutador,\n\n" +
                "Se han generado las siguientes credenciales de inicio de sesión:\n" +
                "Usuario: " + newUser.getEmail() + "\n" +
                "Contraseña: " + randomPassword + "\n\n" +
                "Saludos,\n" +
                "El equipo de reclutamiento");

        emailService.sendEmail(mailMessage);

        Map<String, Object> responseData = new HashMap<>();
        UserGeneralDTO createdUserDTO = new UserGeneralDTO(newUser);
        responseData.put("user", createdUserDTO);

        return new ResponseEntity<>("Reclutador registrado exitosamente. Se ha enviado la contraseña al correo del reclutador", HttpStatus.CREATED);
    }
    @PatchMapping("/admin/administrator/{id}")
    public ResponseEntity<String> updateAdministrator(@PathVariable Long id, @RequestBody AdminDTO adminDTO, Authentication authentication) {

        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        Optional<Admin> optionalAdmin = adminService.getOptionalAdmin(id);
        if (!optionalAdmin.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Administrador no encontrado");
        }

        Admin admin = optionalAdmin.get();

        if (adminDTO.getFirstName() != null) {
            admin.setFirstName(adminDTO.getFirstName());
        }
        if (adminDTO.getLastName() != null) {
            admin.setLastName(adminDTO.getLastName());
        }
        if (adminDTO.getBranchOffice() != null) {
            admin.setBranchOffice(adminDTO.getBranchOffice());
        }

        adminService.saveAdmin(admin);

        return new ResponseEntity<>("El administrador se modifico exitosamente", HttpStatus.CREATED);
    }

    @PatchMapping("/admin/recruiter/{id}")
    public ResponseEntity<String> updateRecruiter(@PathVariable Long id, @RequestBody RecruiterDTO recruiterDTO, Authentication authentication) {
        if (authentication == null || !authentication.getAuthorities().contains(new SimpleGrantedAuthority("ADMIN"))) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Acceso no autorizado");
        }

        Optional<Recruiter> optionalRecruiter = recruiterService.getOptionalRecruiter(id);
        if (!optionalRecruiter.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Reclutador no encontrado");
        }

        Recruiter recruiter = optionalRecruiter.get();
        if (recruiterDTO.getFirstName() != null) {
            recruiter.setFirstName(recruiterDTO.getFirstName());
        }
        if (recruiterDTO.getLastName() != null) {
            recruiter.setLastName(recruiterDTO.getLastName());
        }
        if (recruiterDTO.getBranchOffice() != null) {
            recruiter.setBranchOffice(recruiterDTO.getBranchOffice());
        }

        recruiterService.saveRecruiter(recruiter);
        return new ResponseEntity<>("El reclutador se modifico exitosamente", HttpStatus.CREATED);
    }

    @PatchMapping("/admin/active/recruiter/{id}")
    public ResponseEntity<String> updateActiveRecruiter(@PathVariable Long id, @RequestBody RecruiterDTO recruiterDTO) {
        Optional<Recruiter> optionalRecruiter = recruiterService.getOptionalRecruiter(id);
        Recruiter recruiter = optionalRecruiter.get();
        recruiter.setActiveUser(recruiterDTO.getActiveRecruiter());
        recruiterService.saveRecruiter(recruiter);
        return new ResponseEntity<>("El reclutador se activo exitosamente", HttpStatus.CREATED);
    }

    @PatchMapping("/admin/active/administrator/{id}")
    public ResponseEntity<String> updateActiveAdmin(@PathVariable Long id, @RequestBody AdminDTO adminDTO) {
        Admin admin = adminService.getAdminById(id);
        admin.setActiveUser(adminDTO.getActiveAdmin());
        adminService.saveAdmin(admin);
        return new ResponseEntity<>("El reclutador se activo exitosamente", HttpStatus.CREATED);
    }
}
