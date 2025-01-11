package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
}
