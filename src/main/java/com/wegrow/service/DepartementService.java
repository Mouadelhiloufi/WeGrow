package com.wegrow.service;

import com.wegrow.dto.DepartementResponseDto;
import com.wegrow.entity.Departement;
import java.util.List;

public interface DepartementService {
    Departement createDepartement(Departement departement);
    List<DepartementResponseDto> getAllDepartements();
    void deleteDepartement(Long id);
}