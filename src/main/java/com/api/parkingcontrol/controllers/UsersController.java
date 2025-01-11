package com.api.parkingcontrol.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import com.api.parkingcontrol.exceptions.exceptiontypes.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.api.parkingcontrol.dtos.UsersDto;
import com.api.parkingcontrol.models.UsersModel;
import com.api.parkingcontrol.services.UsersService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/users")
@AllArgsConstructor

public class UsersController {

    private UsersService usersService;

    @PostMapping
    public UsersModel saveResident(@RequestBody @Valid UsersDto usersDto, BindingResult bindingResult) {
        return usersService.save(usersDto, bindingResult);
    }

    @GetMapping
    public List<UsersModel> getAllUsers() {
        return usersService.findAll();
    }

    @GetMapping("/{id}")
    public UsersModel getOneResident(@PathVariable(value = "id") int id) {
        try {
            return usersService.findById(id);
        } catch (NoSuchElementException e) {
            throw new NotFoundException("404","Erro ao encontrar usuario de id: " + id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public UsersModel updateResident(@PathVariable(value = "id") int id,
            @RequestBody @Valid UsersDto usersDto, BindingResult bindingResult) {
        return usersService.update(id, usersDto, bindingResult);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") int id) {
        usersService.delete(id);
        return ResponseEntity.ok().build();
    }
}
