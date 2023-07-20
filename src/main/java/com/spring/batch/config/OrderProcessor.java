package com.spring.batch.config;


import com.spring.batch.helper.Country;
import com.spring.batch.model.Order;
import com.spring.batch.model.CsvOrderInput;
import com.spring.batch.model.PhoneNumber;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.batch.item.ItemProcessor;

import java.util.regex.Pattern;


public class OrderProcessor implements ItemProcessor<CsvOrderInput, Order> {


    @Override
    public Order process(CsvOrderInput csvOrderInput) throws Exception {

        Order order = Order.builder()
                .orderId(csvOrderInput.getId())
                .email(csvOrderInput.getEmail())
                .phoneNumber(csvOrderInput.getPhone_number())
                .parcelWeight(csvOrderInput.getParcel_weight())
                .country(getCountryName(csvOrderInput.getPhone_number()))
                .build();

        return order;
    }

    private String getCountryName(String phoneNumber){
        String country = null;
        if (StringUtils.isNotEmpty(phoneNumber) && phoneNumber.length() >= 11 ) {
            PhoneNumber phoneObj = Country.countryMap.get(phoneNumber.strip().substring(0, 3));
            country =  Pattern.matches(phoneObj.getRegex(), phoneNumber.strip())
             ? phoneObj.getCountry() : "Invalid phone";
//            Pattern.matches("^(212)\\ ?[5-9]\\d{8}$", " 212 905608793 ".strip());
//            Pattern.matches("^(237)\\ ?[2368]\\d{7,8}$", " 23721520563".strip());
//            Pattern.matches("^(251)\\ ?[1-59]\\d{8}$", " 251 543636241".strip());
//            Pattern.matches("^(256)\\ ?\\d{9}$", " 256 217813782".strip());
//            Pattern.matches("^(258) ?[28]\\d{7,8}$", " 258 852828436".strip());
        }
    return country;
    }
}
