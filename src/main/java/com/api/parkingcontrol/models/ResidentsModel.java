package com.api.parkingcontrol.models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "RESIDENTS")
@Getter
@Setter
public class ResidentsModel implements Serializable {
    @Serial
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
}
