package com.sd51.fsneakers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class FsneakersApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsneakersApplication.class, args);
    }

    @PostConstruct
    public void testEnv() {
        System.out.println("CLOUDINARY_CLOUD_NAME = " + System.getenv("CLOUDINARY_CLOUD_NAME"));
    }

}
