package com.gui.car_rental_inventoryService.controllers;

import com.gui.car_rental_inventoryService.dtos.CarDto;
import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import com.gui.car_rental_inventoryService.exceptions.InvalidAvailabilityException;
import com.gui.car_rental_inventoryService.exceptions.InvalidCategoryException;
import com.gui.car_rental_inventoryService.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventory")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService){
        this.carService = carService;
    }


    @GetMapping("/status")
    public String status(){
        return "Inventory service working...";
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<Car>> getCarsByCategory(@PathVariable String category, @RequestParam(required = false) String availability) {
        try {
            Category cat = Category.valueOf(category.toUpperCase());
            List<Car> cars = carService.getCarsByCategory(cat);
            return ResponseEntity.ok(cars);
        } catch (IllegalArgumentException e) {
            throw new InvalidCategoryException("Invalid category provided: " + category + ". Allowed values are: SUV, SEDAN, VAN");
        }
    }
    @GetMapping("/cars/{carId}")
    public ResponseEntity<Car> getCarById(@PathVariable UUID carId){
        Car car = carService.getCarById(carId);
       return ResponseEntity.ok(car);
    }

    @DeleteMapping("cars/{carId}")
    public ResponseEntity deleteCarById(@PathVariable UUID carId){
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/cars")
    public ResponseEntity<List<Car>> getCarsByModel(@RequestParam(required = false) String model,
                                                    @RequestParam(required = false) String availability) {
        if(model != null && availability != null ){
            try {
                AvailabilityStatus status = AvailabilityStatus.valueOf(availability.toUpperCase());

                List<Car> cars = carService.getCarsByModelAndAvailability(model, status);
                return ResponseEntity.ok(cars);
            }catch (RuntimeException e){
                throw new InvalidAvailabilityException("Invalid availability provided: " + availability + ". Allowed values are: RENTED, AVAILABLE, MAINTENANCE");
            }
        }
        if (model != null) {
            List<Car> cars = carService.getCarByModel(model);
            return ResponseEntity.ok(cars);
        } else {
            List<Car> allCars = carService.getAllCars();
            return ResponseEntity.ok(allCars);
        }
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> createCar(@Valid @RequestBody CarDto carDto) {
        Car savedCar = carService.saveCar(carDto);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @PatchMapping("/cars/{carId}")
    public ResponseEntity<Car> patchCar(@RequestBody CarDto carDto, @PathVariable UUID carId){
        Car updatedCar = carService.patchCar(carId, carDto);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
    @PutMapping("/cars/{carId}")
    public ResponseEntity<Car> putCar(@Valid @RequestBody CarDto carDto, @PathVariable UUID carId){
        Car updatedCar = carService.putCar(carId, carDto);
        return new ResponseEntity<>(updatedCar, HttpStatus.OK);
    }
}
