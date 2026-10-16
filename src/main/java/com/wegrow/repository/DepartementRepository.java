package com.wegrow.repository;

import com.wegrow.entity.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {


    Departement save(Departement departement);

    boolean existsByNom(String nom);




    Optional<Departement> findByNom(String nom);

    List<Departement> findAll();
}