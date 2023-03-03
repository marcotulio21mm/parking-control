package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dtos.UsersDto;
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
}
