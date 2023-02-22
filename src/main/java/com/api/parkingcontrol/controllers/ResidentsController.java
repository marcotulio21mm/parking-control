package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.ResidentDto;
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
}
