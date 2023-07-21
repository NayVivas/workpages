package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ChangeEmailDTO;
import com.hipergarzon.workpages.dtos.ChangePasswordDTO;
import com.hipergarzon.workpages.models.*;
import com.hipergarzon.workpages.repositories.ConfirmationTokenRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.repositories.UserGeneralRepository;
import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.services.SeekerService;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@Controller
public class UserAccountController {

    @Autowired
    private UserGeneralRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private EmailService emailSenderService;

    @Autowired
    SeekerService seekerService;
    @Autowired
    SeekerRepository seekerRepository;

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        if (userGeneral == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!passwordEncoder.matches(changePasswordDTO.getCurrentPassword(), userGeneral.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La contraseña actual es incorrecta");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword())) {
            return ResponseEntity.badRequest().body("Las contraseñas nuevas no coinciden");
        }

        userGeneral.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userGeneralService.saveUser(userGeneral);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-email")
    public ResponseEntity<String> changeEmail(@RequestBody ChangeEmailDTO changeEmailDTO, Authentication authentication) {
        String currentEmail = changeEmailDTO.getCurrentEmail();
        String newEmail = changeEmailDTO.getNewEmail();

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());
        if (!userGeneral.getEmail().equals(currentEmail)) {
            return ResponseEntity.badRequest().body("El correo electrónico actual es incorrecto");
        }

        if (!changeEmailDTO.getNewEmail().equals(changeEmailDTO.getConfirmNewEmail())) {
            return ResponseEntity.badRequest().body("El correo electrónico no coincide");
        }

        userGeneral.setEmail(newEmail);
        userGeneralService.saveUser(userGeneral);

        return ResponseEntity.ok("Correo electrónico cambiado exitosamente");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotUserPassword(UserGeneral userGeneral) {
        UserGeneral existingUser = userRepository.findUserByEmail(userGeneral.getEmail());
        if (existingUser != null) {

            ConfirmationToken resetToken = new ConfirmationToken(existingUser);

            confirmationTokenRepository.save(resetToken);

            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(existingUser.getEmail());
            mailMessage.setSubject("Complete Password Reset!");
            mailMessage.setFrom("casa24nov@gmail.com");
            mailMessage.setText("To complete the password reset process, please click here: "
                    + "http://localhost:8080/confirm-reset?token=" + resetToken.getConfirmationToken());

            emailSenderService.sendEmail(mailMessage);

            return new ResponseEntity<>("Solicitud de restablecimiento de contraseña recibida. Revisa tu bandeja de entrada para ver el enlace de reinicio", HttpStatus.CREATED);

        } else {
            return new ResponseEntity<>("El email ingresado no existe en la base de datos", HttpStatus.FORBIDDEN);
        }
    }


    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView validateResetToken(ModelAndView modelAndView, @RequestParam("token") String confirmationToken) {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if (token != null) {
            Seeker seeker = seekerRepository.findUserByEmail(token.getUserGeneral().getEmail());
            seeker.setEnabled(true);
            seekerRepository.save(seeker);
            modelAndView.addObject("user", seeker);
            modelAndView.addObject("email", seeker.getEmail());
            modelAndView.setViewName("resetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }

    @PostMapping("/reset-password")
    public ModelAndView resetUserPassword(ModelAndView modelAndView, UserGeneral user) {
        if (user.getEmail() != null) {
            Seeker tokenUser = seekerRepository.findUserByEmail(user.getEmail());
            tokenUser.setEnabled(true);
            tokenUser.setPassword(passwordEncoder.encode(user.getPassword()));
            // System.out.println(tokenUser.getPassword());
            seekerRepository.save(tokenUser);
            modelAndView.addObject("message", "Password successfully reset. You can now log in with the new credentials.");
            modelAndView.setViewName("successResetPassword");
        } else {
            modelAndView.addObject("message", "The link is invalid or broken!");
            modelAndView.setViewName("error");
        }
        return modelAndView;
    }



    @PatchMapping("/users/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String password, Authentication authentication) {
        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());

        if (!passwordEncoder.matches(password, userGeneral.getPassword())) {
            return ResponseEntity.badRequest().body("La contraseña es incorrecta");
        }
        userGeneral.setActiveUser(false);
        userGeneralService.saveUser(userGeneral);

        return new ResponseEntity<>("Cuenta eliminada exitosamente", HttpStatus.CREATED);
    }


    public UserGeneralRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserGeneralRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ConfirmationTokenRepository getConfirmationTokenRepository() {
        return confirmationTokenRepository;
    }

    public void setConfirmationTokenRepository(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    public EmailService getEmailSenderService() {
        return emailSenderService;
    }

    public void setEmailSenderService(EmailService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }
}