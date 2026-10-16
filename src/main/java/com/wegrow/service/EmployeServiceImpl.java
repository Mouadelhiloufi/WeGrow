package com.wegrow.service;

import com.wegrow.exeption.ResourceAlreadyExistsException;
import com.wegrow.exeption.ResourceNotFoundException;
import com.wegrow.entity.Departement;
import com.wegrow.entity.Employe;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeRepository;
import com.wegrow.service.EmployeService;

import java.util.List;

public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;
    private final DepartementRepository departementRepository;


    public EmployeServiceImpl(EmployeRepository employeRepository, DepartementRepository departementRepository) {
        this.employeRepository = employeRepository;
        this.departementRepository = departementRepository;
    }

    @Override
    public Employe ajouterEmploye(Long departementId, Employe employe) {

        Departement departement = departementRepository.findById(departementId)
                .orElseThrow(() -> new ResourceNotFoundException("Département introuvable avec l'ID : " + departementId));

        // 2. Vérification de l'unicité du matricule (409 si déjà existant)
        if (employeRepository.existsByMatricule(employe.getMatricule())) {
            throw new ResourceAlreadyExistsException("Le matricule '" + employe.getMatricule() + "' existe déjà.");
        }

        employe.setDepartement(departement);
        return employeRepository.save(employe);
    }

    @Override
    public List<Employe> getEmployesParDepartement(Long departementId) {
        // Vérification de l'existence du département (404 si introuvable)
        if (!departementRepository.existsById(departementId)) {
            throw new ResourceNotFoundException("Département introuvable avec l'ID : " + departementId);
        }

        return employeRepository.findByDepartementId(departementId);
    }
}