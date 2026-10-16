package com.wegrow.entity;

import jakarta.persistence.*;


@Entity
public class Employe {


    @ManyToOne(fetch =FetchType.LAZY,optional = false)
    @JoinColumn(name="departement_id",nullable = false)
    private Departement departement;


    @Enumerated(EnumType.STRING)
    private Contrat contrat;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="matricule",nullable = false,unique = true)
    private String matricule;

    @Column(name="prenom",nullable = false)
    private String prenom;

    @Column(name="nom",nullable = false)
    private String nom;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Departement getDep() {
        return departement;
    }

    public void setDep(Departement dep) {
        this.departement = dep;
    }



    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }






}
