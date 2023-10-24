package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.models.Recruiter;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface UserGeneralService {

    public List<UserGeneral> getAllUsers();

    public UserGeneral getUserById(Long id);

    public Optional<UserGeneral> get(Long id);

    public UserGeneral findUserByEmail(String email);

    void saveUser(UserGeneral userGeneral);
}
