package com.wirespeed.clinic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
    "com.wirespeed.clinic",           // Scans your Controllers and Services
    "com.wirespeed.clinic.api",       // Scans the generated API interfaces
    "org.openapitools.configuration"  // Scans the generated OpenAPI config
})
public class ClinicConnectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicConnectApplication.class, args);
    }
}