package com.gui.car_rental_inventoryService.services;

import com.gui.car_rental_inventoryService.dtos.CarDto;
import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import com.gui.car_rental_inventoryService.exceptions.CarNotFoundException;
import com.gui.car_rental_inventoryService.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {

    private final CarRepository carRepository;
    @Autowired
    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
    public List<Car> getCarsByModelAndAvailability(String model, AvailabilityStatus status){
        return  carRepository.findByModelLikeAndAvailabilityStatus(model, status);

    }
    public List<Car> getCarsByCategory(Category category){
        return  carRepository.findByCategory(category);
    }

    public Car saveCar(CarDto carDto){
        Car car = carDto.carFromCarDto();
        return carRepository.saveAndFlush(car);
    }
    public List<Car> getCarByModel(String model){
        return carRepository.findByModel(model);
    }

    public List<Car> getCarByAvailability(AvailabilityStatus availabilityStatus){
        return carRepository.findByAvailabilityStatus(availabilityStatus);
    }

    public void deleteCar(UUID uuid){
        carRepository.deleteById(uuid);
    }

    public Car getCarById(UUID carId) {
        return carRepository.findById(carId).orElseThrow(
                () -> new CarNotFoundException("Car with ID " + carId + " not found")
        );
    }
    public Car putCar(UUID carId, CarDto carDto) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with ID " + carId + " not found"));

        existingCar.setManufacturer(carDto.getManufacturer());
        existingCar.setCategory(carDto.getCategory());
        existingCar.setYear(carDto.getYear());
        existingCar.setColor(carDto.getColor());
        existingCar.setAvailabilityStatus(carDto.getAvailabilityStatus());
        existingCar.setTransmissionType(carDto.getTransmissionType());
        existingCar.setModel(carDto.getModel());
        existingCar.setNumberOfSeats(carDto.getNumberOfSeats());
        existingCar.setNumberOfDoors(carDto.getNumberOfDoors());
        existingCar.setPricePerDay(carDto.getPricePerDay());
        existingCar.setImageUrl(carDto.getImageUrl());

        return carRepository.saveAndFlush(existingCar);
    }


    public Car patchCar(UUID carId, CarDto carDto) {
        Car existingCar = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with ID " + carId + " not found"));

        if (carDto.getManufacturer() != null) {
            existingCar.setManufacturer(carDto.getManufacturer());
        }
        if (carDto.getCategory() != null) {
            existingCar.setCategory(carDto.getCategory());
        }
        if (carDto.getYear() != null) {
            existingCar.setYear(carDto.getYear());
        }
        if (carDto.getColor() != null) {
            existingCar.setColor(carDto.getColor());
        }
        if (carDto.getAvailabilityStatus() != null) {
            existingCar.setAvailabilityStatus(carDto.getAvailabilityStatus());
        }
        if (carDto.getTransmissionType() != null) {
            existingCar.setTransmissionType(carDto.getTransmissionType());
        }
        if (carDto.getModel() != null) {
            existingCar.setModel(carDto.getModel());
        }
        if (carDto.getNumberOfSeats() != null) {
            existingCar.setNumberOfSeats(carDto.getNumberOfSeats());
        }
        if (carDto.getNumberOfDoors() != null) {
            existingCar.setNumberOfDoors(carDto.getNumberOfDoors());
        }
        if (carDto.getPricePerDay() != null) {
            existingCar.setPricePerDay(carDto.getPricePerDay());
        }
        if (carDto.getImageUrl() != null) {
            existingCar.setImageUrl(carDto.getImageUrl());
        }

        return carRepository.saveAndFlush(existingCar);
    }

    public Car changeCarStatus(UUID carId, AvailabilityStatus availabilityStatus){
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with ID " + carId + " not found"));
        car.setAvailabilityStatus(availabilityStatus);
        carRepository.save(car);
        return car;
    }
}
