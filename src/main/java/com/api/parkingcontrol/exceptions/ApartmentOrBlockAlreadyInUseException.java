package com.api.parkingcontrol.exceptions;

public class ApartmentOrBlockAlreadyInUseException {
    public static void throwException() {
        throw new ApiRequestException("Apartamento ou bloco já em uso");
    }
}
