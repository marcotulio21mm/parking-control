package com.api.parkingcontrol.exceptions;

public class ParkingSpotNotFoundException {
    
    public static void throwException() {
        throw new ApiRequestException("Vaga não encontrada");
    }
}
