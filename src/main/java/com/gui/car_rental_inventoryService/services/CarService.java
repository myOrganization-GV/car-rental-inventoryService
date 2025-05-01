package com.gui.car_rental_inventoryService.services;

import com.gui.car_rental_inventoryService.dtos.CarDto;
import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.entities.CarImage;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import com.gui.car_rental_inventoryService.exceptions.CarNotFoundException;
import com.gui.car_rental_inventoryService.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    public CarService(CarRepository carRepository, KafkaTemplate<String,Object> kafkaTemplate){
        this.carRepository = carRepository;
        this.kafkaTemplate = kafkaTemplate;
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
    public List<Car> getCarByManufacturer(String manufacturer){
        return carRepository.findByManufacturer(manufacturer);
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
        existingCar.getImages().clear();
        if (carDto.getImageUrls() != null) {
            carDto.getImageUrls().stream()
                    .map(url ->{
                        CarImage image = new CarImage();
                        image.setImageUrl(url);
                        image.setCar(existingCar);
                        return image;
                    }).forEach(existingCar::addImage);
        }

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
        if (carDto.getImageUrls() != null) {
            existingCar.getImages().clear();

            carDto.getImageUrls().stream()
                    .map(url -> {
                        CarImage image = new CarImage();
                        image.setImageUrl(url);
                        image.setCar(existingCar);
                        return image;
                    })
                    .forEach(existingCar::addImage);
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


    public Car reserveCar(UUID carId, UUID sagaTransactionId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with ID " + carId + " not found"));
        if (car.getAvailabilityStatus() == AvailabilityStatus.AVAILABLE) {
            car.setAvailabilityStatus(AvailabilityStatus.RESERVED);
            return carRepository.save(car);
        } else {
            throw new IllegalStateException("Car with ID " + carId + " is not available for reservation. Current status: " + car.getAvailabilityStatus());
        }
    }

    public void cancelCarReservation(UUID carId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new CarNotFoundException("Car with ID " + carId + " not found"));
        if (car.getAvailabilityStatus() == AvailabilityStatus.RESERVED) {
            car.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
            carRepository.save(car);
        } else {
            throw new IllegalStateException("Car with ID " + carId + " is not Reserved. Current status: " + car.getAvailabilityStatus());
        }
    }


}
