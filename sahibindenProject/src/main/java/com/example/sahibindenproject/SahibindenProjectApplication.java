package com.example.sahibindenproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SahibindenProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SahibindenProjectApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Lets Move");
    }
}
