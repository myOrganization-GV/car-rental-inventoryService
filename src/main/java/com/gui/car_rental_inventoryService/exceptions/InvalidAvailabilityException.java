package com.gui.car_rental_inventoryService.exceptions;

public class InvalidAvailabilityException extends RuntimeException{
    public InvalidAvailabilityException(String message) {
        super(message);
    }
}
