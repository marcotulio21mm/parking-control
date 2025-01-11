package com.api.parkingcontrol.repositories;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.models.UsersModel;

@Repository
public interface UsersRepository extends JpaRepository<UsersModel, Integer> {
    
    @Query
    (value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    Optional<UsersModel> getUserById(int idUser);
}
