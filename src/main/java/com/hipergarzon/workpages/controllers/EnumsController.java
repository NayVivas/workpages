package com.hipergarzon.workpages.controllers;

import com.hipergarzon.workpages.models.enums.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api")
public class EnumsController<T> {

    @GetMapping("/enum/yesorno")
    public List<YesOrNo> getYesOrNo() {
        return Arrays.stream(YesOrNo.values()).collect(Collectors.toList());
    }

    @GetMapping("/enum/rol")
    public List<Rol> getRol() {
        return Arrays.stream(Rol.values()).collect(Collectors.toList());
    }
}
