package com.api.parkingcontrol.exceptions;

public class EmptyResidentsException {
    public static void throwException() {
        throw new ApiRequestException("Nenhum morador encontrado.");
    }
}
