package com.api.parkingcontrol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.tokens.Token.ID;

import com.api.parkingcontrol.models.ResidentsModel;

@Repository
public interface ResidentsRepository extends JpaRepository<ResidentsModel, ID> {
    @Query(value = "SELECT * FROM residents WHERE id_resident = ?1", nativeQuery = true)
    Optional<ResidentsModel> getResidentById(int idResident);
}
