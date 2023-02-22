package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ResidentDto {
    @NotBlank
    private String nameResident;
    @NotBlank
    @Size(max = 14)
    private String documentResident;
    @NotBlank
    private String apartmentResident;
    @NotBlank
    private String blockResident;
    @NotBlank
    private String isActiveResident;

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
        return apartmentResident;
    }

    public void setApartmentResident(String apartmentResident) {
        this.apartmentResident = apartmentResident;
    }

    public String getBlockResident() {
        return blockResident;
    }

    public void setBlockResident(String blockResident) {
        this.blockResident = blockResident;
    }

    public String getIsActiveResident() {
        return isActiveResident;
    }

    public void setIsActiveResident(String isActiveResident) {
        this.isActiveResident = isActiveResident;
    }
}
