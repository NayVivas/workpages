package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.Admin;
import com.hipergarzon.workpages.models.Recruiter;

import java.util.List;
import java.util.Optional;

public interface RecruiterService {

    public List<Recruiter> getAllRecruiters();

    Recruiter getRecruiterById(Long id);
    public Optional<Recruiter> getOptionalRecruiter(Long id);

    Recruiter findRecruiterByEmail(String email);
    void saveRecruiter(Recruiter userRecruiter);
}