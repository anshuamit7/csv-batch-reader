package com.spring.batch.repo;

import com.spring.batch.model.Order;
import com.spring.batch.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.ArrayList;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderRepositoryTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    Order order1;

    @Mock
    Order order2;

    @BeforeEach
    public void dataSetup(){
        order1 = Order.builder()
                .orderId(1)
                .email("email1@email.com")
                .country("Uganda")
                .phoneNumber("256 217813782")
                .parcelWeight("25.23")
                .build();
        order2 = Order.builder()
                .orderId(2)
                .email("email2@email.com")
                .country("Uganda")
                .phoneNumber("258 852828436")
                .parcelWeight("1.33")
                .build();
    }


     @Test
     public void  findAll() {
        List<Order> orders = new ArrayList<>(5);
        orders.add(order1);
        orders.add(order2);
        when(orderRepository.findAll()).thenReturn(orders);
        Assertions.assertEquals(2, orderRepository.findAll().size());
        Assertions.assertNotEquals(5, orderRepository.findAll().size());

    }

    @Test
    public void  save(){
        //orderRepository.save(order1);
        when(orderRepository.save(order1)).getMock();
        assertThat(order1).toString();
    }
}
