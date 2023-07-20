package com.spring.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@Table(name = "`order`")
@Table(name = "orders")
@Builder
public class Order extends BaseEntity{

    private String email;
    private String phoneNumber;
    private String parcelWeight;
    private String country;
    private int orderId;

}
