package com.api.parkingcontrol.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UsersDto {
    @NotBlank(message = "nome não deve estar em branco")
    private String name;
    @Email(message = "insira um email válido")
    @NotBlank(message = "email não deve estar em branco")
    private String email;
    @NotBlank(message = "senha não deve estar em branco")
    private String password;
    @NotBlank
    private String insertDateUser;
}
