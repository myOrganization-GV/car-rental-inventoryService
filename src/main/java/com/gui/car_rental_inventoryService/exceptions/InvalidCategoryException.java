package com.gui.car_rental_inventoryService.exceptions;

public class InvalidCategoryException extends RuntimeException {
    public InvalidCategoryException(String message) {
        super(message);
    }
}