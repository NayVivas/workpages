package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.services.EmailService;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    UserGeneralService userGeneralService;

    @Autowired
    EmailService emailService;

    @RequestMapping("/send")
    @ResponseBody
    public String sendEmail(Authentication authentication) {

        UserGeneral userGeneral = userGeneralService.findUserByEmail(authentication.getName());

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userGeneral.getEmail());
        mailMessage.setSubject("Registro Completo");
        mailMessage.setFrom("casa24nov@gmail.com");
        mailMessage.setText("Has enviado tu cv con exito");
        String[] text = new String[3];
        emailService.sendEmail(mailMessage);
        return "";
    }
}
