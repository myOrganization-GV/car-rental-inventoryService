package com.gui.car_rental_inventoryService.dtos;

import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

public class CarDto {
    private String manufacturer;

    private Category category;

    private Integer year;

    private String color;

    private AvailabilityStatus availabilityStatus;

    private String transmissionType;

    private String model;

    private Integer numberOfSeats;

    private Integer numberOfDoors;

    private BigDecimal pricePerDay;

    public CarDto() {
    }

    private String imageUrl;

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(Integer numberOfDoors) {
        this.numberOfDoors = numberOfDoors;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarDto carDto = (CarDto) o;
        return Objects.equals(getManufacturer(), carDto.getManufacturer()) && getCategory() == carDto.getCategory() && Objects.equals(getYear(), carDto.getYear()) && Objects.equals(getColor(), carDto.getColor()) && getAvailabilityStatus() == carDto.getAvailabilityStatus() && Objects.equals(getTransmissionType(), carDto.getTransmissionType()) && Objects.equals(getModel(), carDto.getModel()) && Objects.equals(getNumberOfSeats(), carDto.getNumberOfSeats()) && Objects.equals(getNumberOfDoors(), carDto.getNumberOfDoors()) && Objects.equals(getPricePerDay(), carDto.getPricePerDay()) && Objects.equals(getImageUrl(), carDto.getImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getManufacturer(), getCategory(), getYear(), getColor(), getAvailabilityStatus(), getTransmissionType(), getModel(), getNumberOfSeats(), getNumberOfDoors(), getPricePerDay(), getImageUrl());
    }


    public Car carFromCarDto() {
        Car car = new Car();
        car.setManufacturer(this.getManufacturer());
        car.setCategory(this.getCategory());
        car.setYear(this.getYear());
        car.setColor(this.getColor());
        car.setAvailabilityStatus(this.getAvailabilityStatus());
        car.setTransmissionType(this.getTransmissionType());
        car.setModel(this.getModel());
        car.setNumberOfSeats(this.getNumberOfSeats());
        car.setNumberOfDoors(this.getNumberOfDoors());
        car.setPricePerDay(this.getPricePerDay());
        car.setImageUrl(this.getImageUrl());
        return car;
    }
}
