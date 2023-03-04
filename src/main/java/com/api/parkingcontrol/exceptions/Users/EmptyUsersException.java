package com.api.parkingcontrol.exceptions.Users;

import com.api.parkingcontrol.exceptions.ApiRequestException;

public class EmptyUsersException {
    public static void throwException() {
        throw new ApiRequestException("Nenhum usuário encontrado.");
    }
}