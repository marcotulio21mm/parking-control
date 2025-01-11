package com.api.parkingcontrol.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.models.ResidentsModel;

@Repository
public interface ResidentsRepository extends JpaRepository<ResidentsModel, Integer> {

}
