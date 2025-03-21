package com.gui.car_rental_inventoryService.entities;

import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID carId;

    @NotNull(message = "Manufacturer is required")
    @NotBlank(message = "Manufacturer is required")
    private String manufacturer;

    @NotNull(message = "Category is required")
    @Enumerated(EnumType.STRING)
    private Category category;
    @NotNull(message = "Year is required")
    @Min(value = 2000, message = "Year must be at least 2000")
    private Integer year;
    @NotBlank(message = "Color is required")
    private String color;

    @Column(name = "availability_status")
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @NotBlank(message = "Transmission type is required")
    private String transmissionType;

    @NotBlank(message = "Model is required")
    private String model;

    @NotNull(message = "Number of seats is required")
    @Min(value = 1, message = "Number of seats must be at least 1")
    @Max(value = 10, message = "Number of seats cannot be greater than 10")
    private Integer numberOfSeats;
    @NotNull(message = "Number of doors is required")
    @Min(value = 2, message = "Number of doors must be at least 2")
    @Max(value = 5, message = "Number of doors cannot be greater than 5")
    private Integer numberOfDoors;

    @NotNull(message = "Price per day is required")
    private BigDecimal pricePerDay;

    private String imageUrl;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Car() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public UUID getCarId() {
        return carId;
    }

    public void setCarId(UUID carId) {
        this.carId = carId;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(getCarId(), car.getCarId()) && Objects.equals(getManufacturer(), car.getManufacturer()) && getCategory() == car.getCategory() && Objects.equals(getYear(), car.getYear()) && Objects.equals(getColor(), car.getColor()) && getAvailabilityStatus() == car.getAvailabilityStatus() && Objects.equals(getTransmissionType(), car.getTransmissionType()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getNumberOfSeats(), car.getNumberOfSeats()) && Objects.equals(getNumberOfDoors(), car.getNumberOfDoors()) && Objects.equals(getPricePerDay(), car.getPricePerDay()) && Objects.equals(getImageUrl(), car.getImageUrl()) && Objects.equals(getCreatedAt(), car.getCreatedAt()) && Objects.equals(getUpdatedAt(), car.getUpdatedAt());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCarId(), getManufacturer(), getCategory(), getYear(), getColor(), getAvailabilityStatus(), getTransmissionType(), getModel(), getNumberOfSeats(), getNumberOfDoors(), getPricePerDay(), getImageUrl(), getCreatedAt(), getUpdatedAt());
    }
}