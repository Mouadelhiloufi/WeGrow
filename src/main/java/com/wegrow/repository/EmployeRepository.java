package com.wegrow.repository;

import com.wegrow.entity.Departement;
import com.wegrow.entity.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {


    boolean existsByMatricule(String matricule);


    Optional<Employe> findByMatricule(String matricule);


    List<Employe> findByDepId(Long departementId);

    int countByDepartementId(Long departementId);

    List<Employe> findByDepartementId(Long departementId);
}