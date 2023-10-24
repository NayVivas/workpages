package com.hipergarzon.workpages.configurations;

import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.models.enums.Rol;
import com.hipergarzon.workpages.repositories.RecruiterRepository;
import com.hipergarzon.workpages.repositories.UserGeneralRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Autowired
    UserGeneralRepository userGeneralRepository;

    @Autowired
    RecruiterRepository recruiterRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(inputEmail -> {
            UserGeneral userGeneral = userGeneralRepository.findUserByEmail(inputEmail);

            if (userGeneral != null) {
                if (userGeneral.getEmail().contains("@admin.com") || userGeneral.getRol() == Rol.ADMIN) {
                    return new User(userGeneral.getEmail(), userGeneral.getPassword(), AuthorityUtils.createAuthorityList("ADMIN"));
                } else if (userGeneral.getRol() == Rol.RECRUITER) {
                    return new User(userGeneral.getEmail(), userGeneral.getPassword(), AuthorityUtils.createAuthorityList("RECRUITER"));
                } else {
                    return new User(userGeneral.getEmail(), userGeneral.getPassword(), AuthorityUtils.createAuthorityList("SEEKER"));
                }
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputEmail);
            }
        });
    }
}