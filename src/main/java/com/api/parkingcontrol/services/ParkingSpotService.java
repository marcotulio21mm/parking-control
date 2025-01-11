package com.api.parkingcontrol.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.dtos.ParkingSpotDto;
import com.api.parkingcontrol.exceptions.ParkingSpotAlreadyInUseException;
import com.api.parkingcontrol.exceptions.ParkingSpotNotFoundException;
import com.api.parkingcontrol.exceptions.PlateAlreadyInUseException;
import com.api.parkingcontrol.models.ParkingSpotModel;
import com.api.parkingcontrol.repositories.ParkingSpotRepository;

import jakarta.transaction.Transactional;

@Service
public class ParkingSpotService {
    final ParkingSpotRepository parkingSpotRepository;

    public ParkingSpotService(ParkingSpotRepository parkingSpotRepository) {
        this.parkingSpotRepository = parkingSpotRepository;
    }

    @Transactional
    public ParkingSpotModel save(ParkingSpotDto parkingSpotDto) {
        this.validateExists(parkingSpotDto);
        ParkingSpotModel parkingSpotModel = this.getMapperToSave(parkingSpotDto);
        return parkingSpotRepository.save(parkingSpotModel);
    }

    private ParkingSpotModel getMapperToSave(ParkingSpotDto parkingSpotDto) {
        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, parkingSpotModel);
        parkingSpotModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return parkingSpotModel;
    }

    private void validateExists(ParkingSpotDto parkingSpotDto) {
        if (this.existsByLicensePlateCar(parkingSpotDto.getLicensePlateCar())) {
            PlateAlreadyInUseException.throwException();
        }
        if (this.existsByParkingSpotNumber(parkingSpotDto.getParkingSpotNumber())) {
            ParkingSpotAlreadyInUseException.throwException();
        }
    }

    public ParkingSpotModel updateParkingSpot(UUID id, ParkingSpotDto parkingSpotDto) {
        Optional<ParkingSpotModel> parkingSpotModelOptional = this.findById(id);
        if (!parkingSpotModelOptional.isPresent()) {
            ParkingSpotNotFoundException.throwException();
        }
        ParkingSpotModel model = this.getMapperToUpdate(parkingSpotModelOptional, id, parkingSpotDto);
        return parkingSpotRepository.save(model);
    }

    private ParkingSpotModel getMapperToUpdate(Optional<ParkingSpotModel> parkingSpotModelOptional, UUID id,
            ParkingSpotDto parkingSpotDto) {
        ParkingSpotModel model = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotDto, model);
        model.setId(parkingSpotModelOptional.get().getId());
        model.setRegistrationDate(parkingSpotModelOptional.get().getRegistrationDate());
        return model;
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSpotRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSpotNumber(String parkingSpotNumber) {
        return parkingSpotRepository.existsByParkingSpotNumber(parkingSpotNumber);
    }

    public List<ParkingSpotModel> findAll() {
        return parkingSpotRepository.findAll();
    }

    public Optional<ParkingSpotModel> findById(UUID id) {
        return parkingSpotRepository.findById(id);
    }

    @Transactional
    public void delete(ParkingSpotModel parkingSpotModel) {
        parkingSpotRepository.delete(parkingSpotModel);
    }

    public List<ParkingSpotModel> getByApartment(String apartment) {
        return parkingSpotRepository.getAllByApartment(apartment);
    }
}
