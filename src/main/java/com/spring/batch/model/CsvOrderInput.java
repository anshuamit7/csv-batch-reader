package com.spring.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CsvOrderInput {

    private int id;
    private String email;
    private String phone_number;
    private String parcel_weight;


}
