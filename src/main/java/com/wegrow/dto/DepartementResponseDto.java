package com.wegrow.dto;

public  class  DepartementResponseDto{
        private Long id;
        private String nom;
        private String description;
        private int nombreEmployes;

        public DepartementResponseDto(){}

    public DepartementResponseDto(String description, Long id, String nom, int nombreEmployes) {
        this.description = description;
        this.id = id;
        this.nom = nom;
        this.nombreEmployes = nombreEmployes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNombreEmployes() {
        return nombreEmployes;
    }

    public void setNombreEmployes(int nombreEmployes) {
        this.nombreEmployes = nombreEmployes;
    }
}