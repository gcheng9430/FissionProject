package com.example.fission;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FissionApplication {

    public static void main(String[] args) {

        Train.train();
        SpringApplication.run(FissionApplication.class, args);
    }

}
