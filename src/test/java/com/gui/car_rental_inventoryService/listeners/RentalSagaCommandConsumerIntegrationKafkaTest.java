package com.gui.car_rental_inventoryService.listeners;

import com.gui.car_rental_common.commands.ReserveCarCommand;
import com.gui.car_rental_common.dtos.BookingDto;
import com.gui.car_rental_common.events.inventory.CarReservedEvent;
import com.gui.car_rental_inventoryService.entities.Car;
import com.gui.car_rental_inventoryService.enums.AvailabilityStatus;
import com.gui.car_rental_inventoryService.enums.Category;
import com.gui.car_rental_inventoryService.repositories.CarRepository;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.kafka.ConfluentKafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Testcontainers
@DirtiesContext
public class RentalSagaCommandConsumerIntegrationKafkaTest{


    @Container
    private static final ConfluentKafkaContainer kafka = new ConfluentKafkaContainer(
            DockerImageName.parse("confluentinc/cp-kafka:7.5.3"));

    @DynamicPropertySource
    static void kafkaProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.kafka.bootstrap-servers", kafka::getBootstrapServers);
    }

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
    @Autowired
    private CarRepository carRepository;
    private Consumer<String, Object> eventConsumer;
    private final String COMMAND_TOPIC = "rental-saga-inventory-commands";
    private final String EVENT_TOPIC = "inventory-service-events";



    @BeforeEach
    void setUp(){
        carRepository.deleteAll();
        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafka.getBootstrapServers());
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        eventConsumer = new KafkaConsumer<>(consumerProps);
        eventConsumer.subscribe(Collections.singletonList(EVENT_TOPIC));
    }

    @AfterEach
    void tearDown() {
        if (eventConsumer != null) {
            eventConsumer.close();
        }
    }

    @Test
    void testReserveCarCommand_Success(){
        Car car = new Car();
        car.setManufacturer("Toyota");
        car.setCategory(Category.SUV);
        car.setYear(2022);
        car.setColor("Black");
        car.setAvailabilityStatus(AvailabilityStatus.AVAILABLE);
        car.setTransmissionType("Automatic");
        car.setModel("RAV4");
        car.setNumberOfSeats(5);
        car.setNumberOfDoors(4);
        car.setPricePerDay(BigDecimal.valueOf(150.00));
        carRepository.save(car);

        UUID sagaId = UUID.randomUUID();
        BookingDto dto = new BookingDto();
        dto.setCarId(car.getCarId());

        ReserveCarCommand cmd = new ReserveCarCommand( dto,sagaId);
        kafkaTemplate.send(COMMAND_TOPIC, cmd);

        Awaitility.await()
                .atMost(Duration.ofSeconds(10))
                .untilAsserted(() -> {
                    ConsumerRecord<String, Object> record =
                            KafkaTestUtils.getSingleRecord(eventConsumer, EVENT_TOPIC);

                    assertThat(record.value()).isInstanceOf(CarReservedEvent.class);
                    CarReservedEvent event = (CarReservedEvent) record.value();
                    assertThat(event.getSagaTransactionId()).isEqualTo(sagaId);
                    assertThat(event.getBookingDto().getPricePerDay())
                            .isEqualByComparingTo(car.getPricePerDay());
                });
    }


    private BookingDto createBookingDto(UUID carId) {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setCarId(carId);
        return bookingDto;
    }
}
