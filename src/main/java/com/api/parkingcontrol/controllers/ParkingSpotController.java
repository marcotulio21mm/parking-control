package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
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

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.exceptions.EmptyParkingSpotsException;
import com.api.parkingcontrol.exceptions.ParkingSpotAlreadyInUseException;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import com.api.parkingcontrol.exceptions.PlateAlreadyInUseException;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.services.ParkingSpotService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/parking-spot")

public class ParkingSpotController {
    final ParkingSpotService parkingSpotService;

    public ParkingSpotController(ParkingSpotService parkingSpotService) {
        this.parkingSpotService = parkingSpotService;
    }

    @PostMapping
    public ResponseEntity<Object> saveParkingSpot(@RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        var parkingSpotModel = new ParkingSpotModel();
        this.validateExists(parkingSpotDto);
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSpotService.save(parkingSpotModel));
    }

    private void validateExists(ParkingSpotDto parkingSpotDto) {
        if (parkingSpotService.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            PlateAlreadyInUseException.throwException();
        }
        if (parkingSpotService.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            ParkingSpotAlreadyInUseException.throwException();
        }
    }

    @GetMapping
    public ResponseEntity<List<ParkingSpotModel>> getAllParkingSpots() {
        ResponseEntity<List<ParkingSpotModel>> all = ResponseEntity.status(HttpStatus.OK)
                .body(parkingSpotService.findAll());

        List<ParkingSpotModel> response = all.getBody();
        if (response.isEmpty()) {
            EmptyParkingSpotsException.throwException();
        }
        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            ParkingSpotNotFoundException.throwException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteParkingSpot(@PathVariable(value = "id") UUID id) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            ParkingSpotNotFoundException.throwException();
        }
        parkingSpotService.delete(parkingSpotModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateParkingSpot(@PathVariable(value = "id") UUID id,
            @RequestBody @Valid ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = parkingSpotService.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            ParkingSpotNotFoundException.throwException();
        }
        var parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setId(parkingSpotModelOptional.get().getId());
        parkingSpotModel.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping("/apartment/{apartment}")
    public ResponseEntity<List<ParkingSpotModel>> getParkingSpotsByApartment(@PathVariable(value = "apartment") String apartment) {
        ResponseEntity<List<ParkingSpotModel>> all = ResponseEntity.status(HttpStatus.OK)
                .body(parkingSpotService.getByApartment(apartment));

        List<ParkingSpotModel> response = all.getBody();
        if (response.isEmpty()) {
            EmptyParkingSpotsException.throwException();
        }
        return all;
    }
}