package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.UsersModel;
import com.api.parkingcontrol.repositories.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class UsersService {
    final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public UsersModel save(UsersModel usersModel) {
        return usersRepository.save(usersModel);
    }
    
    public List<UsersModel> findAll() {
        return usersRepository.findAll();
    }

    public Optional<UsersModel> findById(int id) {
        return usersRepository.getUserById(id);
    }

    @Transactional
    public void delete(UsersModel usersModel) {
        usersRepository.delete(usersModel);
    }
}
