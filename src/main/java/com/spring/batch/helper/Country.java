package com.spring.batch.helper;

import com.spring.batch.model.PhoneNumber;

import java.util.HashMap;

public class Country {
    public static HashMap<String, PhoneNumber> countryMap = new HashMap<>(5);
    static {

        countryMap.put("212", PhoneNumber.builder().regex("^(212)\\ ?[5-9]\\d{8}$").country("Morocco").build());
        countryMap.put("237", PhoneNumber.builder().regex("^(237)\\ ?[2368]\\d{7,8}$").country("Cameroon").build());
        countryMap.put("251", PhoneNumber.builder().regex("^(251)\\ ?[1-59]\\d{8}$").country("Ethiopia").build());
        countryMap.put("256", PhoneNumber.builder().regex("^(256)\\ ?\\d{9}$").country("Uganda").build());
        countryMap.put("258", PhoneNumber.builder().regex("^(258)\\ ?[28]\\d{7,8}$").country("Mozambique").build());
    }
}
