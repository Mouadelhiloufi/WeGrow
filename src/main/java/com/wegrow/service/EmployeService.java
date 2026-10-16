package com.wegrow.service;

import com.wegrow.entity.Employe;

import java.util.List;

public interface EmployeService {
    Employe ajouterEmploye(Long departementId, Employe employe);
    List<Employe> getEmployesParDepartement(Long departementId);
}
