package com.api.parkingcontrol.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResidentsModel saveResident(@RequestBody @Valid ResidentDto residentDto) {
        return residentsService.save(residentDto);
    }

    @GetMapping
    public List<ResidentsModel> getAllResidents() {
        return residentsService.findAll();
    }

    @GetMapping("/{id}")
    public ResidentsModel getOneResident(@PathVariable(value = "id") int id) {
        return residentsService.findById(id);
    }

    @PutMapping("/{id}")
    public ResidentsModel updateResident(@PathVariable(value = "id") int id,
            @RequestBody @Valid ResidentDto residentDto) {
        return residentsService.update(residentDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteResident(@PathVariable(value = "id") int id) {
       residentsService.delete(id);
        return ResponseEntity.ok().build();
    }
}
