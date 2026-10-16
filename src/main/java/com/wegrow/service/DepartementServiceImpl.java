package com.wegrow.service;

import com.wegrow.Mapper.DepartementMapper;
import com.wegrow.dto.DepartementResponseDto;
import com.wegrow.entity.Departement;
import com.wegrow.exeption.ResourceAlreadyExistsException;
import com.wegrow.exeption.ResourceNotFoundException;
import com.wegrow.repository.DepartementRepository;
import com.wegrow.repository.EmployeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class DepartementServiceImpl implements DepartementService {


    private final DepartementRepository departementRepository;
    private final EmployeRepository employeRepository;

    public DepartementServiceImpl(DepartementRepository departementRepository,EmployeRepository employeRepository){
        this.departementRepository=departementRepository;
        this.employeRepository=employeRepository;
    }

    @Override
    public Departement createDepartement(Departement departement) {
        if(departementRepository.existsByNom(departement.getNom())){
            throw new ResourceAlreadyExistsException("Un département avec ce nom existe déjà.");
        }
        return departement;
    }

    @Override
    public List<DepartementResponseDto> getAllDepartements(){
        List<Departement> dep=departementRepository.findAll();
        return dep.stream().map(
                d->{
                    int count= employeRepository.countByDepartementId(d.getId());
                    DepartementMapper DM=new DepartementMapper();
                    return DM.dto(d,count);
                }
        ).collect(Collectors.toList());
    }


    @Override
    public void deleteDepartement(Long id) {
        Departement dept = departementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Département non trouvé."));

        long count = employeRepository.countByDepartementId(id);
        if (count > 0) {
            throw new ResourceAlreadyExistsException("Impossible de supprimer un département contenant des employés.");
        }

        departementRepository.delete(dept);
    }
}
