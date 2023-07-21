package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.repositories.ConfirmationTokenRepository;
import com.hipergarzon.workpages.repositories.RecruiterRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.repositories.UserGeneralRepository;
import com.hipergarzon.workpages.services.UserGeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserGeneralImplement implements UserGeneralService {

    @Autowired
    UserGeneralRepository userGeneralRepository;
    @Autowired
    ConfirmationTokenRepository passwordResetTokenRepository;
    @Autowired
    SeekerRepository seekerRepository;
    @Autowired
    RecruiterRepository userRecruiterRepository;
    @Override
    public List<UserGeneral> getAllUsers() {
        return userGeneralRepository.findAll();
    }

    @Override
    public UserGeneral getUserById(Long id) {
        return userGeneralRepository.findById(id).get();
    }

    @Override
    public Optional<UserGeneral> get(Long id) {
        return userGeneralRepository.findById(id);
    }

    @Override
    public UserGeneral findUserByEmail(String email) {
        return userGeneralRepository.findUserByEmail(email);
    }

    @Override
    public void saveUser(UserGeneral userGeneral) {
        userGeneralRepository.save(userGeneral);
    }
}
