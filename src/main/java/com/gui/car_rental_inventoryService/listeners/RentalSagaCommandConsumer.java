package com.gui.car_rental_inventoryService.listeners;

import com.gui.car_rental_common.commands.ReserveCarCommand;
import com.gui.car_rental_inventoryService.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class RentalSagaCommandConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RentalSagaCommandConsumer.class);

    private final CarService carService;


    public RentalSagaCommandConsumer(CarService carService){
        this.carService = carService;
    }

    @KafkaListener(topics = "rental-saga-commands", groupId = "inventory-service-group")
    public void consumeReserveCarCommand(ReserveCarCommand command) {
        logger.info("Received ReserveCarCommand: {}", command);
        try {
            carService.reserveCar(command.getCarId(), command.getSagaTransactionId());
        } catch (Exception e) {
            logger.error("Error processing ReserveCarCommand: {}", e.getMessage(), e);
        }
    }
}
