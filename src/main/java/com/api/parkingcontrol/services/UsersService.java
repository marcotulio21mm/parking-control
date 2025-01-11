package com.api.parkingcontrol.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.api.parkingcontrol.dtos.UsersDto;
import com.api.parkingcontrol.exceptions.exceptiontypes.BadRequestException;
import com.api.parkingcontrol.exceptions.exceptiontypes.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.UsersModel;
import com.api.parkingcontrol.repositories.UsersRepository;

import jakarta.transaction.Transactional;
import org.springframework.validation.BindingResult;

@Service
@AllArgsConstructor

public class UsersService {
    private UsersRepository usersRepository;

    @Transactional
    public UsersModel save(UsersDto userDto, BindingResult bindingResult) {

        this.checkErrors(bindingResult);
        var usersModel = new UsersModel();
        BeanUtils.copyProperties(userDto, usersModel);
        usersModel.setInsertDateUser(LocalDateTime.now(ZoneId.of("UTC")));
        return usersRepository.save(usersModel);
    }
    
    public List<UsersModel> findAll() {
        return usersRepository.findAll();
    }

    public UsersModel findById(int id) {
        var user = usersRepository.getUserById(id);
        return user.orElseThrow();
    }

    @Transactional
    public UsersModel update(int userId, UsersDto userDto, BindingResult bindingResult) {

        this.checkErrors(bindingResult);
        var user = usersRepository.getUserById(userId);
        if(user.isPresent()){
            return copyAndSave(userDto, user.get());
        }
        throw new NotFoundException("404", "Usuário não encontrado");
    }

    private void checkErrors(BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            throw new BadRequestException("400", Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
        }
    }

    private UsersModel copyAndSave(UsersDto userDto, UsersModel user){
        BeanUtils.copyProperties(userDto, user);
        return usersRepository.save(user);
    }

    @Transactional
    public void delete(int userId) {
        var user = this.findById(userId);
        Optional.of(user.getId())
                .ifPresentOrElse(usersRepository::deleteById, ()-> {
                    throw new NotFoundException("404", "Usuário não encontrado");
                });
    }
}
