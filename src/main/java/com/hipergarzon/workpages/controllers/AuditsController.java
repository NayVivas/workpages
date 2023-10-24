package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.models.Audits;
import com.hipergarzon.workpages.repositories.AuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AuditsController {

    @Autowired
    private AuditsRepository auditsRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Audits>> getAllAudits() {
        List<Audits> audits = auditsRepository.findAll();
        return ResponseEntity.ok(audits);
    }

    @GetMapping("/user-audits/{id}")
    public ResponseEntity<List<Audits>> getAuditsByUser(@PathVariable Long id) {
        List<Audits> audits = auditsRepository.findByUserGeneralId(id);
        return ResponseEntity.ok(audits);
    }
}
