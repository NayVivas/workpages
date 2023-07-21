package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.dtos.ItemFilesDTO;
import com.hipergarzon.workpages.models.ItemFiles;
import com.hipergarzon.workpages.models.ItemImage;
import com.hipergarzon.workpages.models.Seeker;
import com.hipergarzon.workpages.repositories.ItemFilesRepository;
import com.hipergarzon.workpages.repositories.ItemImageRepository;
import com.hipergarzon.workpages.repositories.SeekerRepository;
import com.hipergarzon.workpages.services.SeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ItemFilesController {

    @Autowired
    SeekerService seekerService;
    @Autowired
    ItemFilesRepository itemFilesRepository;

    @Autowired
    ItemImageRepository itemImageRepository;

    @Autowired
    SeekerRepository seekerRepository;

    @GetMapping("/users/image")
    public ResponseEntity<byte[]> getUserImage(Authentication authentication) {
        Seeker seeker = seekerService.findSeekerByEmail(authentication.getName());
        try {
            ItemFilesDTO userImage = seekerService.getUserImage(seeker.getId());
            byte[] imageContent = userImage.getContent();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{id}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) throws IOException {
        Seeker seeker = seekerRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seeker not found with id: " + id));
        ItemFiles itemFiles = seeker.getItemFiles();

        String fileName = itemFiles.getFileName();
        Path filePath = Paths.get("src/main/resources/static/files/" + id + "/" + fileName);
        byte[] fileContent = Files.readAllBytes(filePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.attachment().filename(fileName).build());

        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(Authentication authentication, @RequestParam("file") MultipartFile file) {
        try {
            Seeker seeker = seekerService.findSeekerByEmail(authentication.getName());
            Long userId = seeker.getId();
            String pathString = "src/main/resources/static/images/" + userId.toString();
            Path path = Paths.get(pathString);
            Files.createDirectories(path);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + fileName;

            Seeker seeker1 = seekerService.getSeekerById(userId);

            if(seeker1.getItemImages() != null) {
                String oldFileName = seeker1.getItemImages().getFileName();
                Path oldPath = Paths.get(pathString + "/" + oldFileName);
                Files.deleteIfExists(oldPath);
                itemImageRepository.deleteById(seeker1.getItemImages().getId());
            }

            ItemImage itemImage = new ItemImage();
            itemImage.setFileName(uniqueFileName);
            itemImage.setFileType(file.getContentType());
            itemImage.setSeeker(seeker);

            String url = ServletUriComponentsBuilder.fromPath("/static/images/" + userId.toString() + "/")
                    .path(uniqueFileName)
                    .toUriString();
            itemImage.setUrl(url);

            itemImageRepository.save(itemImage);

            seeker1.setItemImages(itemImage);
            seekerService.saveSeeker(seeker1);

            Files.copy(file.getInputStream(), path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok().build();
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/upload/files")
    public ResponseEntity<ItemFilesDTO> uploadFile(Authentication authentication, @RequestParam("file") MultipartFile file) {
        try {
            Seeker seeker = seekerService.findSeekerByEmail(authentication.getName());
            Long userId = seeker.getId();
            String pathString = "src/main/resources/static/files/" + userId.toString();
            Path path = Paths.get(pathString);
            Files.createDirectories(path);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = fileName;

            Seeker seeker1 = seekerService.getSeekerById(userId);

            if(seeker1.getItemFiles() != null) {
                String oldFileName = seeker1.getItemFiles().getFileName();
                Path oldPath = Paths.get(pathString + "/" + oldFileName);
                Files.deleteIfExists(oldPath);
                    itemFilesRepository.deleteById(seeker1.getItemFiles().getId());
            }

            ItemFiles itemFile = new ItemFiles();
            itemFile.setFileName(uniqueFileName);
            itemFile.setFileType(file.getContentType());
            itemFile.setSeeker(seeker);

            String url = ServletUriComponentsBuilder.fromPath("/static/files/" + userId.toString() + "/")
                    .path(uniqueFileName)
                    .toUriString();
            itemFile.setUrl(url);

            itemFilesRepository.save(itemFile);

            seeker1.setItemFiles(itemFile);
            seekerService.saveSeeker(seeker1);

            Files.copy(file.getInputStream(), path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

            return ResponseEntity.ok().build();
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

@PatchMapping("/update-image")
    public ResponseEntity<ItemFilesDTO> updateImage(Authentication authentication, @RequestParam("file") MultipartFile file) {
        try {
            Seeker seeker = seekerService.findSeekerByEmail(authentication.getName());
            Long userId = seeker.getId();

            ItemImage itemImage = seeker.getItemImages();
            if (itemImage != null) {
                Files.deleteIfExists(Paths.get(itemImage.getFileName()));
                itemImageRepository.deleteById(itemImage.getId());
            }

            String pathString = "src/main/resources/static/images/" + userId.toString();
            Path path = Paths.get(pathString);
            Files.createDirectories(path);

            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueFileName = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_" + fileName;

            itemImage = new ItemImage();
            itemImage.setFileName(uniqueFileName);
            itemImage.setFileType(file.getContentType());
            itemImage.setSeeker(seeker);

            String url = ServletUriComponentsBuilder.fromPath("/static/images/" + userId.toString() + "/")
                    .path(uniqueFileName)
                    .toUriString();
            itemImage.setUrl(url);

            itemImageRepository.save(itemImage);

            Files.copy(file.getInputStream(), path.resolve(uniqueFileName), StandardCopyOption.REPLACE_EXISTING);

            ItemFilesDTO itemFilesDto = new ItemFilesDTO();
            itemFilesDto.setFileName(itemImage.getFileName());
            itemFilesDto.setFileType(itemImage.getFileType());
            itemFilesDto.setUrl(itemImage.getUrl());

            return ResponseEntity.ok(itemFilesDto);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
