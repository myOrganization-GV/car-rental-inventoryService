package com.gui.car_rental_inventoryService.listeners;

import com.gui.car_rental_common.commands.CancelCarReservationCommand;
import com.gui.car_rental_common.commands.ReserveCarCommand;
import com.gui.car_rental_common.events.inventory.CancelCarReservationFailedEvent;
import com.gui.car_rental_common.events.inventory.CarReservationCancelledEvent;
import com.gui.car_rental_common.events.inventory.CarReservationFailedEvent;
import com.gui.car_rental_common.events.inventory.CarReservedEvent;
import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = "rental-saga-inventory-commands", groupId = "inventory-service-group")
public class RentalSagaCommandConsumer {
    private static final Logger logger = LoggerFactory.getLogger(RentalSagaCommandConsumer.class);

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CarService carService;


    public RentalSagaCommandConsumer(CarService carService, KafkaTemplate<String, Object> kafkaTemplate){
        this.carService = carService;
        this.kafkaTemplate = kafkaTemplate;
    }

    @KafkaHandler
    public void consumeReserveCarCommand(ReserveCarCommand command) {
        logger.info("Received ReserveCarCommand: {}", command);
        try {
           Car reservedCar = carService.reserveCar(command.getBookingDto().getCarId(), command.getSagaTransactionId());

           CarReservedEvent event = new CarReservedEvent(
                   command.getSagaTransactionId(),
                   command.getBookingDto(),
                   reservedCar.getPricePerDay()
            );
            logger.info("Reservation completed sending carReservedEvent with saga Id: {}", event.getSagaTransactionId());
            kafkaTemplate.send("inventory-service-events", event);

        } catch (Exception e) {
            logger.error("Error processing ReserveCarCommand: {}", e.getMessage(), e);

            CarReservationFailedEvent event = new CarReservationFailedEvent(
                    command.getBookingDto(),
                    command.getSagaTransactionId(),
                    e.getMessage()
            );
            kafkaTemplate.send("inventory-service-events", event);

            logger.error("Car reservation failed: {}", e.getMessage());
        }
    }
    @KafkaHandler
    public void consumeCancelCarReservationCommand(CancelCarReservationCommand command) {
        logger.info("Received CancelCarReservationCommand: {}", command);
        try {
            carService.cancelCarReservation(command.getBookingDto().getCarId());
            CarReservationCancelledEvent cancelEvent = new CarReservationCancelledEvent();
            cancelEvent.setBookingDto(command.getBookingDto());
            cancelEvent.setSagaTransactionId(command.getSagaTransactionId());
            kafkaTemplate.send("inventory-service-events", cancelEvent);

        } catch (Exception e) {
            logger.error("Error processing CancelCarReservationCommand: {}", e.getMessage(), e);

            CancelCarReservationFailedEvent event = new CancelCarReservationFailedEvent(
                    command.getSagaTransactionId(),
                    command.getBookingDto(),
                    e.getMessage()
            );
            kafkaTemplate.send("inventory-service-events", event);

            logger.error("Cancel car reservation failed {}.Car still reserved", e.getMessage());
        }
    }
}
