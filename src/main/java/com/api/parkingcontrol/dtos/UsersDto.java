package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.NotBlank;

public class UsersDto {
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String insertDateUser;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getInsertDateUser() {
        return this.insertDateUser;
    }

    public void setInsertDateUser(String insertDateUser) {
        this.insertDateUser = insertDateUser;
    }

}
