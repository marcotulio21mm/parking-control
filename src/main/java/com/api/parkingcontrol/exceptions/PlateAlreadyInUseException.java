package com.api.parkingcontrol.exceptions;

public class PlateAlreadyInUseException {
    public static void throwException() {
        throw new ApiRequestException("Placa já em uso");
    }
}
