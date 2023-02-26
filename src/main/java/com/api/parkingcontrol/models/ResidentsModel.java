package com.api.parkingcontrol.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RESIDENTS")

public class ResidentsModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResident;
    @Column(nullable = false, unique = true, length = 255)
    private String nameResident;
    @Column(nullable = false, unique = true, length = 7)
    private String documentResident;
    @Column(nullable = false)
    private String apartmentResident;
    @Column(nullable = false, length = 45)
    private String blockResident;
    @Column(nullable = false, columnDefinition = "CHAR(1) DEFAULT 'T'")
    private String isActiveResident;
    @Column(nullable = false)
    private LocalDateTime insertDateResident;

    public int getIdResindent() {
        return idResident;
    }

    public void setIdResident(int idResident) {
        this.idResident = idResident;
    }

    public String getNameResident() {
        return nameResident;
    }

    public void setNameResident(String nameResident) {
        this.nameResident = nameResident;
    }

    public String getDocumentResident() {
        return documentResident;
    }

    public void setDocumentResident(String documentResident) {
        this.documentResident = documentResident;
    }

    public String getApartmentResident() {
        return this.apartmentResident;
    }

    public void setApartmentResident(String apartmentResident) {
        this.apartmentResident = apartmentResident;
    }

    public String getBlockResident() {
        return this.blockResident;
    }

    public void setBlockResident(String blockResident) {
        this.blockResident = blockResident;
    }

    public String getIsActiveResident() {
        return this.isActiveResident;
    }

    public void setIsActiveResident(String isActiveResident) {
        this.isActiveResident = isActiveResident;
    }

    public LocalDateTime getInsertDateResident() {
        return this.insertDateResident;
    }

    public void setInsertDateResident(LocalDateTime insertDateResident) {
        this.insertDateResident = insertDateResident;
    }
}
