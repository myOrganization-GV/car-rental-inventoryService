package com.gui.car_rental_inventoryService.repositories;

import com.gui.car_rental_inventoryService.entities.CarImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarImageRepository extends JpaRepository<CarImage, UUID> {
}
