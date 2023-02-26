package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.ResidentDto;
import com.api.parkingcontrol.exceptions.EmptyResidentsException;
import com.api.parkingcontrol.exceptions.ResidentNotFoundException;
import com.api.parkingcontrol.models.ResidentsModel;
import com.api.parkingcontrol.services.ResidentsService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/resident")

public class ResidentsController {
    final ResidentsService residentsService;

    public ResidentsController(ResidentsService residentsService) {
        this.residentsService = residentsService;
    }

    @PostMapping
    public ResponseEntity<Object> saveResident(@RequestBody @Valid ResidentDto residentDto) {
        var residentsModel = new ResidentsModel();
        BeanUtils.copyProperties(residentDto, residentsModel);
        residentsModel.setInsertDateResident(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(residentsService.save(residentsModel));
    }

    @GetMapping
    public ResponseEntity<List<ResidentsModel>> getAllParkingSpots() {
        ResponseEntity<List<ResidentsModel>> all = ResponseEntity.status(HttpStatus.OK)
                .body(residentsService.findAll());

        List<ResidentsModel> response = all.getBody();
        if (response.isEmpty()) {
            EmptyResidentsException.throwException();
        }
        return all;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResident(@PathVariable(value = "id") int id,
            @RequestBody @Valid ResidentDto parkingSpotDto) {
        Optional<ResidentsModel> residentsModelOptional = residentsService.findById(id);
        if (!residentsModelOptional.isPresent()) {
            ResidentNotFoundException.throwException();
        }
        var residentsModel = new ResidentsModel();
        BeanUtils.copyProperties(parkingSpotDto, residentsModel);
        residentsModel.setIdResident(residentsModelOptional.get().getIdResindent());
        residentsModel.setInsertDateResident(residentsModelOptional.get().getInsertDateResident());
        return ResponseEntity.status(HttpStatus.OK).body(residentsService.save(residentsModel));
    }
}
