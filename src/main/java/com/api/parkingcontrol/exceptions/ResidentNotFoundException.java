package com.api.parkingcontrol.exceptions;

public class ResidentNotFoundException {
    
    public static void throwException() {
        throw new ApiRequestException("Morador não encontrado.");
    }
}
