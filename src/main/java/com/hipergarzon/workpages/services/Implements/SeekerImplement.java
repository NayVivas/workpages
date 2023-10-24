package com.hipergarzon.workpages.services.Implements;

import com.hipergarzon.workpages.dtos.ItemFilesDTO;
import com.hipergarzon.workpages.models.ItemFiles;
import com.hipergarzon.workpages.models.ItemImage;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.models.UserGeneral;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.services.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeekerImplement implements SeekerService {

    @Autowired
    SeekerRepository seekerRepository;

    @Override
    public List<Seeker> getAllSeeker() {
        return seekerRepository.findAll();
    }

    @Override
    public Seeker getSeekerById(Long id) {
        Optional<Seeker> seekerOptional = seekerRepository.findById(id);
        if (seekerOptional.isPresent()) {
            return seekerOptional.get();
        } else {
            throw new NoSuchElementException("No se encontró un Seeker con el id " + id);
        }
    }

    @Override
    public Seeker findSeekerByEmail(String email) {
        return seekerRepository.findUserByEmail(email);
    }

    @Override
    public void saveSeeker(Seeker seeker) {
        seekerRepository.save(seeker);
    }

    public ItemFilesDTO getUserImage(Long userId) {
        Seeker user = seekerRepository.findById(userId).orElseThrow(() -> new RuntimeException("No se encontró el usuario con el ID " + userId));
        ItemImage image = user.getItemImages();
        String imageUrl = image != null ? image.getUrl() : "";
        ItemFilesDTO dto = new ItemFilesDTO();
        dto.setUrl(imageUrl);
        return dto;
    }
}
