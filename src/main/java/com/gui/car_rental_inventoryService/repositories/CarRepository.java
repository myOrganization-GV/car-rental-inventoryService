package com.gui.car_rental_inventoryService.repositories;

import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    @Query("SELECT c FROM Car c WHERE c.model LIKE %:model%")
    List<Car> findByModel(@Param("model") String model);


    List<Car> findByCategory(Category category);

    List<Car> findByAvailabilityStatus(AvailabilityStatus availabilityStatus);
    @Query("SELECT c FROM Car c WHERE c.model LIKE %:model% AND c.availabilityStatus = :availabilityStatus ")
    List<Car> findByModelLikeAndAvailabilityStatus(String model, AvailabilityStatus availabilityStatus);


}