package com.api.parkingcontrol.exceptions;

public class ParkingSpotAlreadyInUseException {
    public static void throwException() {
        throw new ApiRequestException("Vaga já em uso");
    }
}
