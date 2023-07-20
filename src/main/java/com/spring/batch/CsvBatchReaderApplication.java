package com.spring.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.regex.Pattern;

@SpringBootApplication
public class CsvBatchReaderApplication {

    public static void main(String[] args) {

        SpringApplication.run(CsvBatchReaderApplication.class, args);
    }

}
