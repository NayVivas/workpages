package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.repositories.RecruiterRepository;
import com.hipergarzon.workpages.services.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecruiterImplement implements RecruiterService {

    @Autowired
    RecruiterRepository userRecruiterRepository;
    @Override
    public List<Recruiter> getAllRecruiters() {
        return userRecruiterRepository.findAll();
    }

    @Override
    public Recruiter getRecruiterById(Long id) {
        return userRecruiterRepository.findById(id).get();
    }

    @Override
    public Optional<Recruiter> getOptionalRecruiter(Long id) {
        return userRecruiterRepository.findById(id);
    }

    @Override
    public Recruiter findRecruiterByEmail(String email) {
        return userRecruiterRepository.findRecruiterByEmail(email);
    }
    @Override
    public void saveRecruiter(Recruiter userRecruiter) {
        userRecruiterRepository.save(userRecruiter);
    }
}
