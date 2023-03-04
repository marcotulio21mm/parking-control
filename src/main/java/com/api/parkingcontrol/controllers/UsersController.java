package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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

import com.api.parkingcontrol.dtos.UsersDto;
import com.api.parkingcontrol.exceptions.Users.EmptyUsersException;
import com.api.parkingcontrol.exceptions.Users.UserNotFoundException;
import com.api.parkingcontrol.models.UsersModel;
import com.api.parkingcontrol.services.UsersService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")

public class UsersController {

    final UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping
    public ResponseEntity<Object> saveResident(@RequestBody @Valid UsersDto usersDto) {
        var usersModel = new UsersModel();
        BeanUtils.copyProperties(usersDto, usersModel);
        usersModel.setInsertDateUser(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.save(usersModel));
    }

    @GetMapping
    public ResponseEntity<List<UsersModel>> getAllUsers() {
        ResponseEntity<List<UsersModel>> all = ResponseEntity.status(HttpStatus.OK)
                .body(usersService.findAll());

        List<UsersModel> response = all.getBody();
        if (response.isEmpty()) {
            EmptyUsersException.throwException();
        }
        return all;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneResident(@PathVariable(value = "id") int id) {
        Optional<UsersModel> usersModelOptional = usersService.findById(id);
        if (!usersModelOptional.isPresent()) {
            UserNotFoundException.throwException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersModelOptional.get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateResident(@PathVariable(value = "id") int id,
            @RequestBody @Valid UsersDto usersDto) {
        Optional<UsersModel> usersModelOptional = usersService.findById(id);
        if (!usersModelOptional.isPresent()) {
            UserNotFoundException.throwException();
        }
        var usersModel = new UsersModel();
        BeanUtils.copyProperties(usersDto, usersModel);
        usersModel.setId(usersModelOptional.get().getId());
        usersModel.setInsertDateUser(usersModelOptional.get().getInsertDateUser());
        return ResponseEntity.status(HttpStatus.OK).body(usersService.save(usersModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") int id) {
        Optional<UsersModel> usersModelOptional = usersService.findById(id);
        if (!usersModelOptional.isPresent()) {
            UserNotFoundException.throwException();
        }
        usersService.delete(usersModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");
    }
}
