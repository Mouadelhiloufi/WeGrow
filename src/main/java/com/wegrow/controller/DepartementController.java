package com.wegrow.controller;

import com.wegrow.dto.DepartementResponseDto;
import com.wegrow.dto.DepartementResponseDto;
import com.wegrow.entity.Departement;
import com.wegrow.service.DepartementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departements")
public class DepartementController {

    private final DepartementService departementService;

    // Injection par constructeur
    public DepartementController(DepartementService departementService) {
        this.departementService = departementService;
    }

    @PostMapping
    public ResponseEntity<Departement> createDepartement(@RequestBody Departement departement) {
        Departement created = departementService.createDepartement(departement);
        return new ResponseEntity<>(created, HttpStatus.CREATED); // 201
    }

    @GetMapping
    public ResponseEntity<List<DepartementResponseDto>> getAllDepartements() {
        List<DepartementResponseDto> list = departementService.getAllDepartements();
        return ResponseEntity.ok(list); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartement(@PathVariable("id") Long id) {
        departementService.deleteDepartement(id);
        return ResponseEntity.noContent().build(); // 204
    }
}