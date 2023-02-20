package com.api.parkingcontrol.exceptions;

public class EmptyParkingSpotsException {
    public static void throwException() {
        throw new ApiRequestException("Nenhuma vaga encontrada.");
    }
}
