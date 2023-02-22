package com.api.parkingcontrol.services;

import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.ResidentsModel;
import com.api.parkingcontrol.repositories.ResidentsRepository;

import jakarta.transaction.Transactional;

@Service
public class ResidentsService {
    final ResidentsRepository residentsRepository;

    public ResidentsService(ResidentsRepository residentsRepository) {
        this.residentsRepository = residentsRepository;
    }

    @Transactional
    public ResidentsModel save(ResidentsModel residentsModel) {
        return residentsRepository.save(residentsModel);
    }

}
