package com.api.parkingcontrol.exceptions.Users;

import com.api.parkingcontrol.exceptions.ApiRequestException;

public class UserNotFoundException {
    public static void throwException() {
        throw new ApiRequestException("Usuário não encontrado.");
    }
}
