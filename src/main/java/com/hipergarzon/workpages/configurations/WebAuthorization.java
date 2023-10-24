/*package com.hipergarzon.workpages.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebAuthorization {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/api/items/catalogue", HttpMethod.POST.toString()), new AntPathRequestMatcher("/api/items/category", HttpMethod.POST.toString())).hasAuthority("ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/users/current/**", HttpMethod.DELETE.toString()), new AntPathRequestMatcher("/users/delete", HttpMethod.DELETE.toString())).hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/jobvacancy/{id}", HttpMethod.PATCH.toString())).hasAnyAuthority("RECRUITER", "ADMIN")
                        .requestMatchers(
                                new AntPathRequestMatcher("/scripts/**"),
                                new AntPathRequestMatcher("/assets/**"),
                                new AntPathRequestMatcher("/img/**"),
                                new AntPathRequestMatcher("/files/**"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/index.html"),
                                new AntPathRequestMatcher("/login.html"),
                                new AntPathRequestMatcher("/register.html"),
                                new AntPathRequestMatcher("/resetPassword.html"),
                                new AntPathRequestMatcher("/reset.html"),
                                new AntPathRequestMatcher("/forgot-password.html"),
                                new AntPathRequestMatcher("successForgotPassword.html"),
                                new AntPathRequestMatcher("/forgot-password")
                        ).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/seeker/selectOffices.html"),
                                new AntPathRequestMatcher("/seeker/vacancyDetails.html")).permitAll()
                        .requestMatchers(new AntPathRequestMatcher("/seeker/**")).hasAnyAuthority("SEEKER", "ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/recruiters/**")).hasAnyAuthority("RECRUITER", "ADMIN")
                        .requestMatchers(new AntPathRequestMatcher("/api/**"),
                                new AntPathRequestMatcher("/api/users"),
                                new AntPathRequestMatcher("/confirm-account"),
                                new AntPathRequestMatcher("/confirm-reset"),
                                new AntPathRequestMatcher("/forgot-password"),
                                new AntPathRequestMatcher("/reset-password")).permitAll()
                        .requestMatchers(
                                new AntPathRequestMatcher("/static/images/**"),
                                new AntPathRequestMatcher("/users/seeker/current/apply"),
                                new AntPathRequestMatcher("/change-password"),
                                new AntPathRequestMatcher("/change-email"),
                                new AntPathRequestMatcher("templates/forgotPassword.html"),
                                new AntPathRequestMatcher("/api/jobvacancy/{vacancyId}/applicants")
                        ).hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                        .requestMatchers(
                                new AntPathRequestMatcher("/jobvacancy/{vacancyId}/jobapplication"),
                                new AntPathRequestMatcher("/api/jobvacancy/{id}/applications"),
                                new AntPathRequestMatcher("/api/users/{userId}/image")
                        ).hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                        .requestMatchers(
                                new AntPathRequestMatcher("/api/users/recruiter/current"),
                                new AntPathRequestMatcher("/personal/admin/seekerCV.html")
                        ).hasAnyAuthority("ADMIN", "RECRUITER")
                        .requestMatchers(
                                new AntPathRequestMatcher("/h2-console"),
                                new AntPathRequestMatcher("/rest/**"),
                                new AntPathRequestMatcher("/admin/**")
                        ).hasAuthority("ADMIN")
                )
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}*/
