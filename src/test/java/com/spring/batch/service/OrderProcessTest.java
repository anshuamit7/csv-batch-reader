package com.spring.batch.service;

import com.spring.batch.config.OrderProcessor;
import com.spring.batch.model.CsvOrderInput;
import com.spring.batch.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class OrderProcessTest {
    @InjectMocks
    private OrderProcessor processor;

    @Test
    public void process() throws Exception {
        Order order = Order.builder()
                .orderId(1)
                .email("test@gmail.com")
                .country("Uganda")
                .phoneNumber("256 217813782")
                .parcelWeight("25.23")
                .build();

        CsvOrderInput orderInput = CsvOrderInput.builder()
                .id(1)
                .email("test@gmail.com")
                .phone_number("256 217813782")
                .parcel_weight("25.23")
                .build();

        Assertions.assertEquals(order, processor.process(orderInput));

    }
}
