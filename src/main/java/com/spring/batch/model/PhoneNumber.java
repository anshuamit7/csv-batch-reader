package com.spring.batch.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PhoneNumber {
    String regex;
    String country;
}
