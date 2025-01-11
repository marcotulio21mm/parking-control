package com.api.parkingcontrol.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.api.parkingcontrol.dtos.ResidentDto;
import com.api.parkingcontrol.exceptions.exceptiontypes.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ResidentsModel;
import com.api.parkingcontrol.repositories.ResidentsRepository;

import jakarta.transaction.Transactional;

@Service
@AllArgsConstructor
public class ResidentsService {
    private ResidentsRepository residentsRepository;

    @Transactional
    public ResidentsModel save(ResidentDto residentDto) {

        ResidentsModel residentsModel = new ResidentsModel();
        residentsModel.setInsertDateResident(LocalDateTime.now(ZoneId.of("UTC")));
        BeanUtils.copyProperties(residentDto, residentsModel);
        return residentsRepository.save(residentsModel);
    }

    @Transactional
    public ResidentsModel update(ResidentDto residentDto, int id) {
        var resident = residentsRepository.findById(id);
        if(resident.isPresent()) {
            BeanUtils.copyProperties(residentDto, resident.get());
            return residentsRepository.save(resident.get());
        }
        throw new NotFoundException("404","Morador não encontrado com id: " + id);
    }
    
    public List<ResidentsModel> findAll() {
        return residentsRepository.findAll();
    }

public ResidentsModel findById(int id) {
        var resident = residentsRepository.findById(id);
        if(resident.isPresent()) {
            return resident.get();
        }
        throw new NotFoundException("404","Morador não encontrado com id: " + id);
    }

    @Transactional
    public void delete(int id) {
        var resident = residentsRepository.findById(id);
        resident.ifPresentOrElse(residentsModel -> residentsRepository.deleteById(id), () -> {
            throw new NotFoundException("404","Morador não encontrado com id: " + id);
        });
    }
}
