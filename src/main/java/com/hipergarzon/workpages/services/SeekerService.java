package com.hipergarzon.workpages.services;

import com.hipergarzon.workpages.dtos.ItemFilesDTO;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;

import java.util.List;

public interface SeekerService {

    public List<Seeker> getAllSeeker();

    Seeker getSeekerById(Long id);

    Seeker findSeekerByEmail(String email);

    void saveSeeker(Seeker seeker);

    public ItemFilesDTO getUserImage(Long userId);
}