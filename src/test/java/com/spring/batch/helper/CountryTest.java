package com.spring.batch.helper;


import com.spring.batch.model.PhoneNumber;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import io.micrometer.core.instrument.util.StringUtils;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CountryTest {


    @Mock
    private static HashMap<String, PhoneNumber> testCountryMap;

    private PhoneNumber phoneNumber1;
    private PhoneNumber phoneNumber2;

    private PhoneNumber phoneNumber3;

    private PhoneNumber phoneNumber4;

    private PhoneNumber phoneNumber5;


    @BeforeEach
    public void init() {
        phoneNumber1 = PhoneNumber.builder().regex("^(237)\\ ?[2368]\\d{7,8}$").country("Cameroon").build();
        phoneNumber2 = PhoneNumber.builder().regex("^(251)\\ ?[1-59]\\d{8}$").country("Ethiopia").build();
        phoneNumber3 = PhoneNumber.builder().regex("^(256)\\ ?\\d{9}$").country("Uganda").build();
        phoneNumber4 = PhoneNumber.builder().regex("^(258)\\ ?[28]\\d{7,8}$").country("Mozambique").build();
        phoneNumber5 = PhoneNumber.builder().regex("^(212)\\ ?[5-9]\\d{8}$").country("Morocco").build();


        testCountryMap.put("237", phoneNumber1);
        testCountryMap.put("251", phoneNumber2);
        testCountryMap.put("256", phoneNumber3);
        testCountryMap.put("258", phoneNumber4);
        testCountryMap.put("212", phoneNumber5);
    }

    @Test
    public void findCountryByPhoneNumber() {
        Country country = mock(Country.class);
        PhoneNumber phoneNumber = mock(PhoneNumber.class);

        Assertions.assertEquals("Morocco", Country.countryMap.get("212").getCountry());
        Assertions.assertEquals("Cameroon", Country.countryMap.get("237").getCountry());
        Assertions.assertEquals("Ethiopia", Country.countryMap.get("251").getCountry());
        Assertions.assertEquals("Uganda", Country.countryMap.get("256").getCountry());
        Assertions.assertEquals("Mozambique", Country.countryMap.get("258").getCountry());
        Assertions.assertEquals("^(258)\\ ?[28]\\d{7,8}$", Country.countryMap.get("258").getRegex());
        Assertions.assertNotEquals("Ethiopia", Country.countryMap.get("237").getCountry());

    }
}