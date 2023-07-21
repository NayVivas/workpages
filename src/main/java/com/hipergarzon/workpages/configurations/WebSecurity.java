package com.hipergarzon.workpages.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http.authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/api/items/catalogue", "/api/items/category").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/users", "/api/login", "/register", "/confirm-account", "/forgot-password", "/confirm-reset", "/reset-password", "/api/jobvacancy", "/api/upload", "/image/**", "/api/users/seeker/{id}/state").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/users/current/**", "/users/delete").hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                        .antMatchers(HttpMethod.PATCH,  "/api/jobvacancy/{id}").hasAnyAuthority("RECRUITER", "ADMIN")
                        .antMatchers("/scripts/**", "/assets/**", "/img/**", "/files/**", "/css/**", "/index.html", "/login.html", "/register.html", "/resetPassword.html", "/reset.html", "/forgot-password.html", "successForgotPassword.html", "/forgot-password").permitAll()
                        .antMatchers("/seeker/selectOffices.html", "/seeker/vacancyDetails.html").permitAll()
                        .antMatchers("/seeker/**").hasAnyAuthority("SEEKER", "ADMIN")
                        .antMatchers("/recruiters/**").hasAnyAuthority("RECRUITER", "ADMIN")
                .antMatchers("/api/**", "/api/users", "/confirm-account", "/confirm-reset", "/forgot-password", "/reset-password").permitAll()
                        .antMatchers("/static/images/**", "/users/seeker/current/apply", "/change-password", "/change-email", "templates/forgotPassword.html", "/api/jobvacancy/{vacancyId}/applicants").hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                .antMatchers("/jobvacancy/{vacancyId}/jobapplication", "/api/jobvacancy/{id}/applications", "/api/users/{userId}/image").hasAnyAuthority("SEEKER", "RECRUITER", "ADMIN")
                .antMatchers("/api/users/recruiter/current", "/personal/admin/seekerCV.html").hasAnyAuthority("ADMIN", "RECRUITER")
                .antMatchers("/h2-console", "/rest/**", "/admin/**").hasAuthority("ADMIN")
                        .and()
                        .formLogin()
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .loginPage("/api/login");
        http.logout().logoutUrl("/api/logout");
                http.csrf().disable();
                http.headers().frameOptions().disable();
                http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
                http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));
                http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));
                http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());
    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }
    }
};
